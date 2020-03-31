package com.sda.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Unit test for simple App.
 */

public class AppTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/allAdsView.fxml"));
        primaryStage.setTitle("My First App");
        primaryStage.setScene(new Scene(root, 1280, 800));
        primaryStage.show();
        //Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    }
}

