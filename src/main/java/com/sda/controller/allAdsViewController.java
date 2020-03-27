package com.sda.controller;

import com.sda.entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * @author StanislavR
 */
public class allAdsViewController extends GeneralController {

    @FXML
    private ToggleGroup radioButtonToggleGroup;

    @FXML
    private RadioButton priceRadioButton;

    @FXML
    private TextField priceFromTextField;

    @FXML
    private TableColumn<?, ?> locationColumn;

    @FXML
    private RadioButton dateRadioButton;

    @FXML
    private TableColumn<?, ?> startDateColumn;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private Button findButton;

    @FXML
    private ComboBox<?> locationComboBox;

    @FXML
    private TableColumn<?, ?> categoryColumn;

    @FXML
    private TableColumn<?, ?> subjectColumn;

    @FXML
    private TableColumn<?, ?> endDateColumn;

    @FXML
    private ComboBox<?> categoryComboBox;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private RadioButton categoryRadioButton;

    @FXML
    private TableView<?> mainTableView;

    @FXML
    private TextField priceToTextField;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private RadioButton locationRadioButton;

    public allAdsViewController(Customer customer) {
        super(customer);
    }

    public allAdsViewController() {
    }

    public void myAdsButtonPushed(ActionEvent event) {

    }

    public void allAdsButtonPushed(ActionEvent event) {

    }

    public void logoTextMouseClicked(ActionEvent event) {

    }

    @FXML
    void categoryComboBoxPushed(ActionEvent event) {

    }

    @FXML
    void categoryRadioButtonPushed(ActionEvent event) {

    }

    @FXML
    void locationComboBoxPushed(ActionEvent event) {

    }

    @FXML
    void locationRadioButtonPushed(ActionEvent event) {

    }

    @FXML
    void priceFromTextFieldPushed(ActionEvent event) {

    }

    @FXML
    void priceToTextFieldPushed(ActionEvent event) {

    }

    @FXML
    void priceRadioButtonPushed(ActionEvent event) {

    }

    @FXML
    void startDatePickerPushed(ActionEvent event) {

    }

    @FXML
    void endDatePickerPushed(ActionEvent event) {

    }

    @FXML
    void dateRadioButtonPushed(ActionEvent event) {

    }

    @FXML
    void findButtonPushed(ActionEvent event) {

    }

}
