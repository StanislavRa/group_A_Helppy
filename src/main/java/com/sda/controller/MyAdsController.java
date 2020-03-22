package com.sda.controller;

import com.sda.entity.Advertisement;
import com.sda.entity.Customer;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class MyAdsController implements Initializable {

    @FXML
    private Label fullNameLabel;
    @FXML
    private TableView<Advertisement> myAdsTable;
    @FXML
    private TableColumn<Advertisement, String> subjectTableColumn;
    @FXML
    private TableColumn<Advertisement, String> priceTableColumn;
    @FXML
    private TableColumn<Advertisement, String> stateTableColumn;
    @FXML
    private TableColumn<Advertisement, Date> startDateTableColumn;
    @FXML
    private TableColumn<Advertisement, Date> endDateTableColumn;

    private Customer customer;

    public MyAdsController(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fullNameLabel.setText(customer.getFullName());

        setUpTableColumns();

        myAdsTable.setItems(FXCollections.observableArrayList(customer.getUserAdvertisements()));

    }

    public void setUpTableColumns() {

        //set up the columns in the table
        subjectTableColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        stateTableColumn.setCellValueFactory(value ->
                new SimpleStringProperty(value.getValue().getServiceState().toString()));
        startDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
    }
}
