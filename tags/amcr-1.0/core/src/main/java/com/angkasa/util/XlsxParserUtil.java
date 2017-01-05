package com.angkasa.util;

import com.angkasa.model.Coop;
import com.angkasa.model.Employer;
import com.angkasa.model.Member;
import com.angkasa.model.SimpleAddress;
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
 *
 * @author <a href="mailto:hazlan@gmail.com">Hazlan Rozaimi</a>
 *
 */
public final class XlsxParserUtil {
	private static Log log = LogFactory.getLog(XlsxParserUtil.class);

	/**
	 * Checkstyle rule: utility classes should not have public constructor
	 */
	private XlsxParserUtil() {
	}

    public static int getNumberOfColumns(File importFile){
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
            if (rowIterator.hasNext())
            {
                Row headerRow = (Row) rowIterator.next();
                //get the number of cells in the header row
                numberOfCells = headerRow.getPhysicalNumberOfCells();
            }
            System.out.println("number of cells "+numberOfCells);

            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return numberOfCells;
    }

    public static List<Employer> parseEmployerXlsx(File importFile){
        List<Employer> employerList = new ArrayList<Employer>();

        try {
            FileInputStream file = new FileInputStream(importFile);

            //Get the workbook instance for XLS file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();
            while(rowIterator.hasNext()) {
                Row row = rowIterator.next();

                Employer employer = new Employer();

                //For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();

                String empCode = getCellValue(cellIterator);

                if(StringUtils.isNotBlank(empCode) || !StringUtils.containsIgnoreCase(empCode, "Kod Majikan")){

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

    public static List<Coop> parseCoopXlsx(File importFile){
        List<Coop> coopList = new ArrayList<Coop>();

        try {
            FileInputStream file = new FileInputStream(importFile);

            //Get the workbook instance for XLS file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();
            while(rowIterator.hasNext()) {
                Row row = rowIterator.next();

                Coop coop = new Coop();

                //For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                String empCode = getCellValue(cellIterator);
                if(StringUtils.isNotBlank(empCode)){

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

                    if(!StringUtils.containsIgnoreCase(empCode, "Kod Majikan")){
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

    public static List<Member> parseMemberXlsx(File importFile){
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
            while(rowIterator.hasNext()) {
                Row row = rowIterator.next();

                Member member = new Member();

                //For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();

                String icNumber = getCellValue(cellIterator);

                if(StringUtils.isNotBlank(icNumber) || !StringUtils.containsIgnoreCase(icNumber, "No KP")){

                    //Member IC Number
                    member.setIcNumber(icNumber);

                    //Coop Code
                    member.setImportCoopCode(getCellValue(cellIterator));

                    //Member Name
                    member.setName(getCellValue(cellIterator));

                    //Employer Code
                    member.setImportEmployerCode(getCellValue(cellIterator));

                    //Member Status
                    member.setStatus(getCellValue(cellIterator));

                    //TODO CoopCode 2

                    member.setImportId(importId+"");
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

    private static String getCellValue(Iterator<Cell> cellIterator){
        String result = "";
        if(cellIterator.hasNext()){
            Cell cell = cellIterator.next();

            switch(cell.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    result = (cell.getBooleanCellValue() == true)? "true" : "false";
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    result = cell.getNumericCellValue() + "";
                    break;
                case Cell.CELL_TYPE_STRING:
                    result = cell.getStringCellValue();
                    break;
            }
        }
        return result;
    }

}
