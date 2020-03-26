package com.sda.controller;

import com.sda.controller.animation.Shake;
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

public class logInViewController extends GeneralController implements Initializable {

    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private Button signInButton;
    @FXML
    private Button signUpNowButton;


    public logInViewController(Customer customer) {
        super(customer);
    }

    public logInViewController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private Customer customerFromLogin(String loginText) {
        return customerDao.getByLogin(loginText);
    }


//    public void signUpButtonPushed(ActionEvent event) throws IOException {
//        changeScreen(event, "/views/signUpView.fxml");
//    }
//
//    public FXMLLoader authSignInButtonPushed(ActionEvent event) throws IOException {
//
//        String loginText = userNameTextField.getText().trim();
//        String loginPassword = passwordPasswordField.getText().trim();
//
//        Customer customer = customerFromLogin(loginText);
//
//
//        if (customer != null && loginPassword.equals(customer.getPassword())) {
//
//            // Create a controller instance
//            GeneralController controller = new MyAdsController(customer);
//            return changeScreenWithController(event, "/views/myAdsView.fxml", controller);
//
//        } else {
//            Shake userLoginAnim = new Shake(userNameTextField);
//            Shake userPasswordAnim = new Shake(passwordPasswordField);
//            userLoginAnim.playAnim();
//            userPasswordAnim.playAnim();
//        }
//        return null;
//    }

public void myAdsButtonPushed(ActionEvent event) {

        }

public void allAdsButtonPushed(ActionEvent event) {

        }

public void logoTextMouseClicked(ActionEvent event) {

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
    void signUpNowButtonPushed(ActionEvent event) {

            }
}

