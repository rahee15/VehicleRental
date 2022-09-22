package com.rahi.VehicalRental.constant;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DataFormat {
    public static final String DASHED_YYYY_MM_DD_T_HH_MM_SS_SSSX_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
    public static final String DASHED_YYYY_MM_DD_T_HH_MM_SS_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DASHED_DD_MM_YYYY_HH_MM_SS = "dd-MM-yyyy HH:mm:ss";

    public static final String DASHED_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String DASHED_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String REDIRECTION_DATE_PATTERN = "ddMMyyyyHHmmsss";
    public static final String MMMM_YYYY = "MMMM yyyy";
    public static final String YYYY_MM_DD = "yyyyMMdd";
}

