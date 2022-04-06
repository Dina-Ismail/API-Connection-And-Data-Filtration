package com.example.assignment2gc200485862;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneChanger {
   public static void changeScenes(ActionEvent event, String fxmlFile, String selectedRating,String selectedAsinID,String selectedProductDesc,double selectedPrice,double selectedRetailPrice,boolean selectedPrime,String selectedURL,String selectedDeliveryMessage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFile));

            Scene scene = new Scene(fxmlLoader.load());

            //get the controller from the destination
            DetailViewController controller = fxmlLoader.getController();
            controller.loadDetails(selectedAsinID, selectedProductDesc,selectedPrice,selectedRetailPrice,selectedPrime,selectedURL,selectedDeliveryMessage, selectedRating);

            //get the stage object from the action event triggered when the button was pushed
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e)
        {
            System.out.println("Something went wrong while changing the scenes");
        }
    }
}
