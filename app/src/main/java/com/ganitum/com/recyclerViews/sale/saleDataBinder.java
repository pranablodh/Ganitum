package com.ganitum.com.recyclerViews.sale;

public class saleDataBinder
{
    private  String date = "";
    private  String customer = "";
    private  String amount = "";

    public saleDataBinder(String customer, String amount, String date)
    {
        this.date = date;
        this.customer = customer;
        this.amount = "₹" +  amount;
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
