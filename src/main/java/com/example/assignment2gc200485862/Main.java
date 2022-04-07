package com.example.assignment2gc200485862;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Searching Amazon Product is Now Fun!");
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        Image icon = new Image(getClass().getResourceAsStream("amazon-icon-41517.png"));
        stage.getIcons().add(icon);
        stage.show();
    }

    public static void main(String[] args) {

       launch();


    }
}