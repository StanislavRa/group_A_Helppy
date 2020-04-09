package com.sda.controller;

import com.sda.dao.implementation.*;
import com.sda.entity.Advertisement;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

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
    private Button myAdsButton;

    @FXML
    private Button allAdsButton;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private RadioButton categoryRadioButton;

    @FXML
    private ToggleGroup radioButtonToggleGroup;

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
    private Button findButton;

    @FXML
    private Button showAllButton;

    @FXML
    private Button detailsButton;

    @FXML
    private Text logoText;

    //javafx columns creation
    @FXML
    private TableColumn<Advertisement, String> subjectColumn;

    @FXML
    private TableColumn<Advertisement, String> categoryColumn;

    @FXML
    private TableColumn<Advertisement, String> priceColumn;

    @FXML
    private TableColumn<Advertisement, String> countryColumn;

    @FXML
    private TableColumn<Advertisement, String> cityColumn;

    @FXML
    private TableColumn<Advertisement, String> startDateColumn;

    @FXML
    private TableColumn<Advertisement, String> endDateColumn;

    @FXML
    private TableColumn<Advertisement, String> serviceTypeColumn;

   String connectionToDatabaseCreate  = "oleksHibernateCreateTest.cfg.xml";
   String connectionToDatabaseValidate  = "oleksHibernateValidateTest.cfg.xml";


    @FXML
    void showAllButtonPushed(ActionEvent event) {

        AdvertisementDao advertisementDao = new AdvertisementDao(connectionToDatabaseValidate);
        mainTableView.setItems(convertFromListToObservableList(advertisementDao.getAllActiveList()));

    }

    @FXML
    void detailsButtonPushed(ActionEvent event) {

    }

    @FXML
    void countryComboBoxSelected(ActionEvent event) {
        cityComboBox.getItems().clear();
        CityDao cityDao = new CityDao(connectionToDatabaseValidate);
        List<String> listOfCityNames = cityDao.getAllCitiesByCountryList(countryComboBox.getValue());

        cityComboBox.getItems().addAll(listOfCityNames);
    }

    @FXML
    void findButtonPushed(ActionEvent event) {

        if (categoryRadioButton.isSelected()) {
            mainTableView.setItems(findActiveAdvertisementByCategory(categoryComboBox.getValue()));
        }
        if (countryRadioButton.isSelected()){
            mainTableView.setItems(findActiveAdvertisementByCountry(countryComboBox.getValue()));
        }
        if (cityRadioButton.isSelected()){
            mainTableView.setItems(findActiveAdvertisementByCity(cityComboBox.getValue()));
        }
        if (priceRadioButton.isSelected()) {
            mainTableView.setItems(findActiveAdvertisementByPrice(new BigDecimal(priceFromTextField.getText()),
                                                            new BigDecimal((priceToTextField.getText()))));
        }
        if (dateRadioButton.isSelected()) {
            mainTableView.setItems(findActiveAdvertisementByDate(parser.convertToDateViaSqlDate(startDatePicker.getValue()),
                                                           parser.convertToDateViaSqlDate(endDatePicker.getValue())));
        }
        if (serviceTypeRadioButton.isSelected()) {
            mainTableView.setItems(findActiveAdvertisementByServiceType(serviceTypeComboBox.getValue()));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //create Dao classes
        CustomerDao customerDao = new CustomerDao(connectionToDatabaseValidate);
        AdvertisementDao advertisementDao = new AdvertisementDao(connectionToDatabaseValidate);
        CategoryDao categoryDao = new CategoryDao(connectionToDatabaseValidate);
        AddressDao addressDao = new AddressDao(connectionToDatabaseValidate);
        CountryDao countryDao = new CountryDao(connectionToDatabaseValidate);
        CityDao cityDao = new CityDao(connectionToDatabaseValidate);

        //set Up Table Columns and ComboBoxes
        setUpTableColumns();
        mainTableView.setItems(convertFromListToObservableList(advertisementDao.getAllActiveList()));
        countryComboBox.getItems().addAll(countryDao.getAllCountyList());
        categoryComboBox.getItems().addAll((categoryDao.getAllCategoriesList()));
        serviceTypeComboBox.getItems().addAll(advertisementDao.getAllServiceTypes());
    }

    public void setUpTableColumns() {

        //set up the columns in the table
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        categoryColumn.setCellValueFactory(value ->
                new SimpleStringProperty(value.getValue().getCategory().getName()));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        countryColumn.setCellValueFactory(value ->
                new SimpleStringProperty(value.getValue().getAddress().getCountryName().getCountryName()));
        cityColumn.setCellValueFactory(value ->
                new SimpleStringProperty(value.getValue().getAddress().getCityName().getCityName()));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        serviceTypeColumn.setCellValueFactory(new PropertyValueFactory<>("serviceType"));
    }


    public ObservableList<Advertisement> findActiveAdvertisementByCategory(String category) {

        AdvertisementDao advertisementDao = new AdvertisementDao(connectionToDatabaseValidate);

        //load data
        List<Advertisement> getAllAdvertisementsList =  advertisementDao.getAllActiveList();
        List<Advertisement> getAllAdvertisementsByCategory = new ArrayList<>();

        for (Advertisement advertisement : getAllAdvertisementsList) {
            if (parser.compareTwoStrings(advertisement.getCategory().getName(), category)) {
                getAllAdvertisementsByCategory.add(advertisement);
            }
        }
        return convertFromListToObservableList(getAllAdvertisementsByCategory);
    }

    public ObservableList<Advertisement> findActiveAdvertisementByCity(String city) {

        AdvertisementDao advertisementDao = new AdvertisementDao(connectionToDatabaseValidate);

        //load data
        List<Advertisement> getAllAdvertisementsList =  advertisementDao.getAllActiveList();
        List<Advertisement> getAllAdvertisementsByCity = new ArrayList<>();

        for (Advertisement advertisement : getAllAdvertisementsList) {
            if (advertisement.getAddress().getCityName().getCityName().equals(city)) {
                getAllAdvertisementsByCity.add(advertisement);
            }
        }
        return convertFromListToObservableList(getAllAdvertisementsByCity);
    }

    public ObservableList<Advertisement> findActiveAdvertisementByCountry(String country) {

        AdvertisementDao advertisementDao = new AdvertisementDao(connectionToDatabaseValidate);

        //load data
        List<Advertisement> getAllAdvertisementsList =  advertisementDao.getAllActiveList();
        List<Advertisement> getAllAdvertisementsByCountry = new ArrayList<>();

        for (Advertisement advertisement : getAllAdvertisementsList) {
            if (advertisement.getAddress().getCountryName().getCountryName().equals(country)) {
                getAllAdvertisementsByCountry.add(advertisement);
            }
        }
        return convertFromListToObservableList(getAllAdvertisementsByCountry);
    }

    public ObservableList<Advertisement> findActiveAdvertisementByPrice(BigDecimal bigDecimalBottomRate, BigDecimal bigDecimalTopRate) {

        AdvertisementDao advertisementDao = new AdvertisementDao(connectionToDatabaseValidate);

        //load data
        List<Advertisement> getAllAdvertisementsList =  advertisementDao.getAllActiveList();
        List<Advertisement> getAllAdvertisementsByPrice = new ArrayList<>();

        for (Advertisement advertisement : getAllAdvertisementsList) {
            if (advertisement.getPrice().compareTo(bigDecimalBottomRate)>=0 && advertisement.getPrice().compareTo(bigDecimalTopRate)<=0) {
                getAllAdvertisementsByPrice.add(advertisement);
            }
        }
        return convertFromListToObservableList(getAllAdvertisementsByPrice);
    }

    public ObservableList<Advertisement> findActiveAdvertisementByDate(Date startDate, Date endDate) {

        AdvertisementDao advertisementDao = new AdvertisementDao(connectionToDatabaseValidate);

        //load data
        List<Advertisement> getAllAdvertisementsList =  advertisementDao.getAllActiveList();
        List<Advertisement> getAllAdvertisementsByDate = new ArrayList<>();

        for (Advertisement advertisement : getAllAdvertisementsList) {
            if (advertisement.getStartDate().after(startDate) && advertisement.getEndDate().before(endDate)) {
                getAllAdvertisementsByDate.add(advertisement);
            }
        }
        return convertFromListToObservableList(getAllAdvertisementsByDate);
    }

    public ObservableList<Advertisement> findActiveAdvertisementByServiceType(String serviceType) {

        AdvertisementDao advertisementDao = new AdvertisementDao(connectionToDatabaseValidate);
        //load data
        List<Advertisement> getAllAdvertisementsList =  advertisementDao.getAllActiveList();
        List<Advertisement> getAllAdvertisementsByServiceTypeList = new ArrayList<>();

        for (Advertisement advertisement : getAllAdvertisementsList) {
            if (parser.compareTwoStrings(advertisement.getServiceType().toString(),serviceType)) {
                getAllAdvertisementsByServiceTypeList.add(advertisement);
            }
        }
        return convertFromListToObservableList(getAllAdvertisementsByServiceTypeList);
    }
}
