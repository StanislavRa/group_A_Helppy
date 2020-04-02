package com.sda.controller;

import com.sda.dao.implementation.AddressDao;
import com.sda.dao.implementation.AdvertisementDao;
import com.sda.dao.implementation.CategoryDao;
import com.sda.dao.implementation.CustomerDao;
import com.sda.entity.Advertisement;
import com.sda.entity.Category;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class AllAdsViewController extends GeneralController implements Initializable {

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
    private ComboBox<String> locationComboBox;

    @FXML
    private RadioButton locationRadioButton;

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
    private ToggleGroup radioButtonToggleGroup1;

    @FXML
    private Button findButton;

    @FXML
    private TableColumn<Advertisement, String> subjectColumn;

    @FXML
    private TableColumn<Advertisement, String> categoryColumn;

    @FXML
    private TableColumn<Advertisement, String> priceColumn;

    @FXML
    private TableColumn<Advertisement, String> locationColumn;

    @FXML
    private TableColumn<Advertisement, String> startDateColumn;

    @FXML
    private TableColumn<Advertisement, String> endDateColumn;

    @FXML
    private TableColumn<Advertisement, String> serviceTypeColumn;

    @FXML
    private Text logoText;

    //create Dao classes
    CustomerDao customerDao = new CustomerDao("oleksHibernateTest.cfg.xml");
    AdvertisementDao advertisementDao = new AdvertisementDao("oleksHibernateTest.cfg.xml");
    CategoryDao categoryDao = new CategoryDao("oleksHibernateTest.cfg.xml");
    AddressDao addressDao = new AddressDao("oleksHibernateTest.cfg.xml");


  /*  public AllAdsViewController(Customer customer) {
        super(customer);
    }*/

    public AllAdsViewController() {
    }


    @FXML
    void findButtonPushed() {

        if (categoryRadioButton.isSelected()) {
            mainTableView.setItems(findAdvertisementByCategory(categoryComboBox.getValue()));
        }
        if (locationRadioButton.isSelected()){
            mainTableView.setItems(findAdvertisementByLocation(locationComboBox.getValue()));
        }
        if (priceRadioButton.isSelected()) {
            mainTableView.setItems(findAdvertisementByPrice(new BigDecimal(String.valueOf(priceFromTextField)),
                                                            new BigDecimal(String.valueOf(priceToTextField))));
        }
        if (dateRadioButton.isSelected()) {
            mainTableView.setItems(findAdvertisementByDate(java.sql.Date.valueOf(startDatePicker.getValue()),
                                                            java.sql.Date.valueOf(endDatePicker.getValue())));
        }
        if (serviceTypeRadioButton.isSelected()) {
            mainTableView.setItems(findAdvertisementByServiceType(serviceTypeComboBox.getValue()));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setUpTableColumns();
        mainTableView.setItems(getAdvertisementsListFromDatabase());
        locationComboBox.getItems().addAll(String.valueOf(addressDao.getAll()));
        categoryComboBox.getItems().addAll(getAllCategoriesNames(categoryDao.getAll()));
        serviceTypeComboBox.getItems().addAll(getAllServiceTypes());

    }

    public void setUpTableColumns() {

        //set up the columns in the table
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        subjectColumn.getStyleClass().add("first-name-col");
        categoryColumn.setCellValueFactory(value ->
                new SimpleStringProperty(value.getValue().getCategory().getName()));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        locationColumn.setCellValueFactory(value ->
                new SimpleStringProperty(value.getValue().getAddress().getCity()));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        serviceTypeColumn.setCellValueFactory(new PropertyValueFactory<>("serviceType"));
    }

    public ObservableList<Advertisement> getAdvertisementsListFromDatabase() {
        //load data
        List<Advertisement> getAllAdvertisements =  advertisementDao.getAll();
        return FXCollections.observableArrayList(getAllAdvertisements);
    }
   public List<String> getAllCategoriesNames(List<Category> categories) {
        List<String> listOfSubcategories = new ArrayList<>();
        for (Category o : categories){
            listOfSubcategories.add(o.getName());
            }
        return listOfSubcategories;
    }

    public List<String> getAllServiceTypes() {
        List<String> listOfServiceTypes = new ArrayList<>();
            listOfServiceTypes.add(Advertisement.ServiceType.OFFER.name());
            listOfServiceTypes.add(Advertisement.ServiceType.REQUEST.name());
            return listOfServiceTypes;
    }

    public List<String> getAllServiceStates() {
        List<String> listOfServiceStates = new ArrayList<>();
            listOfServiceStates.add(Advertisement.ServiceState.INACTIVE.name());
            listOfServiceStates.add(Advertisement.ServiceState.ACTIVE.name());
            return listOfServiceStates;
    }


    public ObservableList<Advertisement> findAdvertisementByCategory(String category) {
        //load data
        List<Advertisement> getAllAdvertisementsList =  advertisementDao.getAll();
        List<Advertisement> getAllAdvertisementsByCategory = new ArrayList<>();

        for (Advertisement advertisement : getAllAdvertisementsList) {
            if (advertisement.getAddress().getCity().equals(category)) {
                getAllAdvertisementsByCategory.add(advertisement);
            }
        }
        return FXCollections.observableArrayList(getAllAdvertisementsByCategory);
    }

    public ObservableList<Advertisement> findAdvertisementByLocation(String address) {
        //load data
        List<Advertisement> getAllAdvertisementsList =  advertisementDao.getAll();
        List<Advertisement> getAllAdvertisementsByLocation = new ArrayList<>();

        for (Advertisement advertisement : getAllAdvertisementsList) {
            if (advertisement.getAddress().getCity().equals(address)) {
                getAllAdvertisementsByLocation.add(advertisement);
            }
        }
        return FXCollections.observableArrayList(getAllAdvertisementsByLocation);
    }

    public ObservableList<Advertisement> findAdvertisementByPrice(BigDecimal bigDecimalBottomRate, BigDecimal bigDecimalTopRate) {
        //load data
        List<Advertisement> getAllAdvertisementsList =  advertisementDao.getAll();
        List<Advertisement> getAllAdvertisementsByPrice = new ArrayList<>();

        for (Advertisement advertisement : getAllAdvertisementsList) {
            if (advertisement.getPrice().compareTo(bigDecimalBottomRate)>=0 && advertisement.getPrice().compareTo(bigDecimalTopRate)<=0) {
                getAllAdvertisementsByPrice.add(advertisement);
            }
        }
        return FXCollections.observableArrayList(getAllAdvertisementsByPrice);
    }

    public ObservableList<Advertisement> findAdvertisementByDate(Date startDate, Date endDate) {
        //load data
        List<Advertisement> getAllAdvertisementsList =  advertisementDao.getAll();
        List<Advertisement> getAllAdvertisementsByDate = new ArrayList<>();

        for (Advertisement advertisement : getAllAdvertisementsList) {
            if (advertisement.getStartDate().after(startDate) && advertisement.getEndDate().before(endDate)) {
                getAllAdvertisementsByDate.add(advertisement);
            }
        }
        return FXCollections.observableArrayList(getAllAdvertisementsByDate);
    }

    public ObservableList<Advertisement> findAdvertisementByServiceType(String serviceType) {
        //load data
        List<Advertisement> getAllAdvertisementsList =  advertisementDao.getAll();
        List<Advertisement> getAllAdvertisementsByServiceTypeList = new ArrayList<>();

        for (Advertisement advertisement : getAllAdvertisementsList) {
            if (advertisement.getServiceType().toString().equals(serviceType)) {
                getAllAdvertisementsByServiceTypeList.add(advertisement);
            }
        }
        return FXCollections.observableArrayList(getAllAdvertisementsByServiceTypeList);
    }
}
