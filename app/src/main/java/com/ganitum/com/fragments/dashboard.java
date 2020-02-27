package com.ganitum.com.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ganitum.com.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 */
public class dashboard extends Fragment
{

    private ImageView profileUpdate;
    private ImageView sendMoney;
    private LinearLayout customer;
    private LinearLayout stock;
    private LinearLayout purchase;
    private LinearLayout sale;
    private TextView date;

    public dashboard()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //UI Elements
        profileUpdate = (ImageView) view.findViewById(R.id.profileUpdate);
        sendMoney = (ImageView) view.findViewById(R.id.sendMoney);
        customer = (LinearLayout) view.findViewById(R.id.customer);
        stock = (LinearLayout) view.findViewById(R.id.stock);
        purchase = (LinearLayout) view.findViewById(R.id.purchase);
        sale = (LinearLayout) view.findViewById(R.id.sale);
        date = (TextView) view.findViewById(R.id.date);

        profileUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openProfileUpdate();
            }
        });

        sendMoney.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openSendMoney();
            }
        });

        customer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToCustomer();
            }
        });

        stock.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToStock();
            }
        });

        purchase.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToPurchase();
            }
        });

        sale.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToSale();
            }
        });

        updateDateTime();

        return view;
    }

    private void openProfileUpdate()
    {
        Intent profileUpdating = new Intent(getActivity(), com.ganitum.com.activities.userManagementActivity.profileUpdate.class);
        startActivity(profileUpdating);
        Objects.requireNonNull(getActivity()).finish();
    }

    private void goToCustomer()
    {
        Intent goTOCustomer = new Intent(getActivity(), com.ganitum.com.activities.customerActivity.customer.class);
        startActivity(goTOCustomer);
        Objects.requireNonNull(getActivity()).finish();
    }

    private void goToStock()
    {
        Intent goTOStock = new Intent(getActivity(), com.ganitum.com.activities.stockActivity.stock.class);
        startActivity(goTOStock);
        Objects.requireNonNull(getActivity()).finish();
    }

    private void goToPurchase()
    {
        Intent goTOPurchase = new Intent(getActivity(), com.ganitum.com.activities.purchaseActivity.purchase.class);
        startActivity(goTOPurchase);
        Objects.requireNonNull(getActivity()).finish();
    }

    private void goToSale()
    {
        Intent goTOSALE = new Intent(getActivity(), com.ganitum.com.activities.salesActivity.sale.class);
        startActivity(goTOSALE);
        Objects.requireNonNull(getActivity()).finish();
    }

    private void openSendMoney()
    {
        Intent OpenSendMoney = new Intent(getActivity(), com.ganitum.com.activities.bankingActivity.sendMoney.class);
        startActivity(OpenSendMoney);
        Objects.requireNonNull(getActivity()).finish();
    }

    private void updateDateTime()
    {
        Calendar calendar = Calendar.getInstance();
        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        format = new SimpleDateFormat("EEEE"+"\n"+"dd MMMM\nyyyy", Locale.ENGLISH);
        String formatted_date  = format.format(curDate);
        date.setText(formatted_date);
    }

    //Flipper Image Change
    private void changeFlipperItems()
    {

    }
}