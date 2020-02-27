package com.ganitum.com.activities.bankingActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ganitum.com.R;
import com.ganitum.com.activities.purchaseActivity.purchase;

public class sendMoney extends AppCompatActivity
{

    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        //Hiding Action Bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        //UI Elements
        back = (ImageView) findViewById(R.id.back);

        //On Click Listener
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToHomePage();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        goToHomePage();
    }

    private void goToHomePage()
    {
        Intent homePage = new Intent(sendMoney.this, com.ganitum.com.activities.homePage.class);
        startActivity(homePage);
        finish();
    }
}
