package com.sda.controller;

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

import static com.sda.controller.utilities.AlertBox.*;
import static com.sda.controller.utilities.Parser.convertToDateViaSqlDate;
import static com.sda.controller.utilities.Parser.convertToLocalDateViaInstant;
import static com.sda.controller.utilities.Validator.*;

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
            validation("Make sure you fill all necessary data");
            return false;
        }
        return true;
    }

    protected boolean fieldsValidator() {

        return userInputNullCheck() &&
                startDateIsBeforeEndDate(
                        startDatePicker.getValue(), startDatePicker.getValue(),
                        "Make sure that End Date is after Start Date") &&
                dateIsNotPast(
                        endDatePicker.getValue(),
                        "Make sure that you don't choose End Date from past") &&
                stringMatcherValidation
                        (priceTextField.getText(), "[0-9]*['.']?[0-9]*",
                                "Make sure that price number format is xx.xx") &&
                checkTextLength(descriptionTextField.getText(),
                        1000,
                        "Make sure that description length is not longer than 1000 symbols") &&
                checkTextLength(subjectTextField.getText(),
                        100,
                        "Make sure that subject length is not longer than 100 symbols") &&
                checkTextLength(priceTextField.getText(),
                        100,
                        "Make sure that description length is not longer than 100 symbols");
    }

    protected Advertisement.ServiceType serviceTypeSelected() {
        return offerServiceRadioButton.isSelected() ?
                Advertisement.ServiceType.OFFER : Advertisement.ServiceType.REQUEST;
    }

    protected Advertisement getAdFromFields(Advertisement advertisement) {

        if (advertisement == null) {
            return null;
        }

        advertisement.setSubject(subjectTextField.getText());
        advertisement.setDescription(descriptionTextField.getText());
        advertisement.setPrice(new BigDecimal(priceTextField.getText()));
        advertisement.setStartDate(convertToDateViaSqlDate(startDatePicker.getValue()));
        advertisement.setEndDate(convertToDateViaSqlDate(endDatePicker.getValue()));
        advertisement.setServiceType(serviceTypeSelected());
        advertisement.setCategory(categoryDao.getByName(categoryComboBox.getValue()));
        advertisement.setAddress(new Address(countryComboBox.getValue(), cityComboBox.getValue()));

        return advertisement;
    }

    @FXML
    protected void createButtonPushed() {

        if (fieldsValidator()) {
            Advertisement advertisement = new Advertisement();
            advertisement.setCustomer(customer);
            adDao.save(getAdFromFields(advertisement));
            countryComboBox.getItems().clear();
            categoryComboBox.getItems().clear();
            updateCustomer();
            initData();
            success("New ad " + advertisement.getSubject() + " created");
            clearValues();
        }
    }

    @FXML
    protected void updateButtonPushed() {

        if (fieldsValidator()) {
            Advertisement shouldUpdateThisAd = adDao.get(getFromSelectedRow(mainTableView).getId());
            Advertisement newAd = getAdFromFields(shouldUpdateThisAd);
            adDao.update(newAd);
            countryComboBox.getItems().clear();
            categoryComboBox.getItems().clear();
            updateCustomer();
            initData();
            success(newAd.getSubject() + " ad updated");
            clearValues();
        }
    }

    @FXML
    protected void deleteButtonPushed() {

        ObservableList<Advertisement> allAds = getAllFromTable(mainTableView);
        ObservableList<Advertisement> selectedRows = getSelectedFromTable(mainTableView);

        for (Advertisement advertisement : selectedRows) {
            if (confirmation(advertisement.getSubject(), "Are you sure you want to delete")) {
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
                endDatePicker.setValue(convertToLocalDateViaInstant(getFromSelectedRow(mainTableView).getEndDate()));
                startDatePicker.setValue(convertToLocalDateViaInstant(getFromSelectedRow(mainTableView).getStartDate()));
                if (getFromSelectedRow(mainTableView).getServiceType() == Advertisement.ServiceType.OFFER) {
                    offerServiceRadioButton.setSelected(true);
                } else requestServiceRadioButton.setSelected(true);
            }
        });
    }
}
