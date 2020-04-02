package com.sda.controller;

import com.sda.controller.animation.Shake;
import com.sda.dao.implementation.CustomerDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    private void signInButtonPushed(ActionEvent event) {

        String loginText = userNameTextField.getText().trim();
        String loginPassword = passwordPasswordField.getText().trim();

        if (!loginText.isEmpty() && !loginPassword.isEmpty()) {

            CustomerDao customerDao = new CustomerDao("hibernateDemi.cfg.xml");
            customer = customerDao.getByLoginAndPassword(loginText, loginPassword);

            if (customer != null) {

                MyAdsController controller = (changeScreen(event, "/views/myAdsView.fxml").getController());

                controller.setCustomer(customer);
                controller.initData();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Login name and/or password is incorrect!");
                alert.showAndWait();
            }

        } else {

            Shake userLoginAnim = new Shake(userNameTextField);
            Shake userPasswordAnim = new Shake(passwordPasswordField);
            userLoginAnim.playAnim();
            userPasswordAnim.playAnim();
        }
    }

    @FXML
    void signUpNowButtonPushed(ActionEvent event) {
        changeScreen(event, "/views/signUpView.fxml");
    }
}

