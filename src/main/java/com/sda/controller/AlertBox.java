package com.sda.controller;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public static void ok(String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);

        Button okButton = new Button("OK");
        okButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(okButton, layout);
        layout.setAlignment(Pos.CENTER);

        Parent root;
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }


    static Boolean answer;

    public static Boolean confirmDelete(String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);

        Button yesButton = new Button("Yes");
        Button noButton = new Button("Yes");
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });


        VBox layout = new VBox(10);
        layout.getChildren().addAll(layout, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        Parent root;
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }

    public static boolean confirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + message + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        return alert.getResult() == ButtonType.YES;

    }

    public static void success() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Success!");
        alert.showAndWait();
    }

}