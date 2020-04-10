package com.sda.parser;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Parser {


    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {

        return java.sql.Date.valueOf(dateToConvert);
    }

    public boolean compareTwoStrings(String s1, String s2){

        return s1.toLowerCase().equals(s2.toLowerCase());
    }

    public boolean compareTwoBigDecimal (BigDecimal bigDecimalBottomRate,
                                         BigDecimal bigDecimalTopRate,
                                         BigDecimal advertisementPrice ){

        return advertisementPrice.compareTo(bigDecimalBottomRate)>=0
                && advertisementPrice.compareTo(bigDecimalTopRate)<=0;
    }
}
