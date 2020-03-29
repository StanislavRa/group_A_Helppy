package com.sda.controller;

import com.sda.entity.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MyAdsController extends GeneralController implements Initializable {

    @FXML
    private ComboBox<?> categoryComboBox;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private TextField priceTextField;
    @FXML
    private ComboBox<?> locationComboBox;
    @FXML
    private RadioButton offerServiceRadioButton;
    @FXML
    private ToggleGroup serviceType;
    @FXML
    private RadioButton requestServiceRadioButton;
    @FXML
    private TextField subjectTextField;
    @FXML
    private TextArea descriptionTextField;
    @FXML
    private Button createButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button detailsButton;
    @FXML
    private TableView<?> serviceTable;
    @FXML
    private TableColumn<?, ?> categoryTableColumn;
    @FXML
    private TableColumn<?, ?> startDateTableColumn;
    @FXML
    private TableColumn<?, ?> endDateTableColumn;
    @FXML
    private TableColumn<?, ?> priceDateTableColumn;
    @FXML
    private TableColumn<?, ?> addressDateTableColumn;
    @FXML
    private TableColumn<?, ?> serviceTypeDateTableColumn;
    @FXML
    private TableColumn<?, ?> subjectDateTableColumn;
    @FXML
    private Label fullNameLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    void createButtonClicked() {

    }

    @FXML
    void updateButtonClicked() {

    }

    @FXML
    void deleteButtonClicked() {

    }

    @FXML
    void detailsButtonClicked() {

    }

    public void initData() {
        fullNameLabel.setText(getCustomer().getFullName());
        System.out.println(getCustomer().getFullName());
    }


    public Customer getCustomer() {
        return customer;
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

