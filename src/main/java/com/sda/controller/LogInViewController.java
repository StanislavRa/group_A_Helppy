package com.sda.controller;

import com.sda.controller.utilities.Animation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static com.sda.controller.utilities.AlertBox.error;

public class LogInViewController extends GeneralController {

    @FXML
    protected TextField userNameTextField;
    @FXML
    protected PasswordField passwordPasswordField;
    @FXML
    protected Button signInButton;
    @FXML
    protected Button signUpNowButton;

    @FXML
    protected void signInButtonPushed(ActionEvent event) {

        String loginText = userNameTextField.getText().trim();
        String loginPassword = passwordPasswordField.getText().trim();

        if (!loginText.isEmpty() && !loginPassword.isEmpty()) {


            customer = customerDao.getByLoginAndPassword(loginText, loginPassword);

            if (customer != null) {

                MyAdsController controller = (changeScreen(event, "/views/myAdsView.fxml").getController());

                controller.setCustomer(customer);
                controller.initData();
            } else {
                error("Login name and/or password is incorrect!");
            }

        } else {

            Animation userLoginAnim = new Animation(userNameTextField);
            Animation userPasswordAnim = new Animation(passwordPasswordField);
            userLoginAnim.playAnim();
            userPasswordAnim.playAnim();
        }
    }

    @FXML
    protected void signUpNowButtonPushed(ActionEvent event) {
        changeScreen(event, "/views/signUpView.fxml");
    }
}

