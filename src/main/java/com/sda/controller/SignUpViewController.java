package com.sda.controller;

import com.sda.dao.implementation.CustomerDao;
import com.sda.entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    void signUpButtonPushed(ActionEvent event) {

        String loginText = userNameTextField.getText().trim();
        CustomerDao customerDao = new CustomerDao("hibernate.cfg.xml");
        Customer customer = customerDao.getByLogin(loginText);

        if (customer == null) {

            String loginPassword = passwordPasswordField.getText().trim();
            String fullName = fullNameTextField.getText().trim();

            Customer newCustomer = new Customer(loginText, loginPassword, fullName);

            customerDao.save(newCustomer);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Success!");

            alert.showAndWait();

            changeScreen(event, "/views/logInView.fxml");

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText(loginText + " login name is in use");
            alert.showAndWait();
        }
    }

    @FXML
    void signInButtonPushed(ActionEvent event) {
        changeScreen(event, "/views/loginView.fxml");
    }
}
