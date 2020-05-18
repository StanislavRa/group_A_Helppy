package com.sda.controller.utilities;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;

public class Parser {


    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {

        return java.sql.Date.valueOf(dateToConvert);
    }


    public boolean compareTwoStrings(String s1, String s2){
        /* Case insensitive string comparisons should be made without intermediate upper or lower casing.
        Using toLowerCase() or toUpperCase() to make case insensitive comparisons is inefficient
        because it requires the creation of temporary, intermediate String objects.*/
        return s1.equalsIgnoreCase(s2);
    }

    public boolean compareTwoBigDecimal (BigDecimal bigDecimalBottomRate,
                                         BigDecimal bigDecimalTopRate,
                                         BigDecimal advertisementPrice ){

        return advertisementPrice.compareTo(bigDecimalBottomRate)>=0
                && advertisementPrice.compareTo(bigDecimalTopRate)<=0;
    }

    public LocalDate convertToLocalDateViaInstant(java.util.Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public LocalDate convertSQLDateToLocalDate(java.sql.Date date) {
        return date.toLocalDate();
    }

    public String dateParser(java.util.Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    public String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
