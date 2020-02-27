package com.ganitum.com.recyclerViews.purchase;

public class purchaseDataBinder
{
    private  String date = "";
    private  String customer = "";
    private  String amount = "";

    public purchaseDataBinder(String customer, String amount, String date)
    {
        this.date = date;
        this.customer = customer;
        this.amount = "₹" + amount;
    }

    public String getDate()
    {
        return  date;
    }

    public String getCustomer()
    {
        return  customer;
    }

    public String getAmount()
    {
        return amount;
    }
}
