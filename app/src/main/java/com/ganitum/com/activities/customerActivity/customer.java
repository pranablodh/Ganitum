package com.ganitum.com.activities.customerActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ganitum.com.R;
import com.ganitum.com.activities.purchaseActivity.purchase;
import com.ganitum.com.recyclerViews.customer.creditAdapter;
import com.ganitum.com.recyclerViews.customer.creditDataBinder;
import com.ganitum.com.recyclerViews.customer.customerNameAdapter;
import com.ganitum.com.recyclerViews.customer.customerNameDataBinder;
import com.ganitum.com.recyclerViews.customer.topBuyerAdapter;
import com.ganitum.com.recyclerViews.customer.topBuyerDataBinder;
import com.ganitum.com.recyclerViews.purchase.purchaseDataBinder;

import java.util.ArrayList;
import java.util.List;

public class customer extends AppCompatActivity
{

    private ImageView back;
    private TextView addCustomer;
    private List<creditDataBinder> creditList;
    private List<customerNameDataBinder> customerList;
    private List<topBuyerDataBinder> topBuyerList;
    RecyclerView recyclerViewCustomerName;
    RecyclerView recyclerViewTopBuyer;
    RecyclerView recyclerViewCredit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        //Hiding Action Bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        //UI Elements
        back = (ImageView) findViewById(R.id.back);
        addCustomer = (TextView) findViewById(R.id.addCustomer);
        
        //Recycler View Elements
        recyclerViewCustomerName = (RecyclerView) findViewById(R.id.customerName);
        recyclerViewCustomerName.setHasFixedSize(true);
        recyclerViewCustomerName.setLayoutManager(new LinearLayoutManager(customer.this));

        recyclerViewTopBuyer = (RecyclerView) findViewById(R.id.topBuyer);
        recyclerViewTopBuyer.setHasFixedSize(true);
        recyclerViewTopBuyer.setLayoutManager(new LinearLayoutManager(customer.this));

        recyclerViewCredit = (RecyclerView) findViewById(R.id.credit);
        recyclerViewCredit.setHasFixedSize(true);
        recyclerViewCredit.setLayoutManager(new LinearLayoutManager(customer.this));

        //On Click Listener
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToHomePage();
            }
        });

        addCustomer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToAddCustomer();
            }
        });

        inflateRecyclerViewCustomerName();
        inflateRecyclerViewTopBuyer();
        inflateRecyclerViewCredit();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        goToHomePage();
    }

    private void goToHomePage()
    {
        Intent homePage = new Intent(customer.this, com.ganitum.com.activities.homePage.class);
        startActivity(homePage);
        finish();
    }

    private void goToAddCustomer()
    {
        Intent addCustomerPage = new Intent(customer.this, com.ganitum.com.activities.customerActivity.addCustomer.class);
        startActivity(addCustomerPage);
        finish();
    }
    
    //Inflating Recycler View
    private void inflateRecyclerViewCustomerName()
    {
        customerList = new ArrayList<>();

        customerList.add(new customerNameDataBinder("Aniruddha"));

        customerList.add(new customerNameDataBinder("Pranab"));

        customerList.add(new customerNameDataBinder("Arpan"));

        customerList.add(new customerNameDataBinder("Tanmay"));

        customerList.add(new customerNameDataBinder("Gurucharan"));

        customerList.add(new customerNameDataBinder("Suman"));

        customerList.add(new customerNameDataBinder("Sovan"));

        customerList.add(new customerNameDataBinder("Sivprakash"));

        customerList.add(new customerNameDataBinder("Ajoy"));

        customerNameAdapter cNameAdapter =   new customerNameAdapter(customer.this,customerList);
        recyclerViewCustomerName.setAdapter(cNameAdapter);
    }
    
    private void inflateRecyclerViewTopBuyer()
    {
        topBuyerList = new ArrayList<>();

        topBuyerList.add(new topBuyerDataBinder("Aniruddha","130000"));

        topBuyerList.add(new topBuyerDataBinder("Pranab","130000"));

        topBuyerList.add(new topBuyerDataBinder("Arpan","130000"));

        topBuyerList.add(new topBuyerDataBinder("Tanmay","130000"));

        topBuyerList.add(new topBuyerDataBinder("Gurucharan","130000"));

        topBuyerList.add(new topBuyerDataBinder("Suman","130000"));

        topBuyerList.add(new topBuyerDataBinder("Sovan","130000"));

        topBuyerList.add(new topBuyerDataBinder("Sivprakash","130000"));

        topBuyerList.add(new topBuyerDataBinder("Ajoy","130000"));

        topBuyerAdapter tBuyerAdapter =   new topBuyerAdapter(customer.this,topBuyerList);
        recyclerViewTopBuyer.setAdapter(tBuyerAdapter);
    }
    
    private void inflateRecyclerViewCredit()
    {
        creditList = new ArrayList<>();

        creditList.add(new creditDataBinder("Aniruddha","130000"));

        creditList.add(new creditDataBinder("Pranab","130000"));

        creditList.add(new creditDataBinder("Arpan","130000"));

        creditList.add(new creditDataBinder("Tanmay","130000"));

        creditList.add(new creditDataBinder("Gurucharan","130000"));

        creditList.add(new creditDataBinder("Suman","130000"));

        creditList.add(new creditDataBinder("Sovan","130000"));

        creditList.add(new creditDataBinder("Sivprakash","130000"));

        creditList.add(new creditDataBinder("Ajoy","130000"));

        creditAdapter CreditAdapter =   new creditAdapter(customer.this,creditList);
        recyclerViewCredit.setAdapter(CreditAdapter);
    }
}
