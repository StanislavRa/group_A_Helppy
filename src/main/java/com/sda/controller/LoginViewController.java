package com.sda.controller;

import com.sda.controller.animation.Shake;
import com.sda.dao.implementation.CustomerDao;
import com.sda.entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {

    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button authSignInButton;
    @FXML
    private Button signUpButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private Customer customerFromLogin(String loginText) {
        CustomerDao customerDao = new CustomerDao();
        return customerDao.getByLogin(loginText);
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

    public void signUpButtonPushed(ActionEvent event) throws IOException {
        changeScreen(event, "/views/signUpView.fxml");
    }

    public FXMLLoader authSignInButtonPushed(ActionEvent event) throws IOException {

        String loginText = loginTextField.getText().trim();
        String loginPassword = passwordTextField.getText().trim();

        Customer customer = customerFromLogin(loginText);

        if (customer != null && loginPassword.equals(customer.getPassword())) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/myAdsView.fxml"));

            // Create a controller instance
            MyAdsController controller =
                    new MyAdsController(customer);
            // Set it in the FXMLLoader
            loader.setController(controller);

            Parent tableViewParent = loader.load();

            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();

            return loader;

        } else {
            Shake userLoginAnim = new Shake(loginTextField);
            Shake userPasswordAnim = new Shake(passwordTextField);
            userLoginAnim.playAnim();
            userPasswordAnim.playAnim();
        }
        return null;
    }
}
