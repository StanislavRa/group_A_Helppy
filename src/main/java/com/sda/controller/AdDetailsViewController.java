package com.sda.controller;

import com.sda.dao.implementation.AdvertisementDao;
import com.sda.entity.Advertisement;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * @author StanislavR
 */
public class AdDetailsViewController extends GeneralController {

    @FXML
    private Label subjectLabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private Label startDateLabel;

    @FXML
    private Label endDateLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label adTypeLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label userFullNameLabel;

    public void initData(Advertisement advertisement)  {
        subjectLabel.setText(advertisement.getSubject());
        categoryLabel.setText(String.valueOf(advertisement.getCategory()));
        startDateLabel.setText(advertisement.getStartDate().toString());
        endDateLabel.setText(advertisement.getEndDate().toString());
        addressLabel.setText(advertisement.getAddress().toString());
        adTypeLabel.setText(advertisement.getServiceType().toString());
        priceLabel.setText(advertisement.getPrice().toString()/*String.valueOf(advertisement.getPrice())*/);
        descriptionLabel.setText(advertisement.getDescription());
        userFullNameLabel.setText(advertisement.getCustomer().getFullName());
    }
}
