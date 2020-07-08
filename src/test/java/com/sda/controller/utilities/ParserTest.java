package com.sda.controller.utilities;

import com.sda.entity.Advertisement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static com.sda.controller.utilities.Parser.*;
import static com.sda.util.Constants.DATE_FORMAT;


public class ParserTest {

    @Test
    public void shouldConvertToDateViaSqlDate() throws ParseException {

        String startDateString2 = "11/01/2019";

        LocalDate localDateTest = LocalDate.of(2019, 1, 11);

        Date dateTest1 = new SimpleDateFormat(DATE_FORMAT).parse(startDateString2);
        Date dateTest2 = convertToDateViaSqlDate(localDateTest);

        Assert.assertEquals(dateTest1, dateTest2);
    }

    @Test
    public void shouldCompareTwoStrings(){
        String s1 = "NewString";
        String s2 = "NEWSTRING";

        Assert.assertTrue(compareTwoStrings(s1, s2));
    }

    @Test
    public void shouldCompareTwoBigDecimal(){
        BigDecimal bigDecimalBottomRate = new BigDecimal(100);
        BigDecimal bigDecimalTopRate = new BigDecimal(200);
        BigDecimal bigDecimalTest = new BigDecimal(150);

        Assert.assertTrue(compareTwoBigDecimal(bigDecimalBottomRate, bigDecimalTopRate, bigDecimalTest));
    }

    @Test
    public void shouldConvertToLocalDateViaInstant() throws ParseException {

        String startDateString2 = "11/01/2019";
        Date dateTest = new SimpleDateFormat(DATE_FORMAT).parse(startDateString2);

        LocalDate localDateTest1 = LocalDate.of(2019, 1, 11);
        LocalDate localDateTest2 = convertToLocalDateViaInstant(dateTest);

        Assert.assertEquals(localDateTest1, localDateTest2);
    }

    @Test
    public void shouldConvertSQLDateToLocalDate() throws ParseException {

        LocalDate localDateTest1 = LocalDate.of(2013, 2, 1);

        String startDate="01-02-2013";
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = sdf1.parse(startDate);
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());

        LocalDate localDateTest2 = convertSQLDateToLocalDate(sqlStartDate);

        Assert.assertEquals(localDateTest1, localDateTest2);
    }

    @Test
    public void shouldDoDateParser() throws ParseException {
        String startDateString2 = "11/11/2019";
        Date dateTest = new SimpleDateFormat(DATE_FORMAT).parse(startDateString2);
        String formattedDateTest =  "2019-11-11";
        String dateFromParser = dateParser(dateTest);

        Assert.assertEquals(formattedDateTest, dateFromParser);
    }

    @Test
    public void shouldGetNames() {
               String[] gottenNamesFromAdvertisement = getNames(Advertisement.ServiceType.class);
               String offer = "OFFER";
               String request = "REQUEST";
               Assert.assertEquals(gottenNamesFromAdvertisement[0], offer);
               Assert.assertEquals(gottenNamesFromAdvertisement[1], request);
        }
}
