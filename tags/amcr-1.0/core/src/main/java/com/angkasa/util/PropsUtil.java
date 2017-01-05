package com.angkasa.util;

import com.angkasa.Constants;
import com.angkasa.service.SystemSettingManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * This component is a helper class to get System Setting properties
 */
@Component
public class PropsUtil implements Serializable {

    @Autowired
    SystemSettingManager systemSettingManager;

    private static final long serialVersionUID = 6569165447568323344L;
    private static final Map<String, Integer> months = new HashMap<String, Integer>();

    static {
        months.put("January", 1);
        months.put("Feburary", 2);
        months.put("March", 3);
        months.put("April", 4);
        months.put("May", 5);
        months.put("June", 6);
        months.put("July", 7);
        months.put("Augest", 8);
        months.put("September", 9);
        months.put("October", 10);
        months.put("November", 11);
        months.put("December", 12);
    }

    public String getDocumentRootPath() {
        String rootPath = "/tmp/";
        if (StringUtils.isBlank(rootPath)) //return Constants.SYSTEM_SETTING_DEFAULT_DOC_ROOT_PATH;
        {
            return System.getProperty("java.io.tmpdir");
        } else {
            return rootPath;
        }
    }

    public String getFileSlash() {
        String slash = "/";
        if(StringUtils.isNotBlank(slash)){
            return slash;
        } else {
            return Constants.SYSTEM_SLASH;
        }
    }

    public Integer getFileLimitSize() {
        String fileSizeLimit = "2";
        if (StringUtils.isBlank(fileSizeLimit)) {
            return Constants.SYSTEM_SETTING_DEFAULT_FILE_SIZE_LIMIT;
        } else {
            return Integer.valueOf(fileSizeLimit);
        }
    }

    public Map<String, String> getBooleanList(){
        Map<String, String> bools = new HashMap<>();
        bools.put("true", "TRUE");
        bools.put("false", "FALSE");

        return bools;
    }

    public Map<String, String> getGenderList(){
        Map<String, String> genders = new HashMap<>();
        genders.put(Constants.GENDER_MALE, "Male");
        genders.put(Constants.GENDER_FEMALE, "Female");

        return genders;
    }

    public Map<String, String> getMaritalStatusList(){
        Map<String, String> list = new HashMap<>();
        list.put(Constants.MARITAL_STATUS_SINGLE, "Single");
        list.put(Constants.MARITAL_STATUS_MARRIED, "Married");

        return list;
    }

    public Map<String, String> getRaceList(){
        Map<String, String> list = new HashMap<>();
        list.put(Constants.RACE_MALAY, "Malay");
        list.put(Constants.RACE_CHINESE, "Chinese");
        list.put(Constants.RACE_INDIAN, "Indian");
        list.put(Constants.RACE_OTHERS, "Others");

        return list;
    }

    public Map<String, String> getLanguageList(){
        Map<String, String> list = new HashMap<>();
        list.put(Constants.LANGUAGE_ENGLISH, "English");
        list.put(Constants.LANGUAGE_MALAY, "Malay");
        list.put(Constants.LANGUAGE_CHINESE, "Chinese");

        return list;
    }

    public Map<String, String> getMemberStatusList(){
        Map<String, String> list = new HashMap<>();
        list.put(Constants.MEMBER_STATUS_ACTIVE, Constants.MEMBER_STATUS_ACTIVE);
        list.put(Constants.MEMBER_STATUS_INACTIVE, Constants.MEMBER_STATUS_INACTIVE);
        list.put(Constants.MEMBER_STATUS_PERMABANNED, Constants.MEMBER_STATUS_PERMABANNED);
        list.put(Constants.MEMBER_STATUS_SUSPENDED, Constants.MEMBER_STATUS_SUSPENDED);

        return list;
    }


    public Map<String, String> getMemberTypeFlagList(){
        Map<String, String> list = new HashMap<>();
        list.put(Constants.MEMBER_FLAG_NEW, Constants.MEMBER_FLAG_NEW);
        list.put(Constants.MEMBER_FLAG_ANGKASA, Constants.MEMBER_FLAG_ANGKASA);
        list.put(Constants.MEMBER_FLAG_COOP, Constants.MEMBER_FLAG_COOP);

        return list;
    }

    public Map<String, String> getCoopTypeFlagList(){
        Map<String, String> list = new HashMap<>();
        list.put(Constants.COOP_FLAG_NEW, Constants.COOP_FLAG_NEW);
        list.put(Constants.COOP_FLAG_ANGKASA, Constants.COOP_FLAG_ANGKASA);
        list.put(Constants.COOP_FLAG_COOP, Constants.COOP_FLAG_COOP);

        return list;
    }

    public Map<String, String> getDesignationList(){
        Map<String, String> list = new HashMap<>();
        list.put(Constants.DESIGNATION_MR, Constants.DESIGNATION_MR);
        list.put(Constants.DESIGNATION_MS, Constants.DESIGNATION_MS);
        list.put(Constants.DESIGNATION_MRS, Constants.DESIGNATION_MRS);
        list.put(Constants.DESIGNATION_DR, Constants.DESIGNATION_DR);
        list.put(Constants.DESIGNATION_PROF, Constants.DESIGNATION_PROF);
        list.put(Constants.DESIGNATION_DATO, Constants.DESIGNATION_DATO);
        list.put(Constants.DESIGNATION_DATIN, Constants.DESIGNATION_DATIN);

        return list;
    }

    public boolean isShowPasswordUrl(){
        String value = systemSettingManager.valueByPropertyName(Constants.SYSTEM_SETTING_SHOW_PASSWORD_URL);
        if(StringUtils.equalsIgnoreCase(value, "true")){
            return true;
        }
        return false;
    }
}