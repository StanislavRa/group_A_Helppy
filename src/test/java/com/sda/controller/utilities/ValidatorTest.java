package com.sda.controller.utilities;


import javafx.embed.swing.JFXPanel;
import javafx.scene.control.TextField;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.time.DateTimeException;
import java.time.LocalDate;


public class ValidatorTest {

    @Before
    public void setUp() {
        new JFXPanel();
    }

    @Test
    public void endDateShouldNotBeLessThanStartDate() throws DateTimeException {

        LocalDate startDate = LocalDate.of(2021, 10, 8);
        LocalDate endDate = LocalDate.of(2019, 12, 6);
        String message = "testing date validator";
        boolean result = Validator.startDateIsBeforeEndDate(startDate, endDate, message);

        Assert.assertFalse(result);
    }

    @Test

    public void dateShouldNotBePast() throws DateTimeException {

        LocalDate startDate = LocalDate.of(2019, 10, 8);
        LocalDate endDate = LocalDate.of(2032, 11, 6);
        String message = "testing date validator";

        boolean result1 = Validator.dateIsNotPast(startDate, message);
        boolean result2 = Validator.dateIsNotPast(endDate, message);

        Assert.assertFalse(result1);
        Assert.assertTrue(result2);
    }

    @Test
    public void positiveIsTextFieldEmpty() {

        TextField textField = new TextField("");
        String message = "Something wrong";
        System.out.println(textField.getText());

        Assert.assertFalse(Validator.isTextFieldEmpty(textField, message));
    }
}