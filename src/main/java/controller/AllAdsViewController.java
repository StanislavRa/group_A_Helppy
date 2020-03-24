package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AllAdsViewController {

    @FXML
    private Text logoText;

    @FXML
    private Button myAdsButton;

    @FXML
    private Button allAdsButton;

    @FXML
    private ComboBox<?> categoryComboBox;

    @FXML
    private ComboBox<?> locationComboBox;

    @FXML
    private TextField priceFromTextField;

    @FXML
    private TextField priceToTextField;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TableView<?> mainTableView;

    @FXML
    private TableColumn<?, ?> subjectColumn;

    @FXML
    private TableColumn<?, ?> categoryColumn;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private TableColumn<?, ?> locationColumn;

    @FXML
    private TableColumn<?, ?> startDateColumn;

    @FXML
    private TableColumn<?, ?> endDateColumn;

    @FXML
    void logoTextPushed(ActionEvent event) {

    }

    @FXML
    void myAdsButtonPushed(ActionEvent event) {

    }

    @FXML
    void allAdsButtonPushed(ActionEvent event) {

    }

    @FXML
    void categoryComboBoxPushed(ActionEvent event) {

    }

    @FXML
    void locationComboBoxPushed(ActionEvent event) {

    }

    @FXML
    void priceFromTextFieldPushed(ActionEvent event) {

    }

    @FXML
    void priceToTextFieldPushed(ActionEvent event) {

    }

    @FXML
    void startDatePickerPushed(ActionEvent event) {

    }

    @FXML
    void endDatePickerPushed(ActionEvent event) {

    }
}