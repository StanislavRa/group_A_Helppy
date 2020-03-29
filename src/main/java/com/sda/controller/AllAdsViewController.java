package com.sda.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * @author StanislavR
 */
public class AllAdsViewController extends GeneralController {

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


}
