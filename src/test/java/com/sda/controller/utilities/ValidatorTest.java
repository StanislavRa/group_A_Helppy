package com.sda.controller.utilities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.control.TextField;
import java.time.DateTimeException;
import java.time.LocalDate;

public class ValidatorTest {

    Validator validator;

    @Before
    public void setUp() {
        validator = new Validator();
    }


    @Test
    public void endDateShouldNotBeLessThanStartDate() throws DateTimeException {
        LocalDate startDate = LocalDate.of(2019, 10, 8);
        LocalDate endDate = LocalDate.of(2019, 11, 6);
        String message = "testing date validator";
        boolean result = Validator.startDateIsBeforeEndDate(startDate, endDate, message);

        Assert.assertTrue(result);
    }

    @Test
    public void dateShouldNotBePast() throws DateTimeException {
        LocalDate startDate = LocalDate.of(2019, 10, 8);
        LocalDate endDate = LocalDate.of(2019, 11, 6);
        String message = "testing date validator";
        boolean result1 = Validator.dateIsNotPast(startDate, message);
        boolean result2 = Validator.dateIsNotPast(endDate, message);

        Assert.assertTrue(result1);
        Assert.assertTrue(result2);

    }

    @Test(expected = NullPointerException.class)
    public void textFieldShouldBeEmpty() {
        TextField textField = null;
        String message = "testing Test Field validator";

        Assert.assertTrue(Validator.isTextFieldEmpty(textField, message));

    }
}
