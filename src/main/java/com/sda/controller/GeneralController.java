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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author StanislavR
 */
public abstract class GeneralController {

    @FXML
    protected Text logoText;

    @FXML
    protected Button myAdsButton;

    @FXML
    protected Button allAdsButton;

    protected Customer customer;
    CustomerDao customerDao = new CustomerDao("hibernate.cfg.xml");

    public GeneralController(Customer customer) {
        this.customer = customer;
    }

    public GeneralController() {
    }

    protected FXMLLoader changeScreen(ActionEvent event, String viewName) throws IOException {

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

    protected FXMLLoader changeScreenWithController(ActionEvent event, String viewName, GeneralController controller) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));

        // Set it in the FXMLLoader
        loader.setController(controller);

        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();

        return loader;
    }

    @FXML
    public void logoTextMouseClicked(ActionEvent event) {

    }

    @FXML
    public void myAdsButtonPushed(ActionEvent event) {

    }

    @FXML
    public void allAdsButtonPushed(ActionEvent event) {

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
