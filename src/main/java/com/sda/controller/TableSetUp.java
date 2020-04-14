package com.sda.controller;

import com.sda.controller.utilities.AlertBox;
import com.sda.entity.Advertisement;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableSetUp extends GeneralController {

    @FXML
    protected TableView<Advertisement> mainTableView;
    @FXML
    protected TableColumn<Advertisement, String> categoryTableColumn;
    @FXML
    protected TableColumn<Advertisement, String> startDateTableColumn;
    @FXML
    protected TableColumn<Advertisement, String> endDateTableColumn;
    @FXML
    protected TableColumn<Advertisement, String> priceTableColumn;
    @FXML
    protected TableColumn<Advertisement, String> countryTableColumn;
    @FXML
    protected TableColumn<Advertisement, String> cityTableColumn;
    @FXML
    protected TableColumn<Advertisement.ServiceType, String> serviceTypeTableColumn;
    @FXML
    protected TableColumn<Advertisement, String> subjectTableColumn;


    protected void setUpTableColumns() {

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

    protected boolean isTableRowSelected(TableView<Advertisement> tableView) {
        return tableView.getSelectionModel().getSelectedItem() != null;
    }

    protected ObservableList<Advertisement> getSelectedFromTable(TableView<Advertisement> tableView) {
        return tableView.getSelectionModel().getSelectedItems();
    }

    protected ObservableList<Advertisement> getAllFromTable(TableView<Advertisement> tableView) {
        return tableView.getItems();
    }

    protected Advertisement getFromSelectedRow(TableView<Advertisement> tableView) {
        return tableView.getSelectionModel().getSelectedItem();
    }

    @FXML
    protected void detailsButtonPushed(javafx.event.ActionEvent event) {

        if (isTableRowSelected(mainTableView)) {

            AdDetailsViewController controller = (changeScreen(event, "/views/adDetailsView.fxml").getController());
            controller.initData(mainTableView.getSelectionModel().getSelectedItem());
            controller.setCustomer(customer);
        } else {
            AlertBox.validation("Make sure that some table row is selected");
        }
    }
}
