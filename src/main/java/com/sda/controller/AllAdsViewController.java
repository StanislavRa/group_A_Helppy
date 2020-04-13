package com.sda.controller;

import com.sda.dao.implementation.CityDao;
import com.sda.entity.Advertisement;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


/**
 * @author StanislavR
 */

public class AllAdsViewController extends GeneralController<Advertisement> implements Initializable {

    @FXML
    private TableView<Advertisement> mainTableView;
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
    @FXML
    private TableColumn<Advertisement, String> subjectTableColumn;
    @FXML
    private TableColumn<Advertisement, String> categoryTableColumn;
    @FXML
    private TableColumn<Advertisement, String> priceTableColumn;
    @FXML
    private TableColumn<Advertisement, String> countryTableColumn;
    @FXML
    private TableColumn<Advertisement, String> cityTableColumn;
    @FXML
    private TableColumn<Advertisement, String> startDateTableColumn;
    @FXML
    private TableColumn<Advertisement, String> endDateTableColumn;
    @FXML
    private TableColumn<Advertisement, String> serviceTypeTableColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setUpTableColumns();
        mainTableView.setItems(convertFromListToObservableList(adDao.getAllActiveList()));
        countryComboBox.getItems().addAll(countryDao.getAllCountriesNamesList(countryDao.getAll()));
        categoryComboBox.getItems().addAll((categoryDao.getAllCategoriesList()));
        serviceTypeComboBox.getItems().addAll(adDao.getAllServiceTypes());
    }

    @FXML
    void showAllButtonPushed(ActionEvent event) {
        mainTableView.setItems(convertFromListToObservableList(adDao.getAllActiveList()));
    }

    @FXML
    void detailsButtonPushed(ActionEvent event) {
        AdDetailsViewController controller = (changeScreen(event, "/views/adDetailsView.fxml").getController());
        controller.initData(mainTableView.getSelectionModel().getSelectedItem());
        controller.setCustomer(customer);
    }

    @FXML
    void countryComboBoxSelected(ActionEvent event) {
        cityComboBox.getItems().clear();
        cityComboBox.getItems().addAll(getListOfCityNamesByCountry(countryComboBox.getValue(), cityDao));
    }

    @FXML
    void findButtonPushed(ActionEvent event) {

        if (categoryRadioButton.isSelected()) {
            mainTableView.setItems(findActiveAdvertisementByCategory(categoryComboBox.getValue()));
        }
        if (countryRadioButton.isSelected()) {
            mainTableView.setItems(findActiveAdvertisementByCountry(countryComboBox.getValue()));
        }
        if (cityRadioButton.isSelected()) {
            mainTableView.setItems(findActiveAdvertisementByCity(cityComboBox.getValue()));
        }
        if (priceRadioButton.isSelected()) {
            mainTableView.setItems(findActiveAdvertisementByPrice(new BigDecimal(priceFromTextField.getText()),
                    new BigDecimal((priceToTextField.getText()))));
        }
        if (dateRadioButton.isSelected()) {
            mainTableView.setItems(findActiveAdvertisementByDate(
                    parser.convertToDateViaSqlDate(startDatePicker.getValue()),
                    parser.convertToDateViaSqlDate(endDatePicker.getValue())));
        }
        if (serviceTypeRadioButton.isSelected()) {
            mainTableView.setItems(findActiveAdvertisementByServiceType(serviceTypeComboBox.getValue()));
        }
    }

    public void setUpTableColumns() {

        subjectTableColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        categoryTableColumn.setCellValueFactory(value ->
                new SimpleStringProperty(value.getValue().getCategory().getName()));
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        countryTableColumn.setCellValueFactory(value ->
                new SimpleStringProperty(value.getValue().getAddress().getCountry()));
        cityTableColumn.setCellValueFactory(value ->
                new SimpleStringProperty(value.getValue().getAddress().getCity()));
        startDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        serviceTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("serviceType"));
    }

    public ObservableList<Advertisement> findActiveAdvertisementByCategory(String category) {

        List<Advertisement> getAllAdvertisementsList = adDao.getAllActiveList();
        List<Advertisement> getAllAdvertisementsByCategory = new ArrayList<>();

        for (Advertisement advertisement : getAllAdvertisementsList) {
            if (parser.compareTwoStrings(advertisement.getCategory().getName(), category)) {
                getAllAdvertisementsByCategory.add(advertisement);
            }
        }
        return convertFromListToObservableList(getAllAdvertisementsByCategory);
    }

    public ObservableList<Advertisement> findActiveAdvertisementByCity(String city) {

        List<Advertisement> getAllAdvertisementsList = adDao.getAllActiveList();
        List<Advertisement> getAllAdvertisementsByCity = new ArrayList<>();

        for (Advertisement advertisement : getAllAdvertisementsList) {
            if (advertisement.getAddress().getCity().equals(city)) {
                getAllAdvertisementsByCity.add(advertisement);
            }
        }
        return convertFromListToObservableList(getAllAdvertisementsByCity);
    }

    public ObservableList<Advertisement> findActiveAdvertisementByCountry(String country) {

        List<Advertisement> getAllAdvertisementsList = adDao.getAllActiveList();
        List<Advertisement> getAllAdvertisementsByCountry = new ArrayList<>();

        for (Advertisement advertisement : getAllAdvertisementsList) {
            if (advertisement.getAddress().getCountry().equals(country)) {
                getAllAdvertisementsByCountry.add(advertisement);
            }
        }
        return convertFromListToObservableList(getAllAdvertisementsByCountry);
    }

    public ObservableList<Advertisement> findActiveAdvertisementByPrice(BigDecimal bigDecimalBottomRate,
                                                                        BigDecimal bigDecimalTopRate) {

        List<Advertisement> getAllAdvertisementsList = adDao.getAllActiveList();
        List<Advertisement> getAllAdvertisementsByPrice = new ArrayList<>();

        for (Advertisement advertisement : getAllAdvertisementsList) {
            if (parser.compareTwoBigDecimal(bigDecimalBottomRate, bigDecimalTopRate, advertisement.getPrice())) {
                getAllAdvertisementsByPrice.add(advertisement);
            }
        }
        return convertFromListToObservableList(getAllAdvertisementsByPrice);
    }

    public ObservableList<Advertisement> findActiveAdvertisementByDate(Date startDate, Date endDate) {

        List<Advertisement> getAllAdvertisementsList = adDao.getAllActiveList();
        List<Advertisement> getAllAdvertisementsByDate = new ArrayList<>();

        for (Advertisement advertisement : getAllAdvertisementsList) {
            if (advertisement.getStartDate().after(startDate) && advertisement.getEndDate().before(endDate)) {
                getAllAdvertisementsByDate.add(advertisement);
            }
        }
        return convertFromListToObservableList(getAllAdvertisementsByDate);
    }

    public ObservableList<Advertisement> findActiveAdvertisementByServiceType(String serviceType) {

        List<Advertisement> getAllAdvertisementsList = adDao.getAllActiveList();
        List<Advertisement> getAllAdvertisementsByServiceTypeList = new ArrayList<>();

        for (Advertisement advertisement : getAllAdvertisementsList) {
            if (parser.compareTwoStrings(advertisement.getServiceType().toString(), serviceType)) {
                getAllAdvertisementsByServiceTypeList.add(advertisement);
            }
        }
        return convertFromListToObservableList(getAllAdvertisementsByServiceTypeList);
    }

    List<String> getListOfCityNamesByCountry(String country, CityDao cityDao) {
         List<String> listOfCityNames = cityDao.getAllCitiesByCountryList(country);

         return listOfCityNames;
    }
}
