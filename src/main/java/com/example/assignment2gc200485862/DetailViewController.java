package com.example.assignment2gc200485862;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class DetailViewController {
    @FXML
    private Label DescriptionLabel;
    @FXML
    private Label PriceLabel;
    @FXML
    private Label RetailPriceLabel;
    @FXML
    private Label ProductRating;
    @FXML
    private Label URLLabel;
    @FXML
    private Label DeliverymsgLabel;
    @FXML
    private CheckBox PrimeCB;

    private ProductDetails product;

    /**This method will load asinID
     *
     */

    public void loadDetails(String asinID)
    {
        System.out.println("from Controller"+asinID);
        //product = ApiUtility.getProductDetails(asinID);
    }

}
