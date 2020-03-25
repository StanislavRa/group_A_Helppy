package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public abstract class Models {

    @FXML
    protected Text logoText;

    @FXML
    protected Button myAdsButton;

    @FXML
    protected Button allAdsButton;

    @FXML
    public abstract void logoTextPushed(ActionEvent event);

    @FXML
    public abstract void myAdsButtonPushed(ActionEvent event);

    @FXML
    public abstract void allAdsButtonPushed(ActionEvent event);

}
