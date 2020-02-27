package com.ganitum.com.recyclerViews.customer;

public class customerNameDataBinder
{
    private  String customerName = "";

    public customerNameDataBinder(String customerName)
    {
        this.customerName = customerName;
    }

    public String getCustomerName()
    {
        return  customerName;
    }
}
