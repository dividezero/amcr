package com.angkasa.util;

import com.angkasa.dto.MemberExportDto;
import com.angkasa.model.CoopMember;
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

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Xlsx Parser Utility Class used to parse xlsx files
 *
 * @author <a href="mailto:hazlan@gmail.com">Hazlan Rozaimi</a>
 */
public final class TsvFileUtil {
    private static Log log = LogFactory.getLog(TsvFileUtil.class);

    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    private TsvFileUtil() {
    }

    public static String generateTsvStringFromMemberList(List<Member> memberList) {
        String results = "";

        for (Member member : memberList) {

            results += member.getName() + "\t";
            results += member.getIcNumber() + "\t";
            results += member.getPhoneNo() + "\n";
//            results += member.getCoopCode() + "\t";
//            results += member.getEmployerCode() + "\t";
//            results += member.getStatus() + "\t";
//            results += member.getEmployerNo() + "\n";
        }

        return results;
    }


}
