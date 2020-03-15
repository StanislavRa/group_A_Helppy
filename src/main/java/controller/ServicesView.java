package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ServicesView {

    @FXML
    private CheckBox categoryCheckBox;

    @FXML
    private CheckBox locationCheckBox;

    @FXML
    private TextField priceFromTextField;

    @FXML
    private TableColumn<?, ?> locationColumn;

    @FXML
    private TableColumn<?, ?> categoryColumn;

    @FXML
    private TableColumn<?, ?> subjectColumn;

    @FXML
    private Button seeServicesButton;

    @FXML
    private TableView<?> mainTableView;

    @FXML
    private Button addServicesButton;

    @FXML
    private Button logInButton;

    @FXML
    private TextField priceToTextField;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private TableColumn<?, ?> descriptionColumn;

    @FXML
    void addServicesButtonPushed(ActionEvent event) {

    }

    @FXML
    void seeServicesButtonPushed(ActionEvent event) {

    }

    @FXML
    void logInButtonPushed(ActionEvent event) {

    }

    @FXML
    void categoryCheckBoxPushed(ActionEvent event) {

    }

    @FXML
    void locationCheckBoxPushed(ActionEvent event) {

    }

    @FXML
    void priceFromTextFieldPushed(ActionEvent event) {

    }

    @FXML
    void priceToTextFieldPushed(ActionEvent event) {

    }

}
