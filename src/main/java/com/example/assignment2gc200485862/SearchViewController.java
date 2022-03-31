package com.example.assignment2gc200485862;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    private void searchProducts()
    {
        ApiResponse apiResponse = ApiUtility.getProductsFromApi(keywordTextBox.getText(),1);
        resultListView.getItems().clear();
        if (apiResponse.getSearchProductDetails() != null)
        {
            resultListView.getItems().addAll(apiResponse.getSearchProductDetails());
            resultCountLabel.setText(String.valueOf(apiResponse.getNumberOfProducts()));
        }
        else
            resultCountLabel.setText("0");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
