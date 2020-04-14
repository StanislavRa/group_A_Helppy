package com.sda.controller;

import com.sda.dao.implementation.*;
import com.sda.entity.Customer;
import com.sda.parser.Parser;
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

/**
 * @author StanislavR
 */
public abstract class GeneralController<T> {

    protected Customer customer;
    protected Parser parser = new Parser();
    private final String DB_SETTINGS = "hibernateCreate.cfg.xml";
    protected AdvertisementDao adDao = new AdvertisementDao(DB_SETTINGS);
    protected CustomerDao customerDao = new CustomerDao(DB_SETTINGS);
    protected CategoryDao categoryDao = new CategoryDao(DB_SETTINGS);
    protected CountryDao countryDao = new CountryDao(DB_SETTINGS);
    protected CityDao cityDao = new CityDao(DB_SETTINGS);

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
            e.printStackTrace();
        }

        return loader;
    }

    @FXML
    void allAdsButtonPushed(ActionEvent event) {

        AllAdsViewController allAdsViewController = changeScreen(event, "/views/allAdsView.fxml").getController();
        allAdsViewController.setCustomer(customer);
        allAdsViewController.setUpTableColumns();
    }

    @FXML
    void myAdsButtonPushed(ActionEvent event) {

        if (customer != null) {
            MyAdsController controller = (changeScreen(event, "/views/myAdsView.fxml").getController());
            controller.setCustomer(customer);
            controller.initData();
        } else {
            changeScreen(event, "/views/loginView.fxml");
        }
    }

    @FXML
    void logoTextOnMouseClicked(MouseEvent event) {
        HomeViewController homeViewController = changeScreen(event, "/views/homeView.fxml").getController();
        homeViewController.setCustomer(customer);

    }

    public void updateCustomer() {
        setCustomer(customerDao.get(customer.getId()));
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public ObservableList<T> convertFromListToObservableList(List<T> list) {
        return FXCollections.observableArrayList(list);
    }
}