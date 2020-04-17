package com.sda.controller.utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ValidatorTest {

//    Validator validator;
//
//    @Before
//    public void setUp() {
//        validator = new Validator();
//    }

    Validator validator = new Validator();

    @Test(expected = NullPointerException.class)
    public void positiveIsTextFieldEmpty() {
        TextField textField = null;
        String message = textField.getText();

        Assert.assertTrue(Validator.isTextFieldEmpty(textField, message));
    }
}
