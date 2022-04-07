package com.example.assignment2gc200485862;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchViewController implements Initializable {
    @FXML
    private TextField keywordTextBox;

    @FXML
    private Label resultCountLabel;

    @FXML
    private Label counterLabel;

    @FXML
    private CheckBox PrimeCB;

    @FXML
    private RadioButton Price30;

    @FXML
    private RadioButton Price60;

    @FXML
    private RadioButton Price100;

    @FXML
    private ToggleGroup yes;

    @FXML
    private ListView<ProductDetails> resultListView;

    @FXML
    private ProgressIndicator ProgressIndicator;

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
    {   keyword = keywordTextBox.getText();
        ProgressIndicator.setVisible(true);
        resultListView.getItems().clear();
        counter++;
        counterLabel.setText(String.valueOf(counter));
        if(counter>=3)
        {
            PeviousPageButton.setVisible(true);
        }
        else
            PeviousPageButton.setVisible(false);

        //this is the page number which will be sent next time.//i need to check how many result pages are they to disable the button
        System.out.println("counter"+counter);
        System.out.println("counter label"+counterLabel.getText());
        Thread firstLoadingThread = new Thread(new Runnable() {
        @Override
        public void run() {
            ApiResponse apiResponse = ApiUtility.getProductsFromApi(keywordTextBox.getText(),counter);
            double progress = 0;
            for (int i = 0; i < 10; i++) {
               progress += 0.1;
                //create a "final"  variable
                //to the Javafx thread
               final double reportedProgress = progress;
                //This is the JavaFX thread.  The method runLater() will execute
                //once the thread is available
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //Here this method will add all the results
                        if (reportedProgress >.4)
                        {
                            ProgressIndicator.setVisible(false);
                            try {
                                resultListView.getItems().clear();
                                if (apiResponse.getSearchProductDetails() != null)
                                {
                                    imageView.setVisible(false);
                                    resultListView.getSelectionModel().selectedItemProperty().addListener(
                                            (obs, oldProductSelected, newProductSelected) -> {
                                                ProgressIndicator.setVisible(true);
                                                //A new thread to  fetch the product image
//                                                Thread fetchPosterThread = new Thread(new Runnable() {
//                                                    @Override
//                                                    public void run() {
//                                                        double progress = 0;
//                                                        for (int counter = 0; counter < 10; counter++) {
//                                                            System.out.println("im inside the loop");
//                                                            ProgressIndicator.setVisible(true);
//                                                            //simulate the computer doing work
//                                                            progress += 0.1;
//                                                            System.out.println(progress);
//                                                            //create a "final" (non-changeable) variable to pass in the progress
//                                                            //to the Javafx thread
//                                                            final double reportedProgress = progress;
//                                                            //This is the JavaFX thread.  The method runLater() will execute
//                                                            //once the thread is available
//                                                            Platform.runLater(new Runnable() {
//                                                                @Override
//                                                                public void run() {
//                                                                    //the poster has been loaded
//                                                                    if (reportedProgress > .9)
//                                                                    {
//                                                                        ProgressIndicator.setVisible(false);
//                                                                        try {
//                                                                            imageView.setVisible(true);
//                                                                            imageView.setImage(new Image(newProductSelected.getImgUrl()));
//                                                                        }
//                                                                        catch (Exception e)
//                                                                        {
//                                                                            imageView.setVisible(false);
//                                                                            imageView.setImage(new Image("https://www.publicdomainpictures.net/pictures/280000/velka/not-found-image-15383864787lu.jpg"));
//                                                                        }
//                                                                    }
//                                                                    ProgressIndicator.setProgress(reportedProgress);
//                                                                }
//                                                            });
//                                                        }
//                                                    }
//                                                });
//                                                fetchPosterThread.start();
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
                                    //filtering list using streams to only show Prime products
                                    if(PrimeCB.isSelected())
                                    {
                                        ArrayList <ProductDetails> products = new ArrayList<>();
                                        products.addAll(List.of(apiResponse.getSearchProductDetails()));
                                        //add only those in the stream
                                        products.stream()
                                                .filter(p->p.isPrime()==true)
                                                .toList();
                                        resultCountLabel.setText(String.valueOf(products.stream().count()));
                                    }
                                    if(Price30.isSelected())
                                    {
                                        Price60.setSelected(false);
                                        Price100.setSelected(false);
                                        ArrayList <ProductDetails> products = new ArrayList<>();
                                        products.addAll(List.of(apiResponse.getSearchProductDetails()));
                                        //add only those in the stream
                                        products.stream()
                                                .filter(p->p.getPrice()<30)
                                                .toList();
                                        resultCountLabel.setText(String.valueOf(products.stream().count()));
                                    }
                                    if(Price60.isSelected())
                                    {
                                        Price30.setSelected(false);
                                        Price100.setSelected(false);
                                        ArrayList <ProductDetails> products = new ArrayList<>();
                                        products.addAll(List.of(apiResponse.getSearchProductDetails()));
                                        //add only those in the stream
                                        products.stream()
                                                .filter(p->p.getPrice()<60 && p.getPrice()>=30)
                                                .toList();
                                        resultCountLabel.setText(String.valueOf(products.stream().count()));
                                    }
                                    if(Price100.isSelected())
                                    {
                                        Price60.setSelected(false);
                                        Price30.setSelected(false);
                                        ArrayList <ProductDetails> products = new ArrayList<>();
                                        products.addAll(List.of(apiResponse.getSearchProductDetails()));
                                        //add only those in the stream
                                        products.stream()
                                                .filter(p->p.getPrice()<=100 && p.getPrice()>=60)
                                                .toList();
                                        resultCountLabel.setText(String.valueOf(products.stream().count()));
                                    }
                                    else {
                                        //else if not checked Add all
                                        resultListView.getItems().addAll(apiResponse.getSearchProductDetails());
                                        if(apiResponse.getNumberOfProducts()>0) {
                                            resultCountLabel.setText(String.valueOf(apiResponse.getNumberOfProducts()));
                                        }
                                        else
                                            resultCountLabel.setText("0");
                                    }
                                    detailsButton.setVisible(true);
                                }
                            }
                            catch (Exception e)
                            {
                                imageView.setVisible(false);
                                imageView.setImage(new Image("https://www.publicdomainpictures.net/pictures/280000/velka/not-found-image-15383864787lu.jpg"));
                            }
                        }
                    }
                });
            }
        }
    });
        firstLoadingThread.start();
    }
    /**
     * This method will be trigger by the "Yayy Show me the details!" button and will run the changeScene and pass the selected product object
     * with the new fxml file that needs to be launched.
     */
    @FXML
   private void getDetails(ActionEvent event) throws IOException {
        ProductDetails productsResultListView = resultListView.getSelectionModel().getSelectedItem();
        System.out.println(productsResultListView.getProductDescription());
        SceneChanger.changeScenes(event,"detail-view.fxml",productsResultListView);
    }

    /**
     *This methods gets the previous page of results by calling the searchProduct methiod and updatinh the global variable bounter with the correct
     * value of page number
     */
    @FXML
    private void getPreviousPage(ActionEvent event) throws IOException {
        System.out.println("counter from inside gretprevious method"+counter);
        if (counter > 3) {
            PeviousPageButton.setVisible(true);
            counter -= 2;
            searchProducts();
            counterLabel.setText(String.valueOf(counter));
            System.out.println(keyword);
            System.out.println("counter label" + counterLabel.getText());
            System.out.println("counter"+counter);
        } else {
            PeviousPageButton.setVisible(false);
        }
    }

    /**
     * This is the initialize method implemented by the Initializable interface. A change listener is created to act to the selection of a product
     * by the user and shows an image for this product
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProgressIndicator.setVisible(false);
        counter = 1;
        counterLabel.setText(String.valueOf(counter));
        PeviousPageButton.setVisible(false);
        resultCountLabel.setText("");
        detailsButton.setVisible(false);
    }
}
