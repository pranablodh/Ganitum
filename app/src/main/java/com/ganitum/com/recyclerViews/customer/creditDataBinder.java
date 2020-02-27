package com.ganitum.com.recyclerViews.customer;

public class creditDataBinder
{
    private String creditCustomerName = "";
    private String creditCustomerAmount = "";

    public creditDataBinder(String creditCustomerName, String creditCustomerAmount)
    {
        this.creditCustomerName = creditCustomerName;
        this.creditCustomerAmount = "₹" +  creditCustomerAmount;
    }

    public String getCreditCustomerName()
    {
        return  creditCustomerName;
    }

    public String getCreditCustomerAmount()
    {
        return creditCustomerAmount;
    }
}
