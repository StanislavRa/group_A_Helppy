package com.sda.controller;

import com.sda.controller.GeneralController;
import com.sda.entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * @author StanislavR
 */
public class adDetailsViewController extends GeneralController {

    @FXML
    private Text logoText;

    @FXML
    private Button allAdsButton;

    @FXML
    private Label startDateLabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private Label endDateLabel;

    @FXML
    private Button myAdsButton;

    @FXML
    private Label addressLabel;

    @FXML
    private Label subjectLabel;

    @FXML
    private Label adTypeLabel;

    @FXML
    private Label userFullNameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label descriptionLabel;

    public adDetailsViewController(Customer customer) {
        super(customer);
    }

    public adDetailsViewController() {
    }

    @FXML
    public void logoTextMouseClicked(ActionEvent event) {
    }

    @FXML
    public void myAdsButtonPushed(ActionEvent event) {

    }

    @FXML
    public void allAdsButtonPushed(ActionEvent event) {

    }

}
