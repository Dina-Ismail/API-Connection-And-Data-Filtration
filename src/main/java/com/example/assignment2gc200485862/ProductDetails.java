package com.example.assignment2gc200485862;

public class ProductDetails {
    private String asin;
    private String productDescription;
    private int countReview;
    private String imgUrl;
    private double price;
    private String productRating;
    private boolean prime;
    private String dpUrl;
    private String deliveryMessage;
    private double retailPrice;

    public String getAsin() {
        return asin;
    }

    public String getProductDescription() {
        return productDescription.toString();
    }

    public int getCountReview() {
        return countReview;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public double getPrice() {
        return price;
    }

    public String getProductRating() {
        return productRating;
    }

    public boolean isPrime() {
        return prime;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public String getDpUrl() {
        return dpUrl;
    }

    public String getDeliveryMessage() {
        return deliveryMessage;
    }

    @Override
    public String toString() {
        return "** "+ productDescription;
    }
}
