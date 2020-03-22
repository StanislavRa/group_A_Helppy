package com.sda.controller;

import com.sda.controller.animation.Shake;
import com.sda.dao.implementation.CustomerDao;
import com.sda.entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController extends GeneralController implements Initializable {

    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button authSignInButton;
    @FXML
    private Button signUpButton;

    public LoginViewController(Customer customer) {
        super(customer);
    }

    public LoginViewController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private Customer customerFromLogin(String loginText) {
        CustomerDao customerDao = new CustomerDao();
        return customerDao.getByLogin(loginText);
    }


    public void signUpButtonPushed(ActionEvent event) throws IOException {
        changeScreen(event, "/views/signUpView.fxml");
    }

    public FXMLLoader authSignInButtonPushed(ActionEvent event) throws IOException {

        String loginText = loginTextField.getText().trim();
        String loginPassword = passwordTextField.getText().trim();

        Customer customer = customerFromLogin(loginText);


        if (customer != null && loginPassword.equals(customer.getPassword())) {

            // Create a controller instance
            GeneralController controller = new MyAdsController(customer);
            return changeScreenWithController(event, "/views/myAdsView.fxml", controller);

        } else {
            Shake userLoginAnim = new Shake(loginTextField);
            Shake userPasswordAnim = new Shake(passwordTextField);
            userLoginAnim.playAnim();
            userPasswordAnim.playAnim();
        }
        return null;
    }


}

