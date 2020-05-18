package com.sda.controller;

import com.sda.dao.implementation.*;
import com.sda.entity.Advertisement;
import com.sda.entity.Customer;
import com.sda.controller.utilities.Parser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;



public abstract class GeneralController {

    protected Customer customer;
    protected Parser parser = new Parser();
    protected AdvertisementDao adDao = new AdvertisementDao();
    protected CustomerDao customerDao = new CustomerDao();
    protected CategoryDao categoryDao = new CategoryDao();
    protected CountryDao countryDao = new CountryDao();
    protected CityDao cityDao = new CityDao();

    protected FXMLLoader changeScreen(Event event, String viewName) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        try {
            Parent tableViewParent = loader.load();
            Scene tableViewScene = new Scene(tableViewParent);
            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();    // Use logger to print Throwable
        }
        return loader;
    }

    @FXML
    protected void allAdsButtonPushed(ActionEvent event) {

        AllAdsViewController allAdsViewController = changeScreen(event, "/views/allAdsView.fxml").getController();
        allAdsViewController.setCustomer(customer);
        allAdsViewController.setUpTableColumns();
    }

    @FXML
    protected void myAdsButtonPushed(ActionEvent event) {

        if (customer != null) {
            MyAdsController controller = (changeScreen(event, "/views/myAdsView.fxml").getController());
            controller.setCustomer(customer);
            controller.initData();
        } else {
            changeScreen(event, "/views/loginView.fxml");
        }
    }

    @FXML
    protected void logoTextOnMouseClicked(MouseEvent event) {
        HomeViewController homeViewController = changeScreen(event, "/views/homeView.fxml").getController();
        homeViewController.setCustomer(customer);
    }

    @FXML
    protected void logOutButtonPushed(javafx.event.ActionEvent actionEvent) {
        customer = null;
        changeScreen(actionEvent, "/views/logInView.fxml");
    }

    protected void updateCustomer() {
        setCustomer(customerDao.get(customer.getId()));
    }

    protected ObservableList<Advertisement> convertFromListToObservableList(List<Advertisement> list) {
        return FXCollections.observableArrayList(list);
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}