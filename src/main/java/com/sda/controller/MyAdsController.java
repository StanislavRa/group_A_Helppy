package com.sda.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import com.sda.entity.Advertisement;
import com.sda.entity.Customer;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class MyAdsController extends GeneralController implements Initializable {





        @FXML
        private Text logoText;

        @FXML
        private Button allAdsButton;

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
        private Button detailsButtonClicked;

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
        void addService(MouseEvent event) {

        }

        @FXML
        void allAdsButtonPushed(ActionEvent event) {

        }

        @FXML
        void categoryComboBoxPushed(ActionEvent event) {

        }

        @FXML
        void chooseOfferType(MouseEvent event) {

        }

        @FXML
        void chooseRequestType(MouseEvent event) {

        }

        @FXML
        void createButtonClicked(ActionEvent event) {

        }

        @FXML
        void deleteButtonClicked(ActionEvent event) {

        }

        @FXML
        void deleteService(MouseEvent event) {

        }

        @FXML
        void detailsButtonClicked(ActionEvent event) {

        }

        @FXML
        void endDatePickerPushed(ActionEvent event) {

        }

        @FXML
        void locationComboBoxPushed(ActionEvent event) {

        }

        @FXML
        void logoTextMouseClicked(MouseEvent event) {

        }

        @FXML
        void offerServiceRadioButtonClicked(ActionEvent event) {

        }

        @FXML
        void priceTextFieldTyped(KeyEvent event) {

        }

        @FXML
        void requestServiceRadioButtonClicked(ActionEvent event) {

        }

        @FXML
        void startDatePickerPushed(ActionEvent event) {

        }

        @FXML
        void updateButtonClicked(ActionEvent event) {

        }

        @FXML
        void updateService(MouseEvent event) {

        }

        @Override
        public void initialize(URL location, ResourceBundle resources) {

        }
}

