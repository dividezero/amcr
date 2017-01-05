package com.angkasa.util;

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
 * String Parser Utility Class
 *
 * @author <a href="mailto:hazlan@gmail.com">Hazlan Rozaimi</a>
 */
public final class StringUtil {
    private static Log log = LogFactory.getLog(StringUtil.class);

    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    private StringUtil() {
    }

    public static String leftPad(String strToPad, String pad, int length) {
        String result = "";
        while (result.length() < length) {
            result += pad;
        }
        return result + strToPad;
    }

    public static Boolean isNumeric(String str){
        return str.trim().matches("-?\\d+(\\.\\d+)?");
    }
}
