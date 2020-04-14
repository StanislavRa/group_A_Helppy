package com.sda.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class ParserTest {

        Parser parser;

        @Before
        public void setUp() {
                parser = new Parser();
        }


        @Test
        public void shouldConvertToDateViaSqlDate() throws ParseException {

                String startDateString2 = "11/01/2019";

                LocalDate localDateTest = LocalDate.of(2019, 01, 11);

                Date dateTest1 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString2);
                Date dateTest2 = parser.convertToDateViaSqlDate(localDateTest);

                Assert.assertEquals(dateTest1, dateTest2);

        }

        @Test
        public void shouldCompareTwoStrings(){
                String s1 = "NewString";
                String s2 = "NEWSTRING";

                Assert.assertTrue(parser.compareTwoStrings(s1, s2));
        }

        @Test
        public void shouldCompareTwoBigDecimal(){
                BigDecimal bigDecimalBottomRate = new BigDecimal(100);
                BigDecimal bigDecimalTopRate = new BigDecimal(200);
                BigDecimal bigDecimalTest = new BigDecimal(150);

                Assert.assertTrue(parser.compareTwoBigDecimal(bigDecimalBottomRate, bigDecimalTopRate, bigDecimalTest));
        }

        @Test
        public void shouldConvertToLocalDateViaInstant() throws ParseException {

                String startDateString2 = "11/01/2019";
                Date dateTest = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString2);

                LocalDate localDateTest1 = LocalDate.of(2019, 01, 11);
                LocalDate localDateTest2 = parser.convertToLocalDateViaInstant(dateTest);

                Assert.assertEquals(localDateTest1, localDateTest2);
        }
}
