package com.ganitum.com.activities.userManagementActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.ganitum.com.R;
import com.ganitum.com.activities.miscellaneousFunctions.autoFillStateName;
import com.ganitum.com.activities.miscellaneousFunctions.autoFillCitiesNames;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

public class profileUpdate extends AppCompatActivity
{
    private ImageView back;
    private ImageView pictureSelector;
    private AlertDialog picChooseDialog;
    private EditText firstName;
    private EditText lastName;
    private EditText phoneNumber;
    private EditText email;
    private EditText address;
    private AutoCompleteTextView city;
    private AutoCompleteTextView state;
    private EditText gstNumber;
    private Button update;

    //Image Picker Elements
    private CharSequence[] values = {"Camera","Gallery"};
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_GALLERY = 1;
    private Bitmap bitmap;
    private Uri filePath;
    private String encodedImage = "";
    private String FinalEncodedImage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

        //Hiding Action Bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        //UI Elements
        back = (ImageView) findViewById(R.id.back);
        pictureSelector = (ImageView) findViewById(R.id.pictureSelector);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        email = (EditText) findViewById(R.id.email);
        address = (EditText) findViewById(R.id.address);
        city = (AutoCompleteTextView) findViewById(R.id.city);
        state = (AutoCompleteTextView) findViewById(R.id.state);
        gstNumber = (EditText) findViewById(R.id.gstNumber);
        update = (Button) findViewById(R.id.update);

        //Auto Complete text View Array Adapter
        ArrayAdapter<String> statesName = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoFillStateName.states);
        state.setThreshold(1);
        state.setAdapter(statesName);

        ArrayAdapter<String> citiesName = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoFillCitiesNames.cities);
        city.setThreshold(1);
        city.setAdapter(citiesName);

        //On Click Listener
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToHomePage();
            }
        });

        update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                updateDetails();
            }
        });

        pictureSelector.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                picChooserAlertDialog();
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
        Intent homePage = new Intent(profileUpdate.this, com.ganitum.com.activities.homePage.class);
        startActivity(homePage);
        finish();
    }

    //Picture Chooser Dialog
    private void picChooserAlertDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(profileUpdate.this);
        builder.setTitle(getResources().getString(R.string.select_image_from));
        builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int item)
            {

                switch(item)
                {
                    case 0:
                        pickImageFromCamera();
                        break;

                    case 1:
                        pickImageFromGallery();
                        break;
                }
                picChooseDialog.dismiss();
            }
        });
        picChooseDialog = builder.create();
        picChooseDialog.show();
    }

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
                pictureSelector.setImageBitmap(bitmap);
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
            pictureSelector.setImageBitmap(mphoto);
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

    //Validating Text Fields
    private Boolean validateFirstName()
    {
        if(String.valueOf(firstName.getText()).isEmpty())
        {
            firstName.setError(getResources().getString(R.string.valid_first_name));
            return false;
        }

        if(firstName.getText().toString().trim().length() == 0)
        {
            firstName.setError(getResources().getString(R.string.valid_first_name));
            return false;
        }

        else
        {
            firstName.setError(null);
            return true;
        }
    }

    private Boolean validateLastName()
    {
        if(String.valueOf(lastName.getText()).isEmpty())
        {
            lastName.setError(getResources().getString(R.string.valid_last_name));
            return false;
        }

        if(lastName.getText().toString().trim().length() == 0)
        {
            lastName.setError(getResources().getString(R.string.valid_last_name));
            return false;
        }

        else
        {
            lastName.setError(null);
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

    //Updating Details
    private void updateDetails()
    {
        if(!validateFirstName() | !validateLastName() | !validateMobileNumber() | !validateEmail() | !validateAddress() |
            !validateCityName() | !validateStateName() | !validateGST())
        {
            return;
        }
    }
}
