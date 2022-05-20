package com.example.onlineshopping;

public class ProductList {
    int image;
    String companyName;
    String productName;
    int amount;


    public ProductList(int image, String companyName, String productName, int amount) {
        this.image = image;
        this.companyName = companyName;
        this.productName = productName;
        this.amount = amount;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
