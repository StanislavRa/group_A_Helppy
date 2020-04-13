package com.sda.controller;

import com.sda.entity.Address;
import com.sda.entity.Advertisement;
import com.sda.entity.Customer;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MyAdsController extends GeneralController<Advertisement> implements Initializable {

    @FXML
    private Text logoText;
    @FXML
    private Button allAdsButton;
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
    private ToggleGroup serviceTypeGroup;
    @FXML
    private RadioButton requestServiceRadioButton;
    @FXML
    private TextField subjectTextField;
    @FXML
    private TextArea descriptionTextField;

    // we do not need some buttons, because we use ...ButtonPushed methods
    @FXML
    private Button createButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button detailsButton;
    @FXML
    private TableView<Advertisement> serviceTable;
    @FXML
    private TableColumn<Advertisement, String> categoryTableColumn;
    @FXML
    private TableColumn<Advertisement, String> startDateTableColumn;
    @FXML
    private TableColumn<Advertisement, String> endDateTableColumn;
    @FXML
    private TableColumn<Advertisement, String> priceTableColumn;
    @FXML
    private TableColumn<Advertisement, String> countryTableColumn;
    @FXML
    private TableColumn<Advertisement, String> cityTableColumn;
    @FXML
    private TableColumn<Advertisement.ServiceType, String> serviceTypeTableColumn;
    @FXML
    private TableColumn<Advertisement, String> subjectTableColumn;

    protected void initData() {

        setUpTableColumns();

        List<Advertisement> getUserAds = customer.getUserAdvertisements();
        ObservableList<Advertisement> adsTable = FXCollections.observableArrayList(getUserAds);

        serviceTable.setItems(adsTable);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        countryComboBox.getItems().addAll(countryDao.getAllCountriesNamesList(countryDao.getAll()));
        categoryComboBox.getItems().addAll((categoryDao.getAllCategoriesList()));
        populateFieldsFromSelectedRow();
    }

    // Here we can use Oleks's table settings
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

    // Logic to choose only particular country city
    @FXML
    void countryComboBoxSelected(javafx.event.ActionEvent event) {
        cityComboBox.getItems().clear();
        List<String> listOfCityNames = cityDao.getAllCitiesByCountryList(countryComboBox.getValue());

        cityComboBox.getItems().addAll(listOfCityNames);
    }

    // need some checking from the price input
    private boolean isInt(String input) {
        try {
            int price = Integer.parseInt(String.valueOf(input));
            return true;
        } catch (NumberFormatException e) {
            System.out.println(e);
            return false;
        }
    }

    private Advertisement.ServiceType serviceTypeSelected() {
        return offerServiceRadioButton.isSelected() ?
                Advertisement.ServiceType.OFFER : Advertisement.ServiceType.REQUEST;
    }

    private Advertisement getAdFromFields() {

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

    @FXML
    void createButtonPushed() {

        isInt(priceTextField.getText());
        System.out.println("New ad " + subjectTextField.getText() + " created");

        adDao.save(getAdFromFields());

        updateCustomer();
        initData();

        AlertBox.success(); // new method in alert box

        clearValues();
    }

    @FXML
    void clearValues() {
        categoryComboBox.getSelectionModel().clearSelection();
        startDatePicker.getEditor().clear();
        endDatePicker.getEditor().clear();
        priceTextField.clear();
        countryComboBox.getSelectionModel().clearSelection();
        //serviceTypeGroup.setSelected(false);
        offerServiceRadioButton.setSelected(false);
        requestServiceRadioButton.setSelected(false);
        subjectTextField.clear();
        descriptionTextField.clear();
    }

    @FXML
    void deleteButtonPushed() {

        ObservableList<Advertisement> selectedRows, allAds;

        allAds = getAllAdsFromTable(serviceTable);

        //this gives us the rows that were selected
        selectedRows = getSelectedAdsFromTable(serviceTable);

        for (Advertisement advertisement : selectedRows) {
            if (AlertBox.confirmation(advertisement.getSubject())) {

                allAds.remove(advertisement);

                Advertisement shouldDeleteThisAd = adDao.get(advertisement.getId());

                adDao.delete(shouldDeleteThisAd);
                updateCustomer();

                System.out.println("Ad " + subjectTextField.getText() + " deleted");
            }
        }
    }

    @FXML
    void updateButtonPushed() {

        Advertisement shouldUpdateThisAd = adDao.get(getAdFromSelectedRow(serviceTable).getId());

        shouldUpdateThisAd.setSubject(subjectTextField.getText());
        shouldUpdateThisAd.setDescription(descriptionTextField.getText());
        shouldUpdateThisAd.setServiceType(serviceTypeSelected());
        shouldUpdateThisAd.setAddress(new Address(countryComboBox.getValue(), cityComboBox.getValue()));
        shouldUpdateThisAd.setCategory(categoryDao.getByName(categoryComboBox.getValue()));
        shouldUpdateThisAd.setPrice(priceTextField.getText());

        System.out.println(shouldUpdateThisAd.getServiceType());


        adDao.update(shouldUpdateThisAd);

        updateCustomer();
        initData();
        AlertBox.success();

        System.out.println("Ad " + subjectTextField.getText() + " updated");

        clearValues();

    }

    @FXML
    void detailsButtonPushed(javafx.event.ActionEvent event) {
        AdDetailsViewController controller = (changeScreen(event, "/views/adDetailsView.fxml").getController());
        controller.initData(serviceTable.getSelectionModel().getSelectedItem());
        controller.setCustomer(customer);
    }


    private ObservableList<Advertisement> getSelectedAdsFromTable(TableView<Advertisement> tableView) {
        return tableView.getSelectionModel().getSelectedItems();
    }

    private ObservableList<Advertisement> getAllAdsFromTable(TableView<Advertisement> tableView) {
        return tableView.getItems();
    }

    private Advertisement getAdFromSelectedRow(TableView<Advertisement> tableView) {
        return tableView.getSelectionModel().getSelectedItem();
    }

    public void logOutButtonPushed(javafx.event.ActionEvent actionEvent) {
        customer = null;
        changeScreen(actionEvent, "/views/logInView.fxml");
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void populateFieldsFromSelectedRow() {
        serviceTable.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){

                subjectTextField.setText(getAdFromSelectedRow(serviceTable).getSubject());
                descriptionTextField.setText(getAdFromSelectedRow(serviceTable).getDescription());
                priceTextField.setText(getAdFromSelectedRow(serviceTable).getPrice().toString());
                countryComboBox.setValue(getAdFromSelectedRow(serviceTable).getAddress().getCountry());
                cityComboBox.setValue(getAdFromSelectedRow(serviceTable).getAddress().getCity());
                categoryComboBox.setValue(getAdFromSelectedRow(serviceTable).getCategory().getName());
                endDatePicker.setValue(parser.convertToLocalDateViaInstant(getAdFromSelectedRow(serviceTable).getEndDate()));
                startDatePicker.setValue(parser.convertToLocalDateViaInstant(getAdFromSelectedRow(serviceTable).getStartDate()));
                if (getAdFromSelectedRow(serviceTable).getServiceType() == Advertisement.ServiceType.OFFER) {
                offerServiceRadioButton.setSelected(true);
                } else requestServiceRadioButton.setSelected(true);
            }
        });
    }
}
