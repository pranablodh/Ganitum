package com.ganitum.com.activities.purchaseActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ganitum.com.R;

import java.time.Month;
import java.util.Calendar;

public class newPurchase extends AppCompatActivity implements DatePickerDialog.OnDateSetListener
{

    private ImageView back;
    private Button invoice;
    private TextView skip;
    private EditText name;
    private EditText invoiceNumber;
    private EditText dateOfPurchase;
    private EditText gstNumber;
    private EditText address;
    private EditText stateCode;
    private EditText mobile;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_purchase);

        //Hiding Action Bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        //UI Elements
        invoice = (Button) findViewById(R.id.invoice);
        back = (ImageView) findViewById(R.id.back);
        skip = (TextView) findViewById(R.id.skip);
        name = (EditText) findViewById(R.id.name);
        invoiceNumber = (EditText) findViewById(R.id.invoiceNumber);
        dateOfPurchase = (EditText) findViewById(R.id.dateOfPurchase);
        gstNumber = (EditText) findViewById(R.id.gstNumber);
        address = (EditText) findViewById(R.id.address);
        stateCode = (EditText) findViewById(R.id.sateCode);
        mobile = (EditText) findViewById(R.id.mobile);
        email = (EditText) findViewById(R.id.email);

        //On Click Listener
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToPurchase();
            }
        });

        skip.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToSkipPurchase();
            }
        });

        invoice.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                invoiceGenerator();
            }
        });

        dateOfPurchase.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean hasFocus)
            {
                if(hasFocus)
                {
                    showDatePickerDialog();
                }

                else
                {

                }
            }
        });
    }

    //Date Picker Dialog
    public void showDatePickerDialog()
    {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
    {
        int Month = month + 1;
        String date = dayOfMonth + "/" + Month + "/" + year;
        dateOfPurchase.setText(date);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        goToPurchase();
    }

    private void goToPurchase()
    {
        Intent goTOPurchase = new Intent(newPurchase.this, purchase.class);
        startActivity(goTOPurchase);
        finish();
    }

    private void goToSkipPurchase()
    {
        Intent goTOSkipPurchase = new Intent(newPurchase.this, skipPurchase.class);
        startActivity(goTOSkipPurchase);
        finish();
    }

    //validating Our Text Inputs
    private Boolean validateName()
    {
        if(String.valueOf(name.getText()).isEmpty())
        {
            name.setError(getResources().getString(R.string.enter_valid_name));
            return false;
        }

        if(name.getText().toString().trim().length() == 0)
        {
            name.setError(getResources().getString(R.string.enter_valid_name));
            return false;
        }

        else
        {
            name.setError(null);
            return true;
        }
    }

    private Boolean validateInvoiceNumber()
    {
        if(String.valueOf(invoiceNumber.getText()).isEmpty())
        {
            invoiceNumber.setError(getResources().getString(R.string.valid_invoice_number));
            return false;
        }

        if(invoiceNumber.getText().toString().trim().length() == 0)
        {
            invoiceNumber.setError(getResources().getString(R.string.valid_invoice_number));
            return false;
        }

        else
        {
            invoiceNumber.setError(null);
            return true;
        }
    }

    private Boolean validateDateOfPurchase()
    {
        if(String.valueOf(dateOfPurchase.getText()).isEmpty())
        {
            dateOfPurchase.setError(getResources().getString(R.string.valid_date_of_purchase));
            return false;
        }

        if(dateOfPurchase.getText().toString().trim().length() == 0)
        {
            dateOfPurchase.setError(getResources().getString(R.string.valid_date_of_purchase));
            return false;
        }

        else
        {
            dateOfPurchase.setError(null);
            return true;
        }
    }

    private Boolean validateGst()
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

        else
        {
            gstNumber.setError(null);
            return true;
        }
    }

    private Boolean validateAddress()
    {
        if(String.valueOf(address.getText()).isEmpty())
        {
            address.setError(getResources().getString(R.string.valid_address));
            return false;
        }

        if(address.getText().toString().trim().length() == 0)
        {
            address.setError(getResources().getString(R.string.valid_address));
            return false;
        }

        else
        {
            address.setError(null);
            return true;
        }
    }

    private Boolean validateStateCode()
    {
        if(String.valueOf(stateCode.getText()).isEmpty())
        {
            stateCode.setError(getResources().getString(R.string.valid_state_code));
            return false;
        }

        if(stateCode.getText().toString().trim().length() == 0)
        {
            stateCode.setError(getResources().getString(R.string.valid_state_code));
            return false;
        }

        else
        {
            stateCode.setError(null);
            return true;
        }
    }

    private Boolean validateMobile()
    {
        if(String.valueOf(mobile.getText()).isEmpty())
        {
            mobile.setError(getResources().getString(R.string.valid_mobile_number));
            return false;
        }

        if(mobile.getText().toString().trim().length() < 10)
        {
            mobile.setError(getResources().getString(R.string.valid_mobile_number));
            return false;
        }

        if(mobile.getText().toString().trim().length() == 0)
        {
            mobile.setError(getResources().getString(R.string.valid_mobile_number));
            return false;
        }

        else
        {
            mobile.setError(null);
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

    //Invoice Generator
    private void invoiceGenerator()
    {
        if(!validateName() | !validateAddress() | !validateDateOfPurchase() | !validateEmail() | !validateGst() |
            !validateInvoiceNumber() | !validateMobile() | !validateStateCode())
        {
            return;
        }
    }
}
