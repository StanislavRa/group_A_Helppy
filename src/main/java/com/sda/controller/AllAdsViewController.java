package com.sda.controller;

import com.sda.dao.implementation.AdvertisementDao;
import com.sda.entity.Advertisement;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static com.sda.controller.utilities.Parser.*;
import static com.sda.controller.utilities.Validator.*;

public class AllAdsViewController extends TableSetUp implements Initializable {

    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private RadioButton categoryRadioButton;
    @FXML
    private ComboBox<String> countryComboBox;
    @FXML
    private RadioButton countryRadioButton;
    @FXML
    private ComboBox<String> cityComboBox;
    @FXML
    private RadioButton cityRadioButton;
    @FXML
    private TextField priceFromTextField;
    @FXML
    private TextField priceToTextField;
    @FXML
    private RadioButton priceRadioButton;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private RadioButton dateRadioButton;
    @FXML
    private ComboBox<String> serviceTypeComboBox;
    @FXML
    private RadioButton serviceTypeRadioButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setUpTableColumns();
        mainTableView.setItems(convertFromListToObservableList(adDao.getAllActiveAds()));
        countryComboBox.getItems().addAll(countryDao.getAllCountriesNamesList(countryDao.getAll()));
        categoryComboBox.getItems().addAll((categoryDao.getAllCategoryNames()));
        serviceTypeComboBox.getItems().addAll(getNames(Advertisement.ServiceType.class));
    }

    @FXML
    protected void showAllButtonPushed(ActionEvent event) {
        mainTableView.setItems(convertFromListToObservableList(adDao.getAllActiveAds()));
    }

    @FXML
    protected void countryComboBoxSelected(ActionEvent event) {
        cityComboBox.getItems().clear();
        List<String> listOfCityNames = cityDao.getAllCityNamesByCountry(countryComboBox.getValue());

        cityComboBox.getItems().addAll(listOfCityNames);
    }

    @FXML
    protected void findButtonPushed(ActionEvent event) {

        if (categoryRadioButton.isSelected() &&
                isComboboxHasValue(categoryComboBox, "Select category")) {
            mainTableView.setItems(findActiveAdvertisementByCategory(categoryComboBox.getValue(), adDao));
        }
        if (countryRadioButton.isSelected() &&
                isComboboxHasValue(countryComboBox, "Select country")) {
            mainTableView.setItems(findActiveAdvertisementByCountry(countryComboBox.getValue(), adDao));
        }
        if (cityRadioButton.isSelected() &&
                isComboboxHasValue(cityComboBox, "Select city and country")) {
            mainTableView.setItems(findActiveAdvertisementByCity(cityComboBox.getValue(), adDao));
        }
        if (priceRadioButton.isSelected() &&
                isTextFieldEmpty(priceToTextField, "Insert max. price") &&
                isTextFieldEmpty(priceFromTextField, "Insert min. price") &&
                stringMatcherValidation(priceToTextField.getText(), "[0-9]*['.']?[0-9]*",
                        "Make sure that price number format is xx.xx")) {
            mainTableView.setItems(findActiveAdvertisementByPrice(new BigDecimal(priceFromTextField.getText()),
                    new BigDecimal((priceToTextField.getText())), adDao));
        }
        if (dateRadioButton.isSelected() &&
                isDatePickerEmpty(startDatePicker, "Select Start Date") &&
                isDatePickerEmpty(endDatePicker, "Select End Date") &&
                startDateIsBeforeEndDate(
                        startDatePicker.getValue(),
                        endDatePicker.getValue(),
                        "Make sure that End Date is after Start Date")) {
            mainTableView.setItems(findActiveAdvertisementByDate(
                    convertToDateViaSqlDate(startDatePicker.getValue()),
                    convertToDateViaSqlDate(endDatePicker.getValue()), adDao));
        }
        if (serviceTypeRadioButton.isSelected() &&
                isComboboxHasValue(serviceTypeComboBox, "Select OFFER or REQUEST")) {
            mainTableView.setItems(findActiveAdvertisementByServiceType(serviceTypeComboBox.getValue(), adDao));
        }
    }

    protected ObservableList<Advertisement> findActiveAdvertisementByCategory(String category, AdvertisementDao adDao) {

        List<Advertisement> getAllAdvertisementsList = adDao.getAllActiveAds();
        List<Advertisement> getAllAdvertisementsByCategory = new ArrayList<>();

        for (Advertisement advertisement : getAllAdvertisementsList) {
            if (compareTwoStrings(advertisement.getCategory().getName(), category)) {
                getAllAdvertisementsByCategory.add(advertisement);
            }
        }
        return convertFromListToObservableList(getAllAdvertisementsByCategory);
    }

    protected ObservableList<Advertisement> findActiveAdvertisementByCity(String city, AdvertisementDao adDao) {

        List<Advertisement> getAllAdvertisementsList = adDao.getAllActiveAds();
        List<Advertisement> getAllAdvertisementsByCity = new ArrayList<>();

        for (Advertisement advertisement : getAllAdvertisementsList) {
            if (advertisement.getAddress().getCity().equals(city)) {
                getAllAdvertisementsByCity.add(advertisement);
            }
        }
        return convertFromListToObservableList(getAllAdvertisementsByCity);
    }

    protected ObservableList<Advertisement> findActiveAdvertisementByCountry(String country, AdvertisementDao adDao) {

        List<Advertisement> getAllAdvertisementsList = adDao.getAllActiveAds();
        List<Advertisement> getAllAdvertisementsByCountry = new ArrayList<>();

        for (Advertisement advertisement : getAllAdvertisementsList) {
            if (advertisement.getAddress().getCountry().equals(country)) {
                getAllAdvertisementsByCountry.add(advertisement);
            }
        }
        return convertFromListToObservableList(getAllAdvertisementsByCountry);
    }

    protected ObservableList<Advertisement> findActiveAdvertisementByPrice(BigDecimal bigDecimalBottomRate,
                                                                           BigDecimal bigDecimalTopRate, AdvertisementDao adDao) {

        List<Advertisement> getAllAdvertisementsList = adDao.getAllActiveAds();
        List<Advertisement> getAllAdvertisementsByPrice = new ArrayList<>();

        for (Advertisement advertisement : getAllAdvertisementsList) {
            if (compareTwoBigDecimal(bigDecimalBottomRate, bigDecimalTopRate, advertisement.getPrice())) {
                getAllAdvertisementsByPrice.add(advertisement);
            }
        }
        return convertFromListToObservableList(getAllAdvertisementsByPrice);
    }

    protected ObservableList<Advertisement> findActiveAdvertisementByDate(Date startDate, Date endDate, AdvertisementDao adDao) {

        List<Advertisement> getAllAdvertisementsList = adDao.getAllActiveAds();
        List<Advertisement> getAllAdvertisementsByDate = new ArrayList<>();

        for (Advertisement advertisement : getAllAdvertisementsList) {
            if (!advertisement.getStartDate().before(startDate) && !advertisement.getEndDate().after(endDate)) {
                getAllAdvertisementsByDate.add(advertisement);
            }
        }
        return convertFromListToObservableList(getAllAdvertisementsByDate);
    }

    protected ObservableList<Advertisement> findActiveAdvertisementByServiceType(String serviceType, AdvertisementDao adDao) {

        List<Advertisement> getAllAdvertisementsList = adDao.getAllActiveAds();
        List<Advertisement> getAllAdvertisementsByServiceTypeList = new ArrayList<>();

        for (Advertisement advertisement : getAllAdvertisementsList) {
            if (compareTwoStrings(advertisement.getServiceType().toString(), serviceType)) {
                getAllAdvertisementsByServiceTypeList.add(advertisement);
            }
        }
        return convertFromListToObservableList(getAllAdvertisementsByServiceTypeList);
    }
}
