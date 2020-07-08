package com.sda.controller;

import com.sda.entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static com.sda.controller.utilities.AlertBox.error;
import static com.sda.controller.utilities.AlertBox.success;
import static com.sda.controller.utilities.Validator.checkTextLength;

public class SignUpViewController extends GeneralController {

    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private TextField fullNameTextField;

    @FXML
    protected void signUpButtonPushed(ActionEvent event) {

        if (checkFieldsLength()) {
            String loginText = userNameTextField.getText().trim();
            Customer customer = customerDao.getByLogin(loginText);

            if (customer == null) {
                String loginPassword = passwordPasswordField.getText().trim();
                String fullName = fullNameTextField.getText().trim();
                Customer newCustomer = new Customer(loginText, loginPassword, fullName);
                customerDao.save(newCustomer);
                success("Success!");
                changeScreen(event, "/views/logInView.fxml");

            } else {
                error(loginText + " login name is in use");
            }
        }

    }

    @FXML
    protected void signInButtonPushed(ActionEvent event) {
        changeScreen(event, "/views/loginView.fxml");
    }

    private boolean checkFieldsLength() {

        return checkTextLength(userNameTextField.getText(),
                60,
                "Make sure that your login is not longer than 60 symbols") &&
                checkTextLength(passwordPasswordField.getText(),
                        60,
                        "Make sure that your password is not longer than 60 symbols") &&
                checkTextLength(fullNameTextField.getText(),
                        60,
                        "Make sure that your full name is not longer than 60 symbols");

    }
}
