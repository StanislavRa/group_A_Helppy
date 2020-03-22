package com.sda.controller;

import com.sda.dao.implementation.AddressDao;
import com.sda.dao.implementation.AdvertisementDao;
import com.sda.dao.implementation.CategoryDao;
import com.sda.dao.implementation.CustomerDao;
import com.sda.entity.Address;
import com.sda.entity.Advertisement;
import com.sda.entity.Category;
import com.sda.entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author StanislavR
 */
public abstract class GeneralController {

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
