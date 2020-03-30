package com.sda.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/homeView.fxml"));
        primaryStage.setTitle("My First App");
        primaryStage.setScene(new Scene(root, 1280, 800));
        primaryStage.show();
        //Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    }
}
