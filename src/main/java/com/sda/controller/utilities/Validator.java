package com.sda.controller.utilities;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;


public class Validator {

    public static boolean stringMatcherValidation(String checkText, String regExPattern, String message) {

        if (!checkText.matches(regExPattern)) {
            AlertBox.validation(message);
            return false;
        }
        return true;
    }

    public static boolean startDateIsBeforeEndDate(LocalDate startDate, LocalDate endDate, String message) {
        if (startDate.isAfter(endDate)) {
            AlertBox.validation(message);
            return false;
        }
        return true;
    }

    public static boolean dateIsNotPast(LocalDate date, String message) {
        // need only end date check, consider updating
        if (LocalDate.now().isAfter((date))) {
            AlertBox.validation(message);
            return false;
        }
        return true;
    }

    public static boolean isComboboxHasValue(ComboBox<String> comboBox, String message) {
        if (comboBox.getValue() == null) {
            AlertBox.validation(message);
            return false;
        }
        return true;
    }

    public static boolean isTextFieldEmpty(TextField textField, String message) {
        if (textField.getText().isEmpty()) {
            AlertBox.validation(message);
            return false;
        }
        return true;
    }

    public static boolean isDatePickerEmpty(DatePicker datePicker, String message) {
        if (datePicker.getValue() == null) {
            AlertBox.validation(message);
            return false;
        }
        return true;
    }
}
