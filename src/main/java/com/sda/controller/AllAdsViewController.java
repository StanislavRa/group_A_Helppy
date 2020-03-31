package com.sda.controller;

import com.sda.dao.implementation.AddressDao;
import com.sda.dao.implementation.AdvertisementDao;
import com.sda.dao.implementation.CategoryDao;
import com.sda.dao.implementation.CustomerDao;
import com.sda.entity.Address;
import com.sda.entity.Advertisement;
import com.sda.entity.Category;
import com.sda.entity.Customer;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.text.Text;


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


  /*  public AllAdsViewController(Customer customer) {
        super(customer);
    }*/

    public AllAdsViewController() {
    }

    @FXML
    void findButtonPushed(ActionEvent event) {

        //create Dao classes
        CustomerDao customerDao = new CustomerDao("oleksHibernateTest.cfg.xml");
        AdvertisementDao advertisementDao = new AdvertisementDao("oleksHibernateTest.cfg.xml");
        CategoryDao categoryDao = new CategoryDao("oleksHibernateTest.cfg.xml");
        AddressDao addressDao = new AddressDao("oleksHibernateTest.cfg.xml");

    /*    if (categoryRadioButton.isSelected())
            mainTableView.setItems(categoryDao.);
        if (locationRadioButton.isSelected())
            mainTableView.setItems(bus.findBusByFuel(Float.parseFloat(fuelTextField.getText())));
        if (priceRadioButton.isSelected())
            mainTableView.setItems(bus.findBusByPurchasedOn(parser.convertToDateViaSqlDate(purchasedOnDatePicker.getValue())));
        if (dateRadioButton.isSelected())
            mainTableView.setItems(bus.findBusByPurchasedOn(parser.convertToDateViaSqlDate(purchasedOnDatePicker.getValue())));
        if (serviceTypeRadioButton.isSelected())
            mainTableView.setItems(bus.findBusByPurchasedOn(parser.convertToDateViaSqlDate(purchasedOnDatePicker.getValue())));

        if (findByDriverRadioButton.isSelected())
            tableView.setItems(bus.findBusByDriverName(driverChoiceBox.getValue()));
        if (findByFuelRadioButton.isSelected())
            tableView.setItems(bus.findBusByFuel(Float.parseFloat(fuelTextField.getText())));
        if (findByPurchaseOnRadioButton.isSelected())
            tableView.setItems(bus.findBusByPurchasedOn(parser.convertToDateViaSqlDate(purchasedOnDatePicker.getValue())));*/
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setUpTableColumns();

        //create Dao classes
        CustomerDao customerDao = new CustomerDao("oleksHibernateTest.cfg.xml");
        AdvertisementDao advertisementDao = new AdvertisementDao("oleksHibernateTest.cfg.xml");
        CategoryDao categoryDao = new CategoryDao("oleksHibernateTest.cfg.xml");
        AddressDao addressDao = new AddressDao("oleksHibernateTest.cfg.xml");

        //load data
        List<Advertisement> getAllAdvertisements =  advertisementDao.getAll();
        ObservableList<Advertisement> advertisementObservableList = FXCollections.observableArrayList(getAllAdvertisements);

        mainTableView.setItems(advertisementObservableList);
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

    public List<String> getAllCategoriesNames(List<Category> categories) {
        List<String> listOfSubcategories = new ArrayList<>();
        CategoryDao categoryDao = new CategoryDao("oleksHibernateTest.cfg.xml");
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

/*   public List<String> getAllCategoriesNames(List<Category> categories) {
        List<String> listOfSubcategories = new ArrayList<>();
        CategoryDao categoryDao = new CategoryDao("oleksHibernateTest.cfg.xml");
        for (Category o : categories){
            listOfSubcategories.add(o.getName());
            }
        return listOfSubcategories;
    }
*/
}
