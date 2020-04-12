package com.sda.controller;

import com.sda.dao.implementation.CityDao;
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
    private TableColumn<Advertisement, String> addressTableColumn;
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
    }

    // Here we can use Oleks's table settings
    public void setUpTableColumns() {
        categoryTableColumn.setCellValueFactory(value ->
                new SimpleStringProperty(value.getValue().getCategory().getName()));
        categoryComboBox.setPromptText("Choose category");
        startDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        addressTableColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        countryComboBox.setPromptText("Choose location");
        serviceTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("serviceType"));
        subjectTableColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
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
        return offerServiceRadioButton.isSelected() ? Advertisement.ServiceType.OFFER : Advertisement.ServiceType.REQUEST;
    }

    @FXML
    void createButtonPushed() {

        Advertisement newAd = new Advertisement(
                subjectTextField.getText(),
                descriptionTextField.getText(),
                priceTextField.getText(),
                parser.convertToDateViaSqlDate(startDatePicker.getValue()),
                parser.convertToDateViaSqlDate(endDatePicker.getValue()),
                serviceTypeSelected(),
                categoryDao.getByName(categoryComboBox.getValue()),
                customer,
                new Address(countryComboBox.getValue(), cityComboBox.getValue()));

        isInt(priceTextField.getText());
        System.out.println("New ad " + subjectTextField + " created");

        serviceTable.getItems().add(newAd);

        adDao.update(newAd); // was changed to update
        updateCustomer();

        clearValues();

        AlertBox.success(); // new method in alert box
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

        allAds = serviceTable.getItems();

        //this gives us the rows that were selected
        selectedRows = serviceTable.getSelectionModel().getSelectedItems();

        for (Advertisement advertisement : selectedRows) {
            if (AlertBox.confirmation(advertisement.getSubject())) { // new method in alert box

                allAds.remove(advertisement);

                Advertisement shouldBeDeletedAd = adDao.get(advertisement.getId());

                adDao.delete(shouldBeDeletedAd);

                updateCustomer();

                System.out.println("Ad " + subjectTextField + " deleted");
            }
        }
    }

    @FXML
    void updateButtonPushed() {
/*        ad = serviceTable.getSelectionModel().getSelectedItem();


        if (ad != null) {
            this.categoryComboBox.setValue(ad.getCategory());
            this.startDatePicker.setValue(LocalDate.parse(ad.getStartDate().toString()));
            this.endDatePicker.setValue(LocalDate.parse(ad.getEndDate().toString()));
            //this.priceTextField.setText(String.valueOf(((Double) ad.getPrice())));
            this.locationComboBox.setValue(cityDao.getAll());
            this.serviceTypeGroup.getUserData();
            this.subjectTextField.setText(ad.getSubject());
            this.descriptionTextField.setText(String.valueOf(ad.getDescription()));
        }

        updateButton.setOnAction(e ->
        {
            AlertBox.confirmDelete("Ad has been updated");
            ObservableList<Advertisement> selectedAd;
            selectedAd = serviceTable.getSelectionModel().getSelectedItems();
            selectedAd.setAll();
            setUpTableColumns();
            clearValues();
        });*/
    }

    @FXML
    void detailsButtonPushed(javafx.event.ActionEvent event) {
        AdDetailsViewController controller = (changeScreen(event, "/views/adDetailsView.fxml").getController());
        controller.initData(serviceTable.getSelectionModel().getSelectedItem());
        controller.setCustomer(customer);
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
}
