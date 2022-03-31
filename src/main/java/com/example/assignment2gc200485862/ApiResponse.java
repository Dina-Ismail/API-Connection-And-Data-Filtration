package com.example.assignment2gc200485862;

public class ApiResponse {
    final String domainCode = "com";
    private String keyword;
    private int numberOfProducts;
    private int numberOfSponsoredProducts;
    private String[] foundSponsoredProducts;
    private String[] foundProducts;
    private ProductDetails[] searchProductDetails;
    private String[] categories;

    public String getDomainCode() {
        return domainCode;
    }

    public String getKeyword() {
        return keyword;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public int getNumberOfSponsoredProducts() {
        return numberOfSponsoredProducts;
    }

    public String[] getFoundSponsoredProducts() {
        return foundSponsoredProducts;
    }

    public String[] getFoundProducts() {
        return foundProducts;
    }

    public ProductDetails[] getSearchProductDetails() {
        return searchProductDetails;
    }

    public String[] getCategories() {
        return categories;
    }
}
