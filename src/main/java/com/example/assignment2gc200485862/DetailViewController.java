package com.example.assignment2gc200485862;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

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
    private Label URLLabel;
    @FXML
    private Label DeliverymsgLabel;
    @FXML
    private CheckBox PrimeCB;

    private ProductDetails product;

    /**This method will load dpUrl
     *
     */

   public void loadDetails(String asinID, String selectedProductDesc, double selectedPrice, double selectedRetailPrice, boolean selectedPrime, String selectedURL, String selectedDeliveryMessage, String selectedRating)
    {

        System.out.println("from Controller"+asinID);

        try{
            DescriptionLabel.setText(selectedProductDesc);
        }
        catch(Exception e)
        {
            DescriptionLabel.setText("No Description Available");
        }
        try{
            PriceLabel.setText(String.valueOf(selectedPrice));
        }
        catch(Exception e)
        {
            PriceLabel.setText("0");
        }
        try{
            RetailPriceLabel.setText(String.valueOf(selectedRetailPrice));
        }
        catch(Exception e)
        {
            RetailPriceLabel.setText("0");
        }
        try{
            ProductRating.setText(selectedRating);
        }
        catch(Exception e)
        {
            ProductRating.setText("No Rating Available");
        }

    try {
        if (selectedPrime==true)
            PrimeCB.setSelected(true);
        else
            PrimeCB.setSelected(false);
    }
    catch (Exception e)
    {
        PrimeCB.setSelected(false);

    }
        try{
           DeliverymsgLabel.setText(selectedDeliveryMessage);
       }
       catch(Exception e)
       {
           DeliverymsgLabel.setText("No Delivery Message");
       }
        try{
            if(selectedURL !=null) {
                URLLabel.setText("www.amazon.com" + selectedURL);
            }
            else
                URLLabel.setText("No URL to display");

        }
        catch(Exception e)
        {
            URLLabel.setText("No URL Detected");
        }



    }

}
