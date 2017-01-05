package com.angkasa.util;

import com.angkasa.Constants;
import com.angkasa.model.Coop;
import com.angkasa.model.Employer;
import com.angkasa.model.Member;
import com.angkasa.model.SimpleAddress;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Xlsx Parser Utility Class used to parse xlsx files
 *
 * @author <a href="mailto:hazlan@gmail.com">Hazlan Rozaimi</a>
 */
public final class XlsxParserUtil {
    private static Log log = LogFactory.getLog(XlsxParserUtil.class);

    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    private XlsxParserUtil() {
    }

    public static int getNumberOfColumns(File importFile) {
        int numberOfCells = 0;

        try {
            FileInputStream file = new FileInputStream(importFile);

            //Get the workbook instance for XLS file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator rowIterator = sheet.rowIterator();
            /**
             * Escape the header row *
             */
            if (rowIterator.hasNext()) {
                Row headerRow = (Row) rowIterator.next();
                //get the number of cells in the header row
                numberOfCells = headerRow.getPhysicalNumberOfCells();
            }
            System.out.println("number of cells " + numberOfCells);

            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return numberOfCells;
    }

    public static List<Employer> parseEmployerXlsx(File importFile) {
        List<Employer> employerList = new ArrayList<Employer>();

        try {
            FileInputStream file = new FileInputStream(importFile);

            //Get the workbook instance for XLS file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                Employer employer = new Employer();

                //For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();

                String empCode = getCellValue(cellIterator);

                if (StringUtils.isNotBlank(empCode) || !StringUtils.containsIgnoreCase(empCode, "Kod Majikan")) {

                    //Employer Code
                    employer.setEmployerCode(empCode);

                    //Employer Name
                    employer.setName(getCellValue(cellIterator));

                    //Employer Address
                    SimpleAddress address = new SimpleAddress();
                    address.setAddress1(getCellValue(cellIterator));
                    address.setAddress2(getCellValue(cellIterator));
                    address.setAddress3(getCellValue(cellIterator));
                    employer.setAddress(address);

                    employerList.add(employer);
                }

            }
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return employerList;

    }

    public static List<Coop> parseCoopXlsx(File importFile) {
        List<Coop> coopList = new ArrayList<Coop>();

        try {
            FileInputStream file = new FileInputStream(importFile);

            //Get the workbook instance for XLS file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();
            int importId = -1;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                Coop coop = new Coop();

                //For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                String empCode = getCellValue(cellIterator);
                if (StringUtils.isNotBlank(empCode)) {

                    //Employer Code
                    coop.setEmployerCode(empCode);

                    //Employer Name
                    coop.setName(getCellValue(cellIterator));

                    //Employer Address
                    SimpleAddress address = new SimpleAddress();
                    address.setAddress1(getCellValue(cellIterator));
                    address.setAddress2(getCellValue(cellIterator));
                    address.setAddress3(getCellValue(cellIterator));
                    coop.setAddress(address);

                    if (!StringUtils.containsIgnoreCase(empCode, "Kod Majikan")) {
                        coop.setImportId(importId + "");
                        coopList.add(coop);
                        importId--;
                    }

                }

            }
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return coopList;

    }

    public static List<Coop> parseCoopXlsx2(File importFile) {
        List<Coop> coopList = new ArrayList<Coop>();

        try {
            FileInputStream file = new FileInputStream(importFile);

            //Get the workbook instance for XLS file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();
            int i = 1;

            String newCoopRow = null;
            while (rowIterator.hasNext()) {
                Row row = null;

                //For each row, iterate through each columns. In this case theres only 1 column
                String rowStr = null;
                Iterator<Cell> cellIterator = null;
                if (newCoopRow != null) {
//                    row = newCoopRow;
                    rowStr = newCoopRow;
                    newCoopRow = null;
                } else {
                    row = rowIterator.next();
                    cellIterator = row.cellIterator();
                    rowStr = StringUtils.trim(getCellValue(cellIterator));
                }

                log.debug(rowStr);
                if (StringUtils.isNotBlank(rowStr)) {

                    //new coop
                    if (StringUtils.startsWith(rowStr, i + "")) {
                        log.debug("Doing " + i);
                        i++;

                        Coop coop = new Coop();
                        SimpleAddress simpleAddress = new SimpleAddress();

                        // name row
                        String[] strArr = StringUtils.splitByWholeSeparator(rowStr, "\t");
                        coop.setCoopCode(strArr[1]);
                        coop.setName(strArr[2]);

                        // address row
                        row = rowIterator.next();
                        cellIterator = row.cellIterator();
                        rowStr = getCellValue(cellIterator);
                        if (!StringUtils.contains(rowStr, "Jumlah:")) {
                            coop.setName(coop.getName() + " " + StringUtils.trim(rowStr));
                            row = rowIterator.next();
                            cellIterator = row.cellIterator();
                            rowStr = getCellValue(cellIterator);
                        }
                        strArr = StringUtils.splitByWholeSeparator(rowStr, "Jumlah:");
                        simpleAddress.setAddress1(StringUtils.trim(strArr[0]));
                        coop.setMemberTotal(Integer.parseInt(StringUtils.trim(strArr[strArr.length - 1])));

                        // third row
                        row = rowIterator.next();
                        cellIterator = row.cellIterator();
                        rowStr = getCellValue(cellIterator);
                        if (!StringUtils.contains(rowStr, "Lelaki:")) {
                            simpleAddress.setAddress2(StringUtils.trim(rowStr));
                            row = rowIterator.next();
                            cellIterator = row.cellIterator();
                            rowStr = getCellValue(cellIterator);
                        }
                        if (!StringUtils.contains(rowStr, "Lelaki:")) {
                            simpleAddress.setAddress3(StringUtils.trim(rowStr));
                            row = rowIterator.next();
                            cellIterator = row.cellIterator();
                            rowStr = getCellValue(cellIterator);
                        }

                        if (StringUtils.contains(rowStr, "Lelaki:")) {
                            strArr = StringUtils.splitByWholeSeparator(rowStr, "\t");
                            simpleAddress.setPostalCode(StringUtils.trim(strArr[0]));
                            simpleAddress.setCity(StringUtils.trim(strArr[1]));

                            String memberMale = StringUtils.trim(strArr[strArr.length - 1]);
                            if (StringUtils.isNumeric(memberMale)) {
                                coop.setMemberMale(Integer.parseInt(memberMale));
                            }


                        } else {
                            log.warn("Cant find \'Lelaki:\'");
                        }


                        // Fungsi
                        row = rowIterator.next();
                        cellIterator = row.cellIterator();
                        rowStr = getCellValue(cellIterator);
                        if (StringUtils.contains(rowStr, "Fungsi")) {
                            String value = StringUtils.trim(StringUtils.substringBetween(rowStr, "Fungsi", "Telefon:"));
                            coop.setFunction(value);

                            value = StringUtils.trim(StringUtils.substringBetween(rowStr, "Telefon:", "Perempuan:"));


                            if (StringUtils.contains(value, "/")) {
                                String[] phoneNos = StringUtils.splitByWholeSeparator(value, "/");
                                phoneNos[0] = phoneNos[0].replaceAll("[^\\d-]", "");
                                coop.setPhoneNo(phoneNos[0]);
                                phoneNos[1] = phoneNos[1].replaceAll("[^\\d-]", "");
                                coop.setPhoneNo2(phoneNos[1]);
                            } else if (StringUtils.contains(value, ",")) {
                                String[] phoneNos = StringUtils.splitByWholeSeparator(value, ",");
                                phoneNos[0] = phoneNos[0].replaceAll("[^\\d-]", "");
                                coop.setPhoneNo(phoneNos[0]);
                                phoneNos[1] = phoneNos[1].replaceAll("[^\\d-]", "");
                                coop.setPhoneNo2(phoneNos[1]);
                            } else if (StringUtils.contains(value, ")")) {
                                String[] phoneNos = StringUtils.splitByWholeSeparator(value, ")");
                                phoneNos[0] = phoneNos[0].replaceAll("[^\\d-]", "");
                                coop.setPhoneNo(phoneNos[0]);
                                phoneNos[1] = phoneNos[1].replaceAll("[^\\d-]", "");
                                coop.setPhoneNo2(phoneNos[1]);
                            } else {
                                value = value.replaceAll("[^\\d-]", "");
                                coop.setPhoneNo(value);
                            }

                            value = StringUtils.trim(StringUtils.substringAfterLast(rowStr, "Perempuan:"));
                            if (StringUtils.isNumeric(value)) {
                                coop.setMemberFemale(Integer.parseInt(value));
                            }

                        } else {
                            log.warn("Fungsi not found");
                        }

                        // Fax
                        row = rowIterator.next();
                        cellIterator = row.cellIterator();
                        rowStr = getCellValue(cellIterator);
                        if (StringUtils.contains(rowStr, "Fax:")) {
                            String value = StringUtils.trim(StringUtils.substringBetween(rowStr, "Tarikh Daftar SKM", "Fax:"));
                            try {
                                coop.setSkmJoinDate(DateUtil.convertStringToDate("dd/MM/yyyy", value));
                            } catch (Exception e) {
                                log.fatal("SKM join date failed to parse.");
                            }

                            value = StringUtils.trim(StringUtils.substringAfterLast(rowStr, "Fax:"));
                            coop.setFaxNo(value);

                        } else {
                            log.warn("Fax not found");
                        }

                        // Koperasi
                        row = rowIterator.next();
                        cellIterator = row.cellIterator();
                        rowStr = getCellValue(cellIterator);
                        if (StringUtils.contains(rowStr, "Koperasi:")) {
                            //TODO defuq is this?

                        } else {
                            log.warn("Koperasi count not found");
                        }

                        // 	No Daftar SKM, Kod BPA, Tarikh Keahlian
                        row = rowIterator.next();
                        cellIterator = row.cellIterator();
                        rowStr = getCellValue(cellIterator);
                        if (StringUtils.contains(rowStr, "No Daftar SKM")) {
                            String value = StringUtils.trim(StringUtils.substringBetween(rowStr, "No Daftar SKM", "Kod BPA:"));
                            if (StringUtils.isNotBlank(value)) {
                                coop.setSkmRegistrationNo(value);
                            }

                            value = StringUtils.trim(StringUtils.substringBetween(rowStr, "Kod BPA:", "Tarikh Keahlian:"));
                            if (StringUtils.isNotBlank(value)) {
                                coop.setBpaCode(value);
                            }

                            value = StringUtils.trim(StringUtils.substringAfterLast(rowStr, "Tarikh Keahlian:"));
                            try {
                                coop.setAngkasaJoinDate(DateUtil.convertStringToDate("dd/MM/yyyy", value));
                            } catch (Exception e) {
                                log.fatal("Angkasa join date failed to parse.");
                            }

                        } else {
                            log.warn("No Daftar SKM count not found");
                        }

                        // 	Kegiatan
                        row = rowIterator.next();
                        cellIterator = row.cellIterator();
                        rowStr = getCellValue(cellIterator);
                        if (StringUtils.contains(rowStr, "Kegiatan")) {
                            String value = StringUtils.trim(StringUtils.substringAfterLast(rowStr, "Kegiatan"));

                            row = rowIterator.next();
                            cellIterator = row.cellIterator();
                            rowStr = StringUtils.trim(getCellValue(cellIterator));

                            if (StringUtils.startsWith(rowStr, i + "")) {
                                newCoopRow = rowStr;
                            } else if (StringUtils.contains(rowStr, "Direktori Ahli ANGKASA")) {
                                // do nothing. just page no.

                            } else {
                                value += StringUtils.trim(rowStr);
                            }
                            coop.setDescription(value);

                        } else {
                            log.warn("Kegiatan not found");
                        }

                        // save
                        coop.setAddress(simpleAddress);
                        coopList.add(coop);
                    }

                }
            }


            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return coopList;

    }

    public static List<Member> parseMemberXlsx(File importFile) {
        List<Member> memberList = new ArrayList<Member>();

        try {
            FileInputStream file = new FileInputStream(importFile);

            //Get the workbook instance for XLS file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            int importId = -1;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                Member member = new Member();

                //For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();

                String icNumber = getCellValue(cellIterator);

                if (StringUtils.isNotBlank(icNumber) || !StringUtils.containsIgnoreCase(icNumber, "No KP")) {

                    //Member IC Number
                    member.setIcNumber(icNumber);

                    //Coop Code
                    //member.setImportCoopCode(getCellValue(cellIterator));

                    //BPA Code
                    member.setImportBPACode(getCellValue(cellIterator));

                    //Member Name
                    member.setName(getCellValue(cellIterator));

                    //Employer Code
                    member.setEmployerCode(getCellValue(cellIterator));

                    //Member Status
                    String status = StringUtils.trim(getCellValue(cellIterator));
                    if (StringUtils.equalsIgnoreCase(status, "A")) {
                        member.setStatus(Constants.MEMBER_STATUS_ACTIVE);
                    }


                    //Coop Membership No
                    member.setImportCoopMemberAknNo(getCellValue(cellIterator));

                    //AMCR code
                    member.setImportCoopAmcrCode(getCellValue(cellIterator));

                    //TODO CoopCode 2

                    member.setImportId(importId + "");
                    memberList.add(member);
                    importId--;
                }

            }
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return memberList;

    }

    private static String getCellValue(Iterator<Cell> cellIterator) {
        String result = null;
        if (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();

            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    result = (cell.getBooleanCellValue() == true) ? "true" : "false";
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    result = String.valueOf((long) cell.getNumericCellValue()) + "";
                    break;
                case Cell.CELL_TYPE_STRING:
                    result = cell.getStringCellValue();
                    break;
            }
        }
        return result;
    }

}
