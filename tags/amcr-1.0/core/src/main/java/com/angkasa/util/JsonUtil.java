package com.angkasa.util;

import com.angkasa.model.Coop;
import com.angkasa.model.Employer;
import com.angkasa.model.Member;
import com.angkasa.model.SimpleAddress;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
 * JSON Utility Class used to parse and create JSON strings
 *
 *
 * @author <a href="mailto:hazlan@gmail.com">Hazlan Rozaimi</a>
 *
 */
public final class JsonUtil {
	private static Log log = LogFactory.getLog(JsonUtil.class);

	/**
	 * Checkstyle rule: utility classes should not have public constructor
	 */
	private JsonUtil() {
	}

    public static String convertEmployerListToJson(List<Employer> employerList){

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        String result = "";
        for(Employer employer : employerList){
            result += gson.toJson(employer, Employer.class) + "|";
        }
        return result;
    }

    public static List<Employer> parseJsonToEmployerList(String json){

        ArrayList<Employer> employerList = new ArrayList<Employer>();

        if(StringUtils.isNotBlank(json)){

            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

            String[] jsonEmpArray = StringUtils.split(json, "|");

            for (String jsonEmp : jsonEmpArray) {
                employerList.add(gson.fromJson(jsonEmp, Employer.class));
            }
        }
        return employerList;
    }

    public static String convertMemberListToJson(List<Member> memberList){

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        String result = "";
        for(Member member : memberList){
            result += gson.toJson(member, Member.class) + "|";
        }
        return result;
    }

    public static List<Member> parseJsonToMemberList(String json){

        ArrayList<Member> memberList = new ArrayList<Member>();

        if(StringUtils.isNotBlank(json)){

            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

            String[] jsonMemArray = StringUtils.split(json, "|");

            for (String jsonMem : jsonMemArray) {
                memberList.add(gson.fromJson(jsonMem, Member.class));
            }
        }
        return memberList;
    }

    public static String convertCoopListToJson(List<Coop> coopList){

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        String result = "";
        for(Coop coop : coopList){
            result += gson.toJson(coop, Coop.class) + "|";
        }
        return result;
    }

    public static List<Coop> parseJsonToCoopList(String json){

        ArrayList<Coop> coopList = new ArrayList<Coop>();

        if(StringUtils.isNotBlank(json)){

            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

            String[] jsonMemArray = StringUtils.split(json, "|");

            for (String jsonCoop : jsonMemArray) {
                coopList.add(gson.fromJson(jsonCoop, Coop.class));
            }
        }
        return coopList;
    }
}
