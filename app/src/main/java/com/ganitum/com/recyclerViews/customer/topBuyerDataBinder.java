package com.ganitum.com.recyclerViews.customer;

public class topBuyerDataBinder
{
    private String topCustomerName = "";
    private String topCustomerAmount = "";

    public topBuyerDataBinder(String topCustomerName, String topCustomerAmount)
    {
        this.topCustomerName = topCustomerName;
        this.topCustomerAmount = "₹" +  topCustomerAmount;
    }

    public String getTopCustomerName()
    {
        return  topCustomerName;
    }

    public String getTopCustomerAmount()
    {
        return topCustomerAmount;
    }
}
