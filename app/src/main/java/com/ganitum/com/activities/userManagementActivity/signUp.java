package com.ganitum.com.activities.userManagementActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import com.ganitum.com.activities.miscellaneousFunctions.autoFillCitiesNames;
import com.ganitum.com.activities.miscellaneousFunctions.autoFillStateName;
import com.goodiebag.pinview.Pinview;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class signUp extends AppCompatActivity
{

    private TextView goToLogin;
    private Button submit;
    private EditText companyName;
    private EditText email;
    private EditText phoneNumber;
    private EditText gstNumber;
    private EditText address1;
    private EditText address2;
    private EditText pinCode;
    private AutoCompleteTextView city;
    private AutoCompleteTextView state;
    private final String registrationAPI = api.baseUrl + api.registrationApi;
    private final String otpVerificationAPI = api.baseUrl + api.verifyOTP;
    private final String otpResendAPI = api.baseUrl + api.resendOTP;


    //Variables for OTP Dialog
    private Dialog otpDialog;
    private TextView changePhoneNumber;
    private TextView resend_otp;
    private Button otpSubmit;
    private Pinview otpEnter;
    private String otpValue = "";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hiding Action Bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        //UI Elements
        goToLogin = (TextView) findViewById(R.id.goToLogin);
        submit = (Button) findViewById(R.id.submit);
        companyName = (EditText) findViewById(R.id.companyName);
        email = (EditText) findViewById(R.id.email);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        gstNumber = (EditText) findViewById(R.id.gstNumber);
        address1 = (EditText) findViewById(R.id.address1);
        address2 = (EditText) findViewById(R.id.address2);
        pinCode = (EditText) findViewById(R.id.pinCode);
        city = (AutoCompleteTextView) findViewById(R.id.city);
        state = (AutoCompleteTextView) findViewById(R.id.state);
        otpDialog = new Dialog(signUp.this);
        otpDialog.setContentView(R.layout.dialog_otp_registration);
        otpDialog.setCancelable(false);

        //Auto Complete text View Array Adapter
        ArrayAdapter<String> statesName = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoFillStateName.states);
        state.setThreshold(1);
        state.setAdapter(statesName);

        ArrayAdapter<String> citiesName = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoFillCitiesNames.cities);
        city.setThreshold(1);
        city.setAdapter(citiesName);

        //On Click Listener
        goToLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goTOLogin();
            }
        });

        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                registration();
            }
        });
    }

    private void goTOLogin()
    {
        Intent goBackToLogin = new Intent(signUp.this, loginMain.class);
        startActivity(goBackToLogin);
        finish();
    }

    //Registration Function
    private void registration()
    {
        if(!validateCompanyName() | !validateEmail() | !validateMobileNumber() | !validateGST() | !validateAddress1() | !validateAddress2()
            | !validatePinCode() | !validateCityName() | !validateStateName())
        {
            return;
        }

        httpRequestRegistration();

    }

    //HTTP Request for Registration
    private void httpRequestRegistration()
    {
        StringRequest registrationRequest = new StringRequest(Request.Method.POST, registrationAPI,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            JSONObject registrationResponse = new JSONObject(response);
                            String status = registrationResponse.getString("status");
                            String message = registrationResponse.getString("msg");
                            Log.d("test",status);
                            if(status.trim().toLowerCase().equals("success"))
                            {
                                showOtpDialog();
                            }

                            else
                            {
                                Toast.makeText(signUp.this,message,Toast.LENGTH_SHORT).show();
                            }
                        }

                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(signUp.this, String.valueOf(error),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            protected Map<String, String> getParams()
            {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("email", email.getText().toString());
                MyData.put("ph_no", phoneNumber.getText().toString());
                MyData.put("cmpy_nm",companyName.getText().toString());
                MyData.put("gst_no", gstNumber.getText().toString());
                MyData.put("add1", address1.getText().toString());
                MyData.put("add2",address2.getText().toString());
                MyData.put("pin", pinCode.getText().toString());
                MyData.put("city", city.getText().toString());
                MyData.put("state",state.getText().toString());
                return MyData;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(registrationRequest);
    }

    //HTTP Request for OTP Submission
    private void httpRequestOtpSubmission(final String OTP)
    {
        StringRequest otpVerificationRequest = new StringRequest(Request.Method.POST, otpVerificationAPI,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            JSONObject otpSubmissionResponse = new JSONObject(response);
                            String status = otpSubmissionResponse.getString("status");
                            String message = otpSubmissionResponse.getString("msg");

                            if(status.trim().toLowerCase().equals("success"))
                            {
                                goTOLogin();
                            }

                            else
                            {
                                Toast.makeText(signUp.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }

                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(signUp.this, String.valueOf(error),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            protected Map<String, String> getParams()
            {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("otp", OTP);
                MyData.put("ph_no", phoneNumber.getText().toString());
                return MyData;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(otpVerificationRequest);
    }

    //HTTP Request for OTP Resend
    private void httpRequestOtpResend()
    {
        StringRequest otpResend = new StringRequest(Request.Method.POST, otpResendAPI,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            JSONObject otpSubmissionResponse = new JSONObject(response);
                            String status = otpSubmissionResponse.getString("status");
                            String message = otpSubmissionResponse.getString("msg");

                            if(status.trim().toLowerCase().equals("success"))
                            {
                                Toast.makeText(signUp.this, message,Toast.LENGTH_SHORT).show();
                            }

                            else
                            {
                                Toast.makeText(signUp.this, message,Toast.LENGTH_SHORT).show();
                            }
                        }

                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(signUp.this, String.valueOf(error),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            protected Map<String, String> getParams()
            {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("ph_no", phoneNumber.getText().toString());
                return MyData;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(otpResend);
    }

    //Inflating the OTP Dialog
    private void showOtpDialog()
    {
        otpEnter = (Pinview) otpDialog.findViewById(R.id.otpEnter);
        changePhoneNumber = (TextView) otpDialog.findViewById(R.id.changePhoneNumber);
        resend_otp = (TextView) otpDialog.findViewById(R.id.resend_otp);
        otpSubmit = (Button) otpDialog.findViewById(R.id.otpSubmit);

        otpEnter.setPinViewEventListener(new Pinview.PinViewEventListener()
        {
            @Override
            public void onDataEntered(Pinview pinview, boolean fromUser)
            {
                otpValue = String.valueOf(pinview.getValue());
            }
        });


        changePhoneNumber.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                otpDialog.dismiss();
                phoneNumber.requestFocus();
            }
        });


        resend_otp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                httpRequestOtpResend();
            }
        });


        otpSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                httpRequestOtpSubmission(otpValue);
            }
        });

        otpDialog.show();
    }

    //Validating Text Fields
    private Boolean validateCompanyName()
    {
        if(String.valueOf(companyName.getText()).isEmpty())
        {
            companyName.setError(getResources().getString(R.string.valid_company_name));
            return false;
        }

        if(companyName.getText().toString().trim().length() == 0)
        {
            companyName.setError(getResources().getString(R.string.valid_company_name));
            return false;
        }

        else
        {
            companyName.setError(null);
            return true;
        }
    }

    private Boolean validateEmail()
    {
        if(String.valueOf(email.getText()).isEmpty())
        {
            email.setError(getResources().getString(R.string.valid_email));
            return false;
        }

        if(email.getText().toString().trim().length() == 0)
        {
            email.setError(getResources().getString(R.string.valid_email));
            return false;
        }

        if(!email.getText().toString().trim().contains("@"))
        {
            email.setError(getResources().getString(R.string.valid_email));
            return false;
        }

        if(!email.getText().toString().trim().contains("."))
        {
            email.setError(getResources().getString(R.string.valid_email));
            return false;
        }

        else
        {
            email.setError(null);
            return true;
        }
    }

    private Boolean validateMobileNumber()
    {
        if(String.valueOf(phoneNumber.getText()).isEmpty())
        {
            phoneNumber.setError(getResources().getString(R.string.valid_mobile_number));
            return false;
        }

        if(phoneNumber.getText().toString().trim().length() < 10)
        {
            phoneNumber.setError(getResources().getString(R.string.valid_mobile_number));
            return false;
        }

        if(phoneNumber.getText().toString().trim().length() == 0)
        {
            phoneNumber.setError(getResources().getString(R.string.valid_mobile_number));
            return false;
        }

        else
        {
            phoneNumber.setError(null);
            return true;
        }
    }

    private Boolean validateGST()
    {
        if(String.valueOf(gstNumber.getText()).isEmpty())
        {
            gstNumber.setError(getResources().getString(R.string.valid_gst));
            return false;
        }

        if(gstNumber.getText().toString().trim().length() == 0)
        {
            gstNumber.setError(getResources().getString(R.string.valid_gst));
            return false;
        }

        if(gstNumber.getText().toString().trim().length() < 15)
        {
            gstNumber.setError(getResources().getString(R.string.valid_gst));
            return false;
        }

        else
        {
            gstNumber.setError(null);
            return true;
        }
    }

    private Boolean validateAddress1()
    {
        if(String.valueOf(address1.getText()).isEmpty())
        {
            address1.setError(getResources().getString(R.string.valid_address));
            return false;
        }

        if(address1.getText().toString().trim().length() == 0)
        {
            address1.setError(getResources().getString(R.string.valid_address));
            return false;
        }

        else
        {
            address1.setError(null);
            return true;
        }
    }

    private Boolean validateAddress2()
    {
        if(String.valueOf(address2.getText()).isEmpty())
        {
            address2.setError(getResources().getString(R.string.valid_address));
            return false;
        }

        if(address2.getText().toString().trim().length() == 0)
        {
            address2.setError(getResources().getString(R.string.valid_address));
            return false;
        }

        else
        {
            address2.setError(null);
            return true;
        }
    }

    private Boolean validatePinCode()
    {
        if(String.valueOf(pinCode.getText()).isEmpty())
        {
            pinCode.setError(getResources().getString(R.string.valid_pin_code));
            return false;
        }

        if(pinCode.getText().toString().trim().length() < 6)
        {
            pinCode.setError(getResources().getString(R.string.valid_pin_code));
            return false;
        }

        if(pinCode.getText().toString().trim().length() == 0)
        {
            pinCode.setError(getResources().getString(R.string.valid_pin_code));
            return false;
        }

        else
        {
            pinCode.setError(null);
            return true;
        }
    }

    private Boolean validateCityName()
    {
        if(String.valueOf(city.getText()).isEmpty())
        {
            city.setError(getResources().getString(R.string.valid_city));
            return false;
        }

        if(city.getText().toString().trim().length() == 0)
        {
            city.setError(getResources().getString(R.string.valid_city));
            return false;
        }

        else
        {
            city.setError(null);
            return true;
        }
    }

    private Boolean validateStateName()
    {
        if(String.valueOf(state.getText()).isEmpty())
        {
            state.setError(getResources().getString(R.string.valid_state));
            return false;
        }

        if(state.getText().toString().trim().length() == 0)
        {
            state.setError(getResources().getString(R.string.valid_state));
            return false;
        }

        else
        {
            state.setError(null);
            return true;
        }
    }
}
