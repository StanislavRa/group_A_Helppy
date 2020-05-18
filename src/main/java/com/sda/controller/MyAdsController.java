package com.sda.controller;

import com.sda.controller.utilities.AlertBox;
import com.sda.controller.utilities.Validator;
import com.sda.entity.Address;
import com.sda.entity.Advertisement;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MyAdsController extends TableSetUp implements Initializable {

    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private TextField priceTextField;
    @FXML
    private ComboBox<String> countryComboBox;
    @FXML
    private ComboBox<String> cityComboBox;
    @FXML
    private RadioButton offerServiceRadioButton;
    @FXML
    private RadioButton requestServiceRadioButton;
    @FXML
    private TextField subjectTextField;
    @FXML
    private TextArea descriptionTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setUpTableColumns();
        populateFieldsFromSelectedRow();
    }

    protected void initData() {

        mainTableView.setItems(getAdsFromCustomer(customer));
        countryComboBox.getItems().addAll(countryDao.getAllCountriesNamesList(countryDao.getAll()));
        categoryComboBox.getItems().addAll((categoryDao.getAllCategoryNames()));
        mainTableView.refresh();
    }

    @FXML
    protected void countryComboBoxSelected(javafx.event.ActionEvent event) {
        cityComboBox.getItems().clear();
        List<String> listOfCityNames = cityDao.getAllCityNamesByCountry(countryComboBox.getValue());

        cityComboBox.getItems().addAll(listOfCityNames);
    }

    protected boolean userInputNullCheck() {
        if (
                categoryComboBox.getSelectionModel().isEmpty() ||
                        startDatePicker.getValue() == null ||
                        endDatePicker.getValue() == null ||
                        priceTextField.getText().isEmpty() ||
                        countryComboBox.getSelectionModel().isEmpty() ||
                        cityComboBox.getSelectionModel().isEmpty() ||
                        subjectTextField.getText().isEmpty() ||
                        descriptionTextField.getText().isEmpty() ||
                                (offerServiceRadioButton == null && requestServiceRadioButton == null)
        ) {
            AlertBox.validation("Make sure you fill all necessary data");
            return false;
        }
        return true;
    }

    protected boolean fieldsValidator() {

        return userInputNullCheck() &&
                Validator.startDateIsBeforeEndDate(
                        startDatePicker.getValue(), startDatePicker.getValue(),
                        "Make sure that End Date is after Start Date") &&
                Validator.dateIsNotPast(
                        endDatePicker.getValue(),
                        "Make sure that you don't choose End Date from past") &&
                Validator.stringMatcherValidation
                        (priceTextField.getText(), "[0-9]*['.']?[0-9]*",
                                "Make sure that price number format is xx.xx") &&
                Validator.checkTextLength(descriptionTextField.getText(),
                        1000,
                        "Make sure that description length is not longer than 1000 symbols") &&
                Validator.checkTextLength(subjectTextField.getText(),
                        100,
                        "Make sure that subject length is not longer than 100 symbols") &&
                Validator.checkTextLength(priceTextField.getText(),
                        100,
                        "Make sure that description length is not longer than 100 symbols");
    }

    protected Advertisement.ServiceType serviceTypeSelected() {
        return offerServiceRadioButton.isSelected() ?
                Advertisement.ServiceType.OFFER : Advertisement.ServiceType.REQUEST;
    }

    protected Advertisement getAdFromFields() {

        return new Advertisement(
                subjectTextField.getText(),
                descriptionTextField.getText(),
                priceTextField.getText(),
                parser.convertToDateViaSqlDate(startDatePicker.getValue()),
                parser.convertToDateViaSqlDate(endDatePicker.getValue()),
                serviceTypeSelected(),
                categoryDao.getByName(categoryComboBox.getValue()),
                customer,
                new Address(countryComboBox.getValue(), cityComboBox.getValue()));
    }

    protected Advertisement changeAdFromFields(Advertisement advertisement) {

        advertisement.setSubject(subjectTextField.getText());
        advertisement.setDescription(descriptionTextField.getText());
        // How you store String inside BigDecimal???
        advertisement.setPrice(BigDecimal.valueOf(Long.parseLong(priceTextField.getText())));
        advertisement.setStartDate(parser.convertToDateViaSqlDate(startDatePicker.getValue()));
        advertisement.setEndDate(parser.convertToDateViaSqlDate(endDatePicker.getValue()));
        advertisement.setServiceType(serviceTypeSelected());
        advertisement.setCategory(categoryDao.getByName(categoryComboBox.getValue()));
        advertisement.setAddress(new Address(countryComboBox.getValue(), cityComboBox.getValue()));

        return advertisement;
    }

    @FXML
    protected void createButtonPushed() {

        if (fieldsValidator()) {

            Advertisement advertisement = getAdFromFields();
            adDao.save(advertisement);
            updateCustomer();
            initData();
            AlertBox.success("New ad " + advertisement.getSubject() + " created");
            clearValues();
        }
    }

    @FXML
    protected void updateButtonPushed() {

        if (fieldsValidator()) {

            Advertisement shouldUpdateThisAd = adDao.get(getFromSelectedRow(mainTableView).getId());
            Advertisement newAd = changeAdFromFields(shouldUpdateThisAd);
            adDao.update(newAd);
            updateCustomer();
            initData();
            AlertBox.success(newAd.getSubject() + " ad updated");
            clearValues();
        }
    }

    @FXML
    protected void deleteButtonPushed() {

        ObservableList<Advertisement> allAds = getAllFromTable(mainTableView);
        ObservableList<Advertisement> selectedRows = getSelectedFromTable(mainTableView);

        for (Advertisement advertisement : selectedRows) {

            if (AlertBox.confirmation(advertisement.getSubject(), "Are you sure you want to delete")) {

                allAds.remove(advertisement);
                Advertisement shouldDeleteThisAd = adDao.get(advertisement.getId());
                adDao.delete(shouldDeleteThisAd);
                updateCustomer();
            }
        }
    }

    @FXML
    protected void clearValues() {

        categoryComboBox.getSelectionModel().clearSelection();
        startDatePicker.getEditor().clear();
        endDatePicker.getEditor().clear();
        priceTextField.clear();
        countryComboBox.getSelectionModel().clearSelection();
        offerServiceRadioButton.setSelected(false);
        requestServiceRadioButton.setSelected(false);
        subjectTextField.clear();
        descriptionTextField.clear();
    }

    protected void populateFieldsFromSelectedRow() {

        mainTableView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {

                subjectTextField.setText(getFromSelectedRow(mainTableView).getSubject());
                descriptionTextField.setText(getFromSelectedRow(mainTableView).getDescription());
                priceTextField.setText(getFromSelectedRow(mainTableView).getPrice().toString());
                countryComboBox.setValue(getFromSelectedRow(mainTableView).getAddress().getCountry());
                cityComboBox.setValue(getFromSelectedRow(mainTableView).getAddress().getCity());
                categoryComboBox.setValue(getFromSelectedRow(mainTableView).getCategory().getName());
                endDatePicker.setValue(parser.convertToLocalDateViaInstant(getFromSelectedRow(mainTableView).getEndDate()));
                startDatePicker.setValue(parser.convertToLocalDateViaInstant(getFromSelectedRow(mainTableView).getStartDate()));
                if (getFromSelectedRow(mainTableView).getServiceType() == Advertisement.ServiceType.OFFER) {
                    offerServiceRadioButton.setSelected(true);
                } else requestServiceRadioButton.setSelected(true);
            }
        });
    }
}
