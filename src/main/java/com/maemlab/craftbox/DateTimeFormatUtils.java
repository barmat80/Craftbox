package com.maemlab.craftbox;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class DateTimeFormatUtils {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter ITALIAN_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(Locale.ITALY);
    private static final DateTimeFormatter ITALIAN_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final DateTimeFormatter UK_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd").withLocale(Locale.UK);
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter COMPACT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private DateTimeFormatUtils() {}

    //**********************
    //*  Format Now (Date) *
    //**********************

    /**
     * Formats the current date and time into ISO_LOCAL_DATE format (yyyy-MM-dd)
     *
     * @return the current date in ISO format
     */
    public static String formatNow() {
        return formatNow(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Formats the current date into Italian style (dd/MM/yyyy)
     *
     * @return the current date in Italian format
     */
    public static String formatNowItalian() {
        return formatNow(ITALIAN_DATE_FORMATTER);
    }

    /**
     * Formats the current date into UK style (yyyy/MM/dd)
     *
     * @return the current date in UK format
     */
    public static String formatNowUK() {
        return formatNow(UK_DATE_FORMATTER);
    }

    /**
     * Formats the current date using a custom DateTimeFormatter
     *
     * @param dateFormat the date format to use
     * @return the formatted current date
     */
    public static String formatNow(DateTimeFormatter dateFormat) {
        return LocalDate.now().format(dateFormat);
    }

    //*****************************
    //*  Format Now (Date + Time) *
    //*****************************

    /**
     * Formats the current date and time using custom formatters
     *
     * @param dateFormat the date part format
     * @param timeFormat the time part format
     * @return the formatted current date and time
     */
    public static String formatNow(DateTimeFormatter dateFormat, DateTimeFormatter timeFormat) {
        return LocalDate.now().format(dateFormat) + " " + LocalTime.now().format(timeFormat);
    }

    /**
     * Formats the current date and time as a timestamp (yyyy-MM-dd HH:mm:ss)
     *
     * @return the current timestamp
     */
    public static String nowToTimestamp() {
        return LocalDateTime.now().format(TIMESTAMP_FORMATTER);
    }

    //*********************
    //*  Format LocalDate *
    //*********************

    /**
     * Formats a LocalDate into ISO format (yyyy-MM-dd)
     *
     * @param localDate the date to format
     * @return the formatted date in ISO format
     * @throws NullPointerException if localDate is null
     */
    public static String formatDate(LocalDate localDate) {
        if (localDate == null) {
            throw new NullPointerException("localDate cannot be null");
        }
        return localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Formats a LocalDate into Italian style (dd/MM/yyyy)
     *
     * @param localDate the date to format
     * @return the formatted date in Italian format
     * @throws NullPointerException if localDate is null
     */
    public static String formatDateItalian(LocalDate localDate) {
        if (localDate == null) {
            throw new NullPointerException("localDate cannot be null");
        }
        return localDate.format(ITALIAN_DATE_FORMATTER);
    }

    /**
     * Formats a LocalDate into UK style (yyyy/MM/dd)
     *
     * @param localDate the date to format
     * @return the formatted date in UK format
     * @throws NullPointerException if localDate is null
     */
    public static String formatDateUK(LocalDate localDate) {
        if (localDate == null) {
            throw new NullPointerException("localDate cannot be null");
        }
        return localDate.format(UK_DATE_FORMATTER);
    }

    /**
     * Formats a LocalDate and LocalTime into ISO format (yyyy-MM-dd HH:mm:ss)
     *
     * @param localDate the date to format
     * @param localTime the time to format
     * @return the formatted date and time
     * @throws NullPointerException if localDate or localTime is null
     */
    public static String formatDateTime(LocalDate localDate, LocalTime localTime) {
        if (localDate == null || localTime == null) {
            throw new NullPointerException("localDate and localTime cannot be null");
        }
        return localDate.format(DateTimeFormatter.ISO_LOCAL_DATE) + " " + localTime.format(TIME_FORMATTER);
    }

    //***********************
    //*  String Conversions *
    //***********************

    /**
     * Converts an ISO date string (yyyy-MM-dd) or datetime string (yyyy-MM-dd HH:mm:ss)
     * to Italian date format (dd-MM-yyyy)
     *
     * @param date the date string to format
     * @return the date in Italian format, or empty string if input is blank
     * @throws java.time.format.DateTimeParseException if the string cannot be parsed
     */
    public static String toItalianDate(String date) {
        if (date == null || date.isBlank()) {
            return "";
        }
        // Handle both date and datetime strings by taking only the date part
        String datePart = date.split(" ")[0];
        LocalDate localDate = LocalDate.parse(datePart, DateTimeFormatter.ISO_LOCAL_DATE);
        return localDate.format(ITALIAN_DATE_FORMATTER);
    }

    /**
     * Converts an ISO datetime string (yyyy-MM-dd HH:mm:ss) to Italian format (dd-MM-yyyy HH:mm:ss)
     *
     * @param datetime the datetime string to format
     * @return the datetime in Italian format
     */
    public static String toItalianDateAndTime(String datetime) {
        if (datetime == null || datetime.isBlank()) {
            return "";
        }
        LocalDateTime localDateTime = LocalDateTime.parse(datetime, TIMESTAMP_FORMATTER);
        return localDateTime.format(ITALIAN_DATETIME_FORMATTER);
    }

    /**
     * Parses an ISO date string (yyyy-MM-dd) into a LocalDate
     *
     * @param date the date string to parse
     * @return the parsed LocalDate
     * @throws NullPointerException if localDate is null
     */
    public static LocalDate toISOLocalDate(String date) {
        if (date == null) {
            throw new NullPointerException("localDate cannot be null");
        }
        return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Converts a LocalDate to ISO string format (yyyy-MM-dd)
     *
     * @param localDate the date to convert
     * @return the ISO formatted date string
     * @throws NullPointerException if localDate is null
     */
    public static String toISOString(LocalDate localDate) {
        if (localDate == null) {
            throw new NullPointerException("localDate cannot be null");
        }
        return DateTimeFormatter.ISO_LOCAL_DATE.format(localDate);
    }

    /**
     * Converts a LocalDate to Italian string format (dd/MM/yyyy)
     *
     * @param localDate the date to convert
     * @return the Italian formatted date string
     * @throws NullPointerException if localDate is null
     */
    public static String toItalianString(LocalDate localDate) {
        if (localDate == null) {
            throw new NullPointerException("localDate cannot be null");
        }
        return ITALIAN_DATE_FORMATTER.format(localDate);
    }

    /**
     * Format now epoch to compact timestamp
     *
     * @return the formatted string
     */
     public static String getCompactTimestamp() {
        return COMPACT.format(LocalDateTime.now());
    }

    /**
     * Format a millisecond epoch to compact timestamp
     *
     * @param epochMillis the timestamp in milliseconds
     * @return the formatted string
     */
    public static String getCompactTimestamp(long epochMillis) {
        var dt = LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), ZoneId.systemDefault());
        return COMPACT.format(dt);
    }
}