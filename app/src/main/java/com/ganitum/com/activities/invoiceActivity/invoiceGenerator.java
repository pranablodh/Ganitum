package com.ganitum.com.activities.invoiceActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ganitum.com.R;
import com.ganitum.com.activities.purchaseActivity.skipPurchase;
import com.ganitum.com.activities.salesActivity.newSale;

public class invoiceGenerator extends AppCompatActivity
{

    //Clickable and Editable Elements
    private Button pdfShare;
    private ImageView back;
    private TextView product;
    private TextView quantity;
    private TextView price;
    private TextView gst;
    private TextView total;
    private TextView customerName;
    private TextView address;
    private TextView mobile;
    private TextView email;
    private TextView invoiceNumber;

    //Text Variable
    private String activityName = "";
    private String ProductName;
    private String hsn;
    private String Unit;
    private String Quantity;
    private String Price;
    private String GST;
    private String Total;
    private String SalePrice;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_generator);

        //Hiding Action Bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        //Reading Intent Values
        Intent invoice = getIntent();
        activityName = invoice.getStringExtra("activity_name");
        ProductName = invoice.getStringExtra("product_name");
        hsn = invoice.getStringExtra("hsn");
        Unit = invoice.getStringExtra("unit");
        Quantity = invoice.getStringExtra("quantity");
        Price = invoice.getStringExtra("price");
        GST = invoice.getStringExtra("gst");
        Total = invoice.getStringExtra("total");
        SalePrice = invoice.getStringExtra("salePrice");

        //UI Elements
        back = (ImageView) findViewById(R.id.back);
        pdfShare = (Button) findViewById(R.id.pdfShare);
        customerName = (TextView) findViewById(R.id.customerName);
        product = (TextView) findViewById(R.id.product);
        quantity = (TextView) findViewById(R.id.quantity);
        price = (TextView) findViewById(R.id.price);
        gst = (TextView) findViewById(R.id.gst);
        total = (TextView) findViewById(R.id.total);
        address = (TextView) findViewById(R.id.address);
        mobile = (TextView) findViewById(R.id.mobile);
        email = (TextView) findViewById(R.id.email);
        invoiceNumber = (TextView) findViewById(R.id.invoiceNumber);



        //On Click Listener
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                switch (activityName)
                {
                    case "newSale":
                        Intent goToNewSale = new Intent(invoiceGenerator.this, newSale.class);
                        startActivity(goToNewSale);
                        finish();
                        break;

                    case "skipPurchase" :
                        Intent goToskipPurchase = new Intent(invoiceGenerator.this, skipPurchase.class);
                        startActivity(goToskipPurchase);
                        finish();
                        break;

                     default:
                         break;
                }

            }
        });

        pdfShare.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            }
        });

        updateTextFields();
    }

    //Updating Text Field
    private void updateTextFields()
    {
        product.setText(ProductName);
        quantity.setText(Quantity);
        price.setText(Price);
        gst.setText(GST);
        total.setText(Total);
    }
}
