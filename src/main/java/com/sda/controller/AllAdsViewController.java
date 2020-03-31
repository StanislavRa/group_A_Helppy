package com.sda.controller;

import com.sda.entity.Address;
import com.sda.entity.Advertisement;
import com.sda.entity.Category;
import com.sda.entity.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author StanislavR
 */
public class AllAdsViewController extends GeneralController {

    @FXML
    private Button seeDetailsButton;

    @FXML
    private ToggleGroup radioButtonToggleGroup;

    @FXML
    private RadioButton priceRadioButton;

    @FXML
    private TextField priceFromTextField;

    @FXML
    private TableColumn<?, ?> locationColumn;

    @FXML
    private RadioButton dateRadioButton;

    @FXML
    private TableColumn<?, ?> startDateColumn;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private Button findButton;

    @FXML
    private ComboBox<?> locationComboBox;

    @FXML
    private TableColumn<?, ?> categoryColumn;

    @FXML
    private TableColumn<?, ?> subjectColumn;

    @FXML
    private TableColumn<?, ?> endDateColumn;

    @FXML
    private ComboBox<?> categoryComboBox;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private RadioButton categoryRadioButton;

    @FXML
    private TableView<?> mainTableView;

    @FXML
    private TextField priceToTextField;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private RadioButton locationRadioButton;

    @FXML
    void seeDetailsButtonPushed(ActionEvent event) throws ParseException {
        // create new customer
        Customer customer = new Customer();
        customer.setLogin("Pjotr");
        customer.setPassword("123456");
        customer.setFullName("Petr III");

        // create new address
        Address addressTest1 = new Address("Estonia", "Tallinn", "Parnu mnt");

        // create new advertisement
        String startDateString1 = "31/12/1998";
        Date startDate1 = new SimpleDateFormat("dd/MM/yy").parse(startDateString1);

        String endDateString1 = "31/12/1998";
        Date endDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(endDateString1);



        Advertisement advertisement1 = new Advertisement(
                "Clean Fast",
                "blablabla",
                "2.5",
                startDate1,
                endDate1,
                "OFFER",
                new Category("CLEANING"),
                customer);
        advertisement1.setServiceState(Advertisement.ServiceState.INACTIVE);

        // assign address to advertisement
        advertisement1.setAddress(addressTest1);

        // assign customer to advertisement
        advertisement1.setCustomer(customer);

        AdDetailsViewController controller = (changeScreen(event, "/views/adDetailsView.fxml").getController());

        controller.setCustomer(customer);
        controller.initData(advertisement1);
    }

}
