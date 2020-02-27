package com.ganitum.com.activities.userManagementActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ganitum.com.R;
import com.ganitum.com.activities.homePage;

public class loginWithMobile extends AppCompatActivity
{
    private TextView goToRegistration;
    private EditText mobileNumber;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_mobile);

        //Hiding Action Bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        //UI Elements
        goToRegistration = (TextView) findViewById(R.id.goToRegistration);
        mobileNumber = (EditText) findViewById(R.id.mobileNumber);
        submit = (Button) findViewById(R.id.submit);

        //On Click Listener
        goToRegistration.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                signUP();
            }
        });

        submit.setOnClickListener(new View.OnClickListener()
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
        goToLoginMain();
    }

    private void signUP()
    {
        Intent sign_up = new Intent(loginWithMobile.this, signUp.class);
        startActivity(sign_up);
        finish();
    }

    private void goToHomePage()
    {
        Intent homePage = new Intent(loginWithMobile.this, com.ganitum.com.activities.homePage.class);
        startActivity(homePage);
        finish();
    }

    private void goToLoginMain()
    {
        Intent LoginMain = new Intent(loginWithMobile.this, loginMain.class);
        startActivity(LoginMain);
        finish();
    }

    //Validating Text Inputs
    private Boolean validateMobileNumber()
    {
        if(String.valueOf(mobileNumber.getText()).isEmpty())
        {
            mobileNumber.setError(getResources().getString(R.string.valid_mobile_number));
            return false;
        }

        if(mobileNumber.getText().toString().trim().length() < 10)
        {
            mobileNumber.setError(getResources().getString(R.string.valid_mobile_number));
            return false;
        }

        if(mobileNumber.getText().toString().trim().length() == 0)
        {
            mobileNumber.setError(getResources().getString(R.string.valid_mobile_number));
            return false;
        }

        else
        {
            mobileNumber.setError(null);
            return true;
        }
    }
}
