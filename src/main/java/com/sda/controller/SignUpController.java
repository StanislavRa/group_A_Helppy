package com.sda.controller;

import com.sda.dao.implementation.CustomerDao;
import com.sda.entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField signUpFullNameTextField;

    @FXML
    void signUpButtonPushed(ActionEvent event) throws IOException {

        String loginText = loginTextField.getText().trim();
        Customer customer = customerFromLogin(loginText);

        if (customer == null) {

            String loginPassword = passwordTextField.getText().trim();
            String fullName = signUpFullNameTextField.getText().trim();

            Customer newCustomer = new Customer(loginText,loginPassword,fullName);

            CustomerDao customerDao = new CustomerDao();
            customerDao.save(newCustomer);

            changeScreen(event, "/views/signInView.fxml");

        } else {
            System.out.println(loginText + " is in use");
        }


    }

    public FXMLLoader changeScreen(ActionEvent event, String viewName) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

        return loader;
    }

    private Customer customerFromLogin(String loginText) {
        CustomerDao customerDao = new CustomerDao();
        return customerDao.getByLogin(loginText);
    }

}
