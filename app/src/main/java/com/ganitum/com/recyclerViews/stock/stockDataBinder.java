package com.ganitum.com.recyclerViews.stock;

public class stockDataBinder
{
    private  String productName = "";
    private  String amount = "";

    public stockDataBinder(String productName, String amount)
    {
        this.productName = productName;
        this.amount = amount;
    }

    public String getProductName()
    {
        return productName;
    }

    public String getQuantity()
    {
        return  amount;
    }
}
