package com.example.assignment2gc200485862;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DetailViewController {
    @FXML
    private TextArea DescriptionLabel;
    @FXML
    private Label PriceLabel;
    @FXML
    private Label RetailPriceLabel;
    @FXML
    private Label ProductRating;
    @FXML
    private Hyperlink URLLabel;
    @FXML
    private Label MessageLabel;
    @FXML
    private Label DeliverymsgLabel;
    @FXML
    private CheckBox PrimeCB;
    @FXML
    private ImageView imageView;

    private ProductDetails product;

    /**This method will load product object information to the right controls after
     *
     */
    public void loadDetails(ProductDetails products)
    {
        product=products;
        try{
            DescriptionLabel.setText(products.getProductDescription());
            DescriptionLabel.autosize();
        }
        catch(Exception e)
        {
            DescriptionLabel.setText("No Description Available");
        }
        try{
            PriceLabel.setText(String.valueOf(products.getPrice()));
        }
        catch(Exception e)
        {
            PriceLabel.setText("0");
        }
        try{
            RetailPriceLabel.setText(String.valueOf(products.getRetailPrice()));
        }
        catch(Exception e)
        {
            RetailPriceLabel.setText("0");
        }
        try{
            ProductRating.setText(products.getProductRating());
        }
        catch(Exception e)
        {
            ProductRating.setText("No Rating Available");
        }
        try {
            if (products.isPrime()==true)
                PrimeCB.setSelected(true);
            else
                 PrimeCB.setSelected(false);
        }
        catch (Exception e)
        {
            PrimeCB.setSelected(false);
        }
        try{
            DeliverymsgLabel.setText(products.getDeliveryMessage());
        }
        catch(Exception e)
        {
            DeliverymsgLabel.setText("No Delivery Message");
        }
        try{
            if(products.getDpUrl() !=null) {
                URLLabel.setText("www.amazon.com" + products.getDpUrl());
            }
            else
                URLLabel.setText("No URL to display");
        }
        catch(Exception e)
        {
            URLLabel.setText("No URL Detected");
        }
        try {
            imageView.setImage(new Image(products.getImgUrl()));
        }
        catch (IllegalArgumentException e)
        {
            imageView.setImage(new Image("https://www.publicdomainpictures.net/pictures/280000/velka/not-found-image-15383864787lu.jpg"));
        }

    }//end of LoadDetails

    /** This method loads the table view and set chartYear variable to the corresponding
     * year viewed by the user to reflect Data table
     * @param event
     * @throws IOException
     */
    @FXML
    private void loadSearchView(ActionEvent event) throws IOException {
        SceneChanger.ResultViewchangeScenes(event, "search-view.fxml");
    }
    /** This method makes the hyperlink opens the link to the product URL
     */
    @FXML
    private void openLink(ActionEvent event) throws IOException, URISyntaxException {
        System.out.println("link is clicked");
        try {
            Desktop.getDesktop().browse(new URI("www.amazon.com" + product.getDpUrl()));
        }
        catch(Exception e) {
            MessageLabel.setText("URL is not accessible, please try to copy and paste it in your browser");
        }
    }
}
