package com.example.assignment2gc200485862;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchViewController implements Initializable {
    @FXML
    private TextField keywordTextBox;

    @FXML
    private Label resultCountLabel;

    @FXML
    private ListView<ProductDetails> resultListView;

    @FXML
    private ImageView imageView;


    @FXML
    private Button detailsButton;

    /**
     * This method is triggered with the "Go for it button", where it calls the Api through the getProductFromApi method and searches for the keyword entered
     * by the user and shows a list and count of matching results.
     */
    @FXML
    private void searchProducts()
    {
        ApiResponse apiResponse = ApiUtility.getProductsFromApi(keywordTextBox.getText(),1);
        resultListView.getItems().clear();
       // imageView.setImage(new Image("https://i.gifer.com/4V0b.gif"));
        if (apiResponse.getSearchProductDetails() != null)
        {
            resultListView.getItems().addAll(apiResponse.getSearchProductDetails());
            resultCountLabel.setText(String.valueOf(apiResponse.getNumberOfProducts()));
            detailsButton.setVisible(true);
        }
        else
            resultCountLabel.setText("0");
    }
    /**
     * This method will be called to get all the details of the amazon product after passing to it the asin
     */

    /**
     * This method will pass the imdb information to the movie details controller
     */
    @FXML
    private void getDetails(ActionEvent event) throws IOException {
        String asinID = resultListView.getSelectionModel().getSelectedItem().getAsin();
        System.out.println("ASIN ID: "+asinID);
       SceneChanger.changeScenes(event, "detail-view.fxml",asinID);
    }

    /**
     * This is the initialize method implemented by the Initializable interface. A change listener is created to act to the selection of a product
     * by the user and shows an image for this product
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        resultCountLabel.setText("");
        detailsButton.setVisible(false);
        resultListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldProductSelected, newProductSelected) -> {
                    try {
                        imageView.setImage(new Image(newProductSelected.getImgUrl()));
                    }
                    catch (IllegalArgumentException e)
                    {
                        imageView.setImage(new Image("https://www.publicdomainpictures.net/pictures/280000/velka/not-found-image-15383864787lu.jpg"));
                    }
                });
    }
}
