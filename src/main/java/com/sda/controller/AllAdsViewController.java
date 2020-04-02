package com.sda.controller;

import com.sda.dao.implementation.AdvertisementDao;
import com.sda.entity.Address;
import com.sda.entity.Advertisement;
import com.sda.entity.Category;
import com.sda.entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private ComboBox<?> categoryComboBox;

    @FXML
    private RadioButton categoryRadioButton;

    @FXML
    private ToggleGroup radioButtonToggleGroup;

    @FXML
    private ComboBox<?> locationComboBox;

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
    private ComboBox<?> serviceTypeComboBox;

    @FXML
    private RadioButton serviceTypeRadioButton;

    @FXML
    private ToggleGroup radioButtonToggleGroup1;

    @FXML
    private Button findButton;

    @FXML
    private TableColumn<?, ?> subjectColumn;

    @FXML
    private TableColumn<?, ?> categoryColumn;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private TableColumn<?, ?> locationColumn;

    @FXML
    private TableColumn<?, ?> startDateColumn;

    @FXML
    private TableColumn<?, ?> endDateColumn;

    @FXML
    private TableColumn<?, ?> serviceTypeColumn;

    @FXML
    private Text logoText;


  /*  public AllAdsViewController(Customer customer) {
        super(customer);
    }*/

    public AllAdsViewController() {
    }

    @FXML
    void findButtonPushed(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpTableColumns();
        //load data
        AdvertisementDao advertisementDao = new AdvertisementDao("hibernateDemi.cfg.xml");
        List<Advertisement> getAllAdvertisements =  advertisementDao.getAll();
        ObservableList<Advertisement> advertisementObservableList = FXCollections.observableArrayList(getAllAdvertisements);

        mainTableView.setItems(advertisementObservableList);
    }

    public void setUpTableColumns() {
        //set up the columns in the table
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        serviceTypeColumn.setCellValueFactory(new PropertyValueFactory<>("serviceType"));
    }

    @FXML
    void seeDetailsButtonPushed(ActionEvent event) throws ParseException {

//        AdDetailsViewController controller = (changeScreen(event, "/views/adDetailsView.fxml").getController());
//        controller.initData(mainTableView.getSelectionModel().getSelectedItem());


    }
}
