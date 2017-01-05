package com.angkasa.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import com.angkasa.Constants;

/**
 * Date Utility Class used to convert Strings to Dates and Timestamps
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *         Modified by <a href="mailto:dan@getrolling.com">Dan Kibler </a>
 *         to correct time pattern. Minutes should be mm not MM (MM is month).
 */
public final class DateUtil {
	private static Log log = LogFactory.getLog(DateUtil.class);
	private static final String TIME_PATTERN = "HH:mm";
	private static final String FULL_TIME_PATTERN = "ddMMyyyyHHmm";
	public static final String yyyyMMdd = "yyyyMMdd";
	public static final String ddMMyyyy = "ddMMyyyy";
	public static final String yyyyMM = "yyyyMM";

	public static final String MONTH_YEAR_PATTERN = "MMyyyy";
	public static final String MONTH_YEAR_PATTERN_WITH_SLASH = "MM/yyyy";
	public static final String DAY_MONTH_YEAR_PATTERN_WITH_DASH = "dd-MM-yyyy";
	public static final String YEAR_PATTERN = "yyyy";

	/**
	 * Checkstyle rule: utility classes should not have public constructor
	 */
	private DateUtil() {
	}

	/**
	 * Returns a new date based on the current time
	 * 
	 * @return
	 */
	public static Date getDateToday() {
		Date date = new Date();
		if (StringUtils.isNotBlank(Constants.APPLICABLE_DATE_MONTH_YEAR)) {
			String day = Constants.APPLICABLE_DATE_MONTH_YEAR.substring(0, 2);
			String month = Constants.APPLICABLE_DATE_MONTH_YEAR.substring(2, 4);
			String year = Constants.APPLICABLE_DATE_MONTH_YEAR.substring(4, 8);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
			cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
			cal.set(Calendar.YEAR, Integer.parseInt(year));
			return cal.getTime();
		} else
			return date;
	}

    public static Date getDateYesterday() {
        return DateUtils.addDays(getDateToday(), -1);
    }

	public static Date getDateCurrentMonth() throws ParseException {
		return convertMonthStringToDate(getMonthYear());
	}

	/**
	 * This method converts a month String to a date using the
	 * MOTHN_YEAR_PATTERN
	 * 
	 * @param strDate
	 *            the date to convert (in format MMyyyy)
	 * @return a date object
	 * @throws ParseException
	 *             when String doesn't match the expected format
	 */
	public static Date convertMonthStringToDate(final String strDate) throws ParseException {
		return convertStringToDate(MONTH_YEAR_PATTERN, strDate);
	}

	/**
	 * 
	 * @return the current date in the format mmYYYY
	 */
	public static String getMonthYear() {
		return getDateTime(MONTH_YEAR_PATTERN, getDateToday());
	}

	/**
	 * Return default datePattern (MM/dd/yyyy)
	 *
	 * @return a string representing the date pattern on the UI
	 */
	public static String getDatePattern() {
		Locale locale = LocaleContextHolder.getLocale();
		String defaultDatePattern;
		try {
			defaultDatePattern = ResourceBundle.getBundle(Constants.BUNDLE_KEY, locale).getString("date.format");
		} catch (MissingResourceException mse) {
			defaultDatePattern = "MM/dd/yyyy";
		}

		return defaultDatePattern;
	}

	public static String getDateTimePattern() {
		return DateUtil.getDatePattern() + " HH:mm:ss.S";
	}

	/**
	 * This method attempts to convert an Oracle-formatted date
	 * in the form dd-MMM-yyyy to mm/dd/yyyy.
	 *
	 * @param aDate date from database as a string
	 * @return formatted string for the ui
	 */
	public static String getDate(Date aDate) {
		SimpleDateFormat df;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date/time
	 * in the format you specify on input
	 *
	 * @param aMask the date pattern the string is in
	 * @param strDate a string representation of a date
	 * @return a converted Date object
	 * @throws ParseException when String doesn't match the expected format
	 * @see java.text.SimpleDateFormat
	 */
	public static Date convertStringToDate(String aMask, String strDate) throws ParseException {
		SimpleDateFormat df;
		Date date;
		df = new SimpleDateFormat(aMask);

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
		}

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			//log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	/**
	 * This method returns the current date time in the format:
	 * MM/dd/yyyy HH:MM a
	 *
	 * @param theTime the current time
	 * @return the current date/time
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(TIME_PATTERN, theTime);
	}

	/**
	 * This method returns the current date in the format: MM/dd/yyyy
	 *
	 * @return the current date
	 * @throws ParseException when String doesn't match the expected format
	 */
	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	/**
	 * This method generates a string representation of a date's date/time
	 * in the format you specify on input
	 *
	 * @param aMask the date pattern the string is in
	 * @param aDate a date object
	 * @return a formatted string representation of the date
	 * @see java.text.SimpleDateFormat
	 */
	public static String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			log.warn("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date based
	 * on the System Property 'dateFormat'
	 * in the format you specify on input
	 *
	 * @param aDate A date to convert
	 * @return a string representation of the date
	 */
	public static String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	/**
	 * This method converts a String to a date using the datePattern
	 *
	 * @param strDate the date to convert (in format MM/dd/yyyy)
	 * @return a date object
	 * @throws ParseException when String doesn't match the expected format
	 */
	public static Date convertStringToDate(final String strDate) throws ParseException {
		return convertStringToDate(getDatePattern(), strDate);
	}

    /**
     * Given a date, this method will return a Date object set to the first
     * second of the given day.
     *
     * @param date
     * @return
     */
    public static Date getDayBegin(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    /**
     * Given a date, this method will return a Date object set to the last
     * second of the given day.
     *
     * @param date
     * @return
     */
    public static Date getDayEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        return cal.getTime();
    }
}
