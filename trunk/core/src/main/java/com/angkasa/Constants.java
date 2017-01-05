package com.angkasa;


/**
 * Constant values used throughout the application.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public final class Constants {

    private Constants() {
        // hide me
    }
    //~ Static fields/initializers =============================================

    // Windows is \ while linux or mac is /
    public static final String SYSTEM_SLASH = "/";

    /**
     * Assets Version constant
     */
    public static final String ASSETS_VERSION = "assetsVersion";
    /**
     * The name of the ResourceBundle used in this application
     */
    public static final String BUNDLE_KEY = "ApplicationResources";

    /**
     * File separator from System properties
     */
    public static final String FILE_SEP = System.getProperty("file.separator");

    /**
     * User home from System properties
     */
    public static final String USER_HOME = System.getProperty("user.home") + FILE_SEP;

    /**
     * The name of the configuration hashmap stored in application scope.
     */
    public static final String CONFIG = "appConfig";

    /**
     * Session scope attribute that holds the locale set by the user. By setting this key
     * to the same one that Struts uses, we get synchronization in Struts w/o having
     * to do extra work or have two session-level variables.
     */
    public static final String PREFERRED_LOCALE_KEY = "org.apache.struts2.action.LOCALE";

    /**
     * The request scope attribute under which an editable user form is stored
     */
    public static final String USER_KEY = "userForm";

    /**
     * The request scope attribute that holds the user list
     */
    public static final String USER_LIST = "userList";

    /**
     * The request scope attribute for indicating a newly-registered user
     */
    public static final String REGISTERED = "registered";

    /**
     * The name of the Administrator role, as specified in web.xml
     */
    public static final String ADMIN_ROLE = "ROLE_ADMIN";

    /**
     * The name of the User role, as specified in web.xml
     */
    public static final String USER_ROLE = "ROLE_USER";

    public static final String USER_ROLE_MEMBER = "ROLE_MEMBER";
    public static final String USER_ROLE_COOP = "ROLE_COOP";
    public static final String USER_ROLE_ANGKASA = "ROLE_ANGKASA";
    public static final String USER_ROLE_DMS = "ROLE_DMS";
    public static final String USER_ROLE_EVENT = "ROLE_EVENT";

    /**
     * The name of the user's role list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String USER_ROLES = "userRoles";

    /**
     * The name of the available roles list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String AVAILABLE_ROLES = "availableRoles";

    /**
     * The name of the CSS Theme setting.
     *
     * @deprecated No longer used to set themes.
     */
    public static final String CSS_THEME = "csstheme";

    /**
     * Applicable month and year to use
     */
    public static String APPLICABLE_DATE_MONTH_YEAR = ""; //ddmmYYYY format
    public static String DEFAULT_USER = "SYSTEM";

    public static final Integer SYSTEM_SETTING_DEFAULT_FILE_SIZE_LIMIT = 2;


    // System settings
    public static final String SYSTEM_SETTING_SHOW_PASSWORD_URL = "show.password.url";
    public static final String SYSTEM_SETTING_USE_COOP_CODE_IN_MEMBER_IMPORT = "memberimport.use.coopcode";
    public static final String SYSTEM_SETTING_ENABLE_DMS_API = "dmsapi.enabled";

    // Gender
    public static final String GENDER_MALE = "M";
    public static final String GENDER_FEMALE = "F";

    // Marital Status
    public static final String MARITAL_STATUS_SINGLE = "SINGLE";
    public static final String MARITAL_STATUS_MARRIED = "MARRIED";

    // Designation List
    public static final String DESIGNATION_MR = "Mr";
    public static final String DESIGNATION_MS = "Ms";
    public static final String DESIGNATION_MRS = "Mrs";
    public static final String DESIGNATION_DR = "Dr";
    public static final String DESIGNATION_PROF = "Prof";
    public static final String DESIGNATION_PROF_MADYA = "Prof Madya";
    public static final String DESIGNATION_DATO = "Dato'";
    public static final String DESIGNATION_DATUK = "Datuk";
    public static final String DESIGNATION_DATIN = "Datin";
    public static final String DESIGNATION_DATO_SERI = "Dato' Seri";
    public static final String DESIGNATION_DATIN_SERI = "Datin Seri";
    public static final String DESIGNATION_DATUK_SERI = "Datuk Seri";
    public static final String DESIGNATION_TAN_SRI = "Tan Sri";
    public static final String DESIGNATION_DATIN_SRI = "Datin Sri";
    public static final String DESIGNATION_TUN = "Tun";
    public static final String DESIGNATION_PEHIN = "Pehin";
    public static final String DESIGNATION_PEHIN_SRI = "Pehin Sri";

    // Race List
    public static final String RACE_MALAY = "MALAY";
    public static final String RACE_CHINESE = "CHINESE";
    public static final String RACE_INDIAN = "INDIAN";
    public static final String RACE_OTHERS = "OTHERS";

    // Langages
    public static final String LANGUAGE_ENGLISH = "ENGLISH";
    public static final String LANGUAGE_MALAY = "MALAY";
    public static final String LANGUAGE_CHINESE = "CHINESE";

    // Coop Flags
    public static final String COOP_FLAG_ANGKASA = "ANGKASA";
    public static final String COOP_FLAG_COOP = "COOP";
    public static final String COOP_FLAG_NEW = "NEW";

    // Member Flags
    public static final String MEMBER_FLAG_ANGKASA = "ANGKASA";
    public static final String MEMBER_FLAG_COOP = "COOP";
    public static final String MEMBER_FLAG_NEW = "NEW";


    // Member Statuses
    public static final String MEMBER_STATUS_ACTIVE = "ACTIVE";
    public static final String MEMBER_STATUS_INACTIVE = "INACTIVE";
    public static final String MEMBER_STATUS_SUSPENDED = "SUSPENDED";
    public static final String MEMBER_STATUS_PERMABANNED = "PERMABANNED";

    // Import Statuses
    public static final String IMPORT_STATUS_VALID = "VALID";
    public static final String IMPORT_STATUS_INVALID = "INVALID";
    public static final String IMPORT_STATUS_SUCCESS = "SUCCESS";
    public static final String IMPORT_STATUS_FAILED = "FAILED";

    // Transaction Type
    public static final String TRANS_TYPE_DEDUCT_ACCT = "DEDUCT_ACCT";
    public static final String TRANS_TYPE_DEDUCT_SALARY = "DEDUCT_SALARY";
    public static final String TRANS_TYPE_DEPOSIT_ACCT = "DEPOSIT_ACCT";


}

