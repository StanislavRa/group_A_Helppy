package com.sda.controller;

import com.sda.dao.implementation.CustomerDao;
import com.sda.entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignUpController extends GeneralController {

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField signUpFullNameTextField;


    public SignUpController(Customer customer) {
        super(customer);
    }

    public SignUpController() {
    }

    @FXML
    void signUpButtonPushed(ActionEvent event) throws IOException {

        String loginText = loginTextField.getText().trim();
        Customer customer = customerFromLogin(loginText);

        if (customer == null) {

            String loginPassword = passwordTextField.getText().trim();
            String fullName = signUpFullNameTextField.getText().trim();

            Customer newCustomer = new Customer(loginText, loginPassword, fullName);

            customerDao.save(newCustomer);

            changeScreen(event, "/views/signInView.fxml");

        } else {
            System.out.println(loginText + " is in use");
        }
    }

    private Customer customerFromLogin(String loginText) {
        return customerDao.getByLogin(loginText);
    }
}
