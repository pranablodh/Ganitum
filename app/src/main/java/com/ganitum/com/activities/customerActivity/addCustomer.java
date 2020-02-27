package com.ganitum.com.activities.customerActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ganitum.com.R;
import com.ganitum.com.activities.salesActivity.newSale;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import yuku.ambilwarna.AmbilWarnaDialog;

public class addCustomer extends AppCompatActivity
{

    //UI Elements
    private ImageView back;
    private ImageView cameraPicker;
    private ImageView galleryPicker;
    private ImageView customerPicture;
    private ImageView colorPicker;
    private Button add;
    private EditText name;
    private EditText gstNumber;
    private EditText address;
    private EditText pinCode;
    private EditText stateCode;
    private EditText phoneNumber;
    private EditText email;


    //Image Picker Elements
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_GALLERY = 1;
    private Bitmap bitmap;
    private Uri filePath;
    private String encodedImage;
    private String FinalEncodedImage = "";
    private int DefaultColor;
    private final int bitmapWidth = 768;
    private final int bitmapHeight = 1366;

    //EditText Values Holder
    private String Name = "";
    private String gstNo = "";
    private String Address = "";
    private String pin = "";
    private String StateCode = "";
    private String Mobile = "";
    private String Email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        //Hiding Action Bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        //UI Elements
        back = (ImageView) findViewById(R.id.back);
        cameraPicker = (ImageView) findViewById(R.id.cameraPicker);
        galleryPicker = (ImageView) findViewById(R.id.galleryPicker);
        customerPicture = (ImageView) findViewById(R.id.customerPicture);
        colorPicker = (ImageView) findViewById(R.id.colorPicker);
        add = (Button) findViewById(R.id.add);
        name = (EditText) findViewById(R.id.name);
        gstNumber = (EditText) findViewById(R.id.gstNumber);
        address = (EditText) findViewById(R.id.address);
        pinCode = (EditText) findViewById(R.id.pinCode);
        stateCode = (EditText) findViewById(R.id.stateCode);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        email = (EditText) findViewById(R.id.email);

        //On Click Listener
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToCustomer();
            }
        });

        colorPicker.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openColorPickerDialog(false);
            }
        });

        cameraPicker.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                pickImageFromCamera();
            }
        });

        galleryPicker.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                pickImageFromGallery();
            }
        });

        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AddCustomer();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        goToCustomer();
    }

    private void goToCustomer()
    {
        Intent customerPage = new Intent(addCustomer.this, customer.class);
        startActivity(customerPage);
        finish();
    }

    //Validating Text Fields
    private Boolean validateName()
    {
        if(String.valueOf(name.getText()).isEmpty())
        {
            name.setError(getResources().getString(R.string.enter_valid_name));
            return false;
        }

        if(name.getText().toString().trim().length() == 0)
        {
            name.setError(getResources().getString(R.string.valid_product_name));
            return false;
        }

        else
        {
            name.setError(null);
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

    private Boolean validatePinCode()
    {
        if(String.valueOf(pinCode.getText()).isEmpty())
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

    //Color Picker Dialog and Making Bitmap
    private void openColorPickerDialog(boolean AlphaSupport)
    {
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, DefaultColor, AlphaSupport,
                new AmbilWarnaDialog.OnAmbilWarnaListener()
                {
                    @Override
                    public void onOk(AmbilWarnaDialog ambilWarnaDialog, int color)
                    {
                        DefaultColor = color;
                        customerPicture.setImageDrawable(null);
                        makeBitmap(DefaultColor);
                    }

                    @Override
                    public void onCancel(AmbilWarnaDialog ambilWarnaDialog)
                    {
                        DefaultColor = ContextCompat.getColor(addCustomer.this, R.color.colorPrimary);
                        customerPicture.setImageDrawable(null);
                        makeBitmap(DefaultColor);
                    }
                });
        ambilWarnaDialog.show();
    }

    private void makeBitmap(int color)
    {
        Bitmap image=Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
        image.eraseColor(color);
        customerPicture.setImageBitmap(image);
        FinalEncodedImage = base64Encoding(image);
    }
    //End of Color Picker Dialog and Making Bitmap

    //Image Picker
    private void pickImageFromGallery()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_FROM_GALLERY);
    }

    private void pickImageFromCamera()
    {
        Intent intentCamera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentCamera, PICK_FROM_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            filePath = data.getData();
            try
            {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(filePath));
                customerPicture.setImageBitmap(bitmap);
                FinalEncodedImage = base64Encoding(bitmap);
            }

            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        if (requestCode == PICK_FROM_CAMERA && resultCode == RESULT_OK)
        {
            Bitmap mphoto = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
            assert mphoto != null;
            customerPicture.setImageBitmap(mphoto);
            FinalEncodedImage = base64Encoding(mphoto);
        }
    }

    private String base64Encoding(Bitmap mphoto)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        assert mphoto != null;
        mphoto.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    //End of Image Picker and Base64 Encoding

    //Add Customer
    private void AddCustomer()
    {
        if(!validateName() | !validateGST() | !validateAddress() | !validatePinCode() | !validateStateCode()
                | !validateMobileNumber() | !validateEmail())
        {
            return;
        }
    }
}
