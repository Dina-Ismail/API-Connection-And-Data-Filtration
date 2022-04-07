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
    private Label counterLabel;

    @FXML
    private ListView<ProductDetails> resultListView;

    @FXML
    private ImageView imageView;

    @FXML
    private Button detailsButton;
    @FXML
    private Button PeviousPageButton;

    private int counter;
    private String keyword;

    /**
     * This method is triggered with the "Go for it button", where it calls the Api through the getProductFromApi method and searches for the keyword entered
     * by the user and shows a list and count of matching results.
     * @return
     */
    @FXML

    private void searchProducts()
    {    keyword = keywordTextBox.getText();
        ApiResponse apiResponse = ApiUtility.getProductsFromApi(keywordTextBox.getText(),counter);
        resultListView.getItems().clear();
        if (apiResponse.getSearchProductDetails() != null)
        {
            imageView.setVisible(false);
            resultListView.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldProductSelected, newProductSelected) -> {
                        try {
                            imageView.setVisible(true);
                            imageView.setImage(new Image(newProductSelected.getImgUrl()));
                        }
                        catch (Exception e)
                        {
                            imageView.setVisible(false);
                            imageView.setImage(new Image("https://www.publicdomainpictures.net/pictures/280000/velka/not-found-image-15383864787lu.jpg"));
                        }
                    });
            resultListView.getItems().addAll(apiResponse.getSearchProductDetails());
            resultCountLabel.setText(String.valueOf(apiResponse.getNumberOfProducts()));
            detailsButton.setVisible(true);

            counter++;
            counterLabel.setText(String.valueOf(counter));
            if (counter>2)
            {
                PeviousPageButton.setVisible(true);
            }
            //this is the page number which will be sent next time.//i need to check how many result pages are they to disable the button
            System.out.println("counter"+counter);
            System.out.println("counter label"+counterLabel.getText());
        }
        else
            resultCountLabel.setText("0");

    }
    /**
     * This method will be trigger by the "Yayy Show me the details!" button and will run the changeScene and pass the selected product object
     * with the new fxml file that needs to be launched.
     */
    @FXML
   private void getDetails(ActionEvent event) throws IOException {
        ProductDetails products = resultListView.getSelectionModel().getSelectedItem();
        System.out.println(products.getProductDescription());
        SceneChanger.changeScenes(event,"detail-view.fxml",products);
    }

    /**
     *This methods gets the previous page of results by calling the searchProduct methiod and updatinh the global variable bounter with the correct
     * value of page number
     */
    @FXML
    private void getPreviousPage(ActionEvent event) throws IOException {
        counter-=2;
        searchProducts();
        counterLabel.setText(String.valueOf(counter));
        System.out.println(keyword);
        System.out.println("counter label" + counterLabel.getText());
        System.out.println("counter"+counter);
    }

    /**
     * This is the initialize method implemented by the Initializable interface. A change listener is created to act to the selection of a product
     * by the user and shows an image for this product
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        counter = 1;
        counterLabel.setText(String.valueOf(counter));
        PeviousPageButton.setVisible(false);
        resultCountLabel.setText("");
        detailsButton.setVisible(false);

    }
}
