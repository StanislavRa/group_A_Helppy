package com.sda.controller;

import com.sda.controller.utilities.AlertBox;
import com.sda.controller.utilities.Validator;
import com.sda.entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

                AlertBox.success("Success!");

                changeScreen(event, "/views/logInView.fxml");

            } else {
                AlertBox.error(loginText + " login name is in use");
            }
        }

    }

    @FXML
    protected void signInButtonPushed(ActionEvent event) {
        changeScreen(event, "/views/loginView.fxml");
    }

    private boolean checkFieldsLength() {

        return Validator.checkTextLength(userNameTextField.getText(),
                60,
                "Make sure that your login is not longer than 60 symbols") &&
                Validator.checkTextLength(passwordPasswordField.getText(),
                        60,
                        "Make sure that your password is not longer than 60 symbols") &&
                Validator.checkTextLength(fullNameTextField.getText(),
                        60,
                        "Make sure that your full name is not longer than 60 symbols");

    }
}
