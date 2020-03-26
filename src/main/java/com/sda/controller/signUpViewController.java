package com.sda.controller;

import com.sda.entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class signUpViewController extends GeneralController {

    @FXML
    private Button signInButton;

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField fullNameTextField;


    public signUpViewController(Customer customer) {
        super(customer);
    }

    public signUpViewController() {
    }

    @FXML
    void signUpButtonPushed(ActionEvent event) throws IOException {

        String loginText = userNameTextField.getText().trim();
        Customer customer = customerFromLogin(loginText);

        if (customer == null) {

            String loginPassword = passwordPasswordField.getText().trim();
            String fullName = fullNameTextField.getText().trim();

            Customer newCustomer = new Customer(loginText, loginPassword, fullName);

            customerDao.save(newCustomer);

            changeScreen(event, "/views/signInView.fxml");

        } else {
            System.out.println(loginText + " is in use");
        }
    }

    @FXML
    void passwordPasswordFieldPushed(ActionEvent event) {

    }

    @FXML
    void userNameTextFieldPushed(ActionEvent event) {

    }

    @FXML
    void signInButtonPushed(ActionEvent event) {

    }

    @FXML
    void fullNameTextFieldPushed(ActionEvent event) {

    }

    @Override
    public void logoTextMouseClicked(ActionEvent event) {

    }

    @Override
    public void myAdsButtonPushed(ActionEvent event) {

    }

    @Override
    public void allAdsButtonPushed(ActionEvent event) {

    }

    private Customer customerFromLogin(String loginText) {
        return customerDao.getByLogin(loginText);
    }
}
