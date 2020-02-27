package com.ganitum.com.recyclerViews.calculationDashBoard;

public class itemDataBinder
{
    private String productName = "";
    private String productPrice = "";
    private String urlImage = "";

    public itemDataBinder(String productName, String productPrice, String urlImage)
    {
        this.productName = productName;
        this.productPrice = productPrice;
        this.urlImage = urlImage;
    }

    public String getProductName()
    {
        return productName;
    }

    public String getProductPrice()
    {
        return productPrice;
    }

    public String getUrlImage()
    {
        return urlImage;
    }
}
