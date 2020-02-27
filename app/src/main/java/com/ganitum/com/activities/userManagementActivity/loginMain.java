package com.ganitum.com.activities.userManagementActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ganitum.com.R;
import com.ganitum.com.activities.miscellaneousFunctions.api;

import java.util.HashMap;
import java.util.Map;

public class loginMain extends AppCompatActivity
{

    private TextView createAccount;
    private EditText userName;
    private EditText password;
    private Button login;
    private Button loginByMobile;
    private String loginAPI = api.baseUrl + api.loginAPI;
    private String otp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        //Hiding Action Bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        //UI Elements
        createAccount = (TextView) findViewById(R.id.createAccount);
        login = (Button) findViewById(R.id.login);
        loginByMobile = (Button) findViewById(R.id.loginByMobile);
        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);

        //On Click Listener
        createAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                signUP();
            }
        });

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(!validateUserName() | !validatePassword())
                {
                    return;
                }

                //httpRequest();
                goToHomePage();
            }
        });

        loginByMobile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                loginMobile();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }

    private void signUP()
    {
        Intent sign_up = new Intent(loginMain.this, signUp.class);
        startActivity(sign_up);
    }

    private void goToHomePage()
    {
        Intent homePage = new Intent(loginMain.this, com.ganitum.com.activities.homePage.class);
        startActivity(homePage);
    }

    private void loginMobile()
    {
        Intent mobileLogin = new Intent(loginMain.this, loginWithMobile.class);
        startActivity(mobileLogin);
    }

    //Validating Our Text Inputs
    private Boolean validateUserName()
    {
        if(String.valueOf(userName.getText()).isEmpty())
        {
            userName.setError(getResources().getString(R.string.valid_user_name));
            return false;
        }

        if(userName.getText().toString().trim().length() == 0)
        {
            userName.setError(getResources().getString(R.string.valid_user_name));
            return false;
        }

        else
        {
            userName.setError(null);
            return true;
        }
    }

    private Boolean validatePassword()
    {
        if(String.valueOf(password.getText()).trim().isEmpty())
        {
            password.setError(getResources().getString(R.string.valid_password_entry));
            return false;
        }

        if(password.getText().toString().trim().length() == 0)
        {
            password.setError(getResources().getString(R.string.valid_password_entry));
            return false;
        }

        else
        {
            password.setError(null);
            return true;
        }
    }

    //HTTP Request
    private void httpRequest()
    {
        StringRequest loginRequest = new StringRequest(Request.Method.POST, loginAPI,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        if(response.trim().equals("success"))
                        {
                            userName.setError(null);
                            password.setError(null);
                            goToHomePage();
                        }

                        else
                        {
                            userName.setError(getResources().getString(R.string.valid_user_name));
                            password.setError(getResources().getString(R.string.valid_password_entry));
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(loginMain.this, String.valueOf(error),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            protected Map<String, String> getParams()
            {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("id", userName.getText().toString());
                MyData.put("name", password.getText().toString());
                MyData.put("otp",otp);
                return MyData;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(loginRequest);
    }
}
