package com.sda.controller;

import com.sda.entity.Advertisement;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        categoryLabel.setText(advertisement.getCategory().getName());
        startDateLabel.setText(dateParser(advertisement.getStartDate()));
        endDateLabel.setText(dateParser(advertisement.getEndDate()));
        addressLabel.setText(advertisement.getAddress().getCity());
        adTypeLabel.setText(advertisement.getServiceType().toString());
        priceLabel.setText(advertisement.getPrice().toString()/*String.valueOf(advertisement.getPrice())*/);
        descriptionLabel.setText(advertisement.getDescription());
        userFullNameLabel.setText(advertisement.getCustomer().getFullName());
    }

    public String dateParser(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        String formattedDate = formatter.format(date);
        return formattedDate;
    }
}
