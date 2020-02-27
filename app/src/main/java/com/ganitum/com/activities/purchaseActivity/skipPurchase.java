package com.ganitum.com.activities.purchaseActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ganitum.com.R;
import com.ganitum.com.activities.invoiceActivity.invoiceGenerator;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;

import yuku.ambilwarna.AmbilWarnaDialog;

public class skipPurchase extends AppCompatActivity
{

    //Clickable and Editable Elements
    private ImageView back;
    private ImageView cameraPicker;
    private ImageView galleryPicker;
    private ImageView customerPicture;
    private ImageView colorPicker;
    private Button next;
    private Button done;
    private EditText productName;
    private EditText HSN;
    private EditText unit;
    private EditText quantity;
    private EditText price;
    private EditText gst;
    private EditText total;
    private EditText salePrice;

    //Image Picker Elements
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_GALLERY = 1;
    private Bitmap bitmap;
    private Uri filePath;
    private String encodedImage = "";
    private String FinalEncodedImage = "";
    int DefaultColor;
    private final int bitmapWidth = 768;
    private final int bitmapHeight = 1366;
    private int barCodeScanFlag = 0;

    //Edit Text Variable
    private String ProductName = "";
    private String hsn = "";
    private String Unit = "";
    private String Quantity = "";
    private String Price = "";
    private String GST = "";
    private String Total = "";
    private String SalePrice = "";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skip_purchase);

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
        done = (Button) findViewById(R.id.done);
        next = (Button) findViewById(R.id.next);
        HSN = (EditText) findViewById(R.id.HSN);
        productName = (EditText) findViewById(R.id.productName);
        unit = (EditText) findViewById(R.id.unit);
        quantity = (EditText) findViewById(R.id.quantity);
        price = (EditText) findViewById(R.id.price);
        gst = (EditText) findViewById(R.id.gst);
        total = (EditText) findViewById(R.id.total);
        salePrice = (EditText) findViewById(R.id.salePrice);

        //On Click Listener
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToNewPurchase();
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

        done.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                invoiceDataSet(0);
            }
        });

        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                invoiceDataSet(1);
            }
        });

        HSN.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean hasFocus)
            {
                if(hasFocus)
                {
                    barCodeScanFlag = 1;
                    scanQRCode();
                }

                else
                {
                    barCodeScanFlag = 0;
                }
            }
        });

        gst.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean hasFocus)
            {
                if(!hasFocus)
                {
                    calculateTotalPrice();
                }
            }
        });

        total.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean hasFocus)
            {
                if(!hasFocus)
                {
                    validateTotal();
                }
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        goToNewPurchase();
    }

    private void goToNewPurchase()
    {
        Intent goTONewPurchase = new Intent(skipPurchase.this, newPurchase.class);
        startActivity(goTONewPurchase);
        finish();
    }

    private void invoiceDataSet(int flag)
    {
        if(!validateProductName() | !validateHSN() | !validateQuantity() | !validatePrice() | !validateUnit() |
                !validateTotal() | !validateSalePrice() | !validateGST())
        {
            return;
        }

        ProductName = productName.getText().toString();
        hsn = HSN.getText().toString();
        Unit = unit.getText().toString();
        Quantity = quantity.getText().toString();
        Price = price.getText().toString();
        GST = gst.getText().toString();
        Total = total.getText().toString();
        SalePrice = salePrice.getText().toString();

        if(flag == 0)
        {
            httpRequest();
        }

        else if(flag == 1)
        {
            goToInvoice();
        }
    }

    private void goToInvoice()
    {
        Intent goTOInvoice = new Intent(skipPurchase.this, invoiceGenerator.class);
        goTOInvoice.putExtra("activity_name","skipPurchase");
        goTOInvoice.putExtra("product_name",ProductName);
        goTOInvoice.putExtra("hsn",hsn);
        goTOInvoice.putExtra("unit",Unit);
        goTOInvoice.putExtra("quantity",Quantity);
        goTOInvoice.putExtra("price",Price);
        goTOInvoice.putExtra("gst",GST);
        goTOInvoice.putExtra("total",Total);
        goTOInvoice.putExtra("salePrice",SalePrice);
        startActivity(goTOInvoice);
        finish();
    }

    private void calculateTotalPrice()
    {
        if(!validateProductName() | !validateHSN() | !validateQuantity() | !validatePrice() | !validateUnit() | !validateGST())
        {
            return;
        }

        double totalPrice = Double.parseDouble(quantity.getText().toString().trim()) *
                            Double.parseDouble(price.getText().toString().trim()) *
                            ((Double.parseDouble(gst.getText().toString().trim())+100.0) / 100.0);

        total.setText(new DecimalFormat("##.##").format(totalPrice));
    }

    //Validating Text Fields
    private Boolean validateProductName()
    {
        if(String.valueOf(productName.getText()).isEmpty())
        {
            productName.setError(getResources().getString(R.string.valid_product_name));
            return false;
        }

        if(productName.getText().toString().trim().length() == 0)
        {
            productName.setError(getResources().getString(R.string.valid_product_name));
            return false;
        }

        else
        {
            productName.setError(null);
            return true;
        }
    }

    private Boolean validateHSN()
    {
        if(String.valueOf(HSN.getText()).isEmpty())
        {
            HSN.setError(getResources().getString(R.string.valid_hsn));
            return false;
        }

        if(HSN.getText().toString().trim().length() == 0)
        {
            HSN.setError(getResources().getString(R.string.valid_hsn));
            return false;
        }

        else
        {
            HSN.setError(null);
            return true;
        }
    }

    private Boolean validateUnit()
    {
        if(String.valueOf(unit.getText()).isEmpty())
        {
            unit.setError(getResources().getString(R.string.non_empty_unit));
            return false;
        }

        if(unit.getText().toString().trim().length() == 0)
        {
            unit.setError(getResources().getString(R.string.non_empty_unit));
            return false;
        }

        else
        {
            unit.setError(null);
            return true;
        }
    }

    private Boolean validateQuantity()
    {
        if(String.valueOf(quantity.getText()).isEmpty())
        {
            quantity.setError(getResources().getString(R.string.non_empty_quantity));
            return false;
        }

        if(quantity.getText().toString().trim().length() == 0)
        {
            quantity.setError(getResources().getString(R.string.non_empty_quantity));
            return false;
        }

        else
        {
            quantity.setError(null);
            return true;
        }
    }

    private Boolean validatePrice()
    {
        if(String.valueOf(price.getText()).isEmpty())
        {
            price.setError(getResources().getString(R.string.valid_price));
            return false;
        }

        if(price.getText().toString().trim().length() == 0)
        {
            price.setError(getResources().getString(R.string.valid_price));
            return false;
        }

        else
        {
            price.setError(null);
            return true;
        }
    }

    private Boolean validateGST()
    {
        if(String.valueOf(gst.getText()).isEmpty())
        {
            gst.setError(getResources().getString(R.string.non_empty_gst));
            return false;
        }

        if(gst.getText().toString().trim().length() == 0)
        {
            gst.setError(getResources().getString(R.string.non_empty_gst));
            return false;
        }

        else
        {
            gst.setError(null);
            return true;
        }
    }

    private Boolean validateTotal()
    {
        if(String.valueOf(total.getText()).isEmpty())
        {
            total.setError(getResources().getString(R.string.non_empty_total_price));
            return false;
        }

        if(total.getText().toString().trim().length() == 0)
        {
            total.setError(getResources().getString(R.string.non_empty_total_price));
            return false;
        }

        else
        {
            total.setError(null);
            return true;
        }
    }

    private Boolean validateSalePrice()
    {
        if(String.valueOf(salePrice.getText()).isEmpty())
        {
            salePrice.setError(getResources().getString(R.string.non_empty_sale_price));
            return false;
        }

        if(salePrice.getText().toString().trim().length() == 0)
        {
            salePrice.setError(getResources().getString(R.string.non_empty_sale_price));
            return false;
        }

        else
        {
            salePrice.setError(null);
            return true;
        }
    }

    //QR Code Scan Scanner
    private void scanQRCode()
    {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
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
                        DefaultColor = ContextCompat.getColor(skipPurchase.this, R.color.colorPrimary);
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
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
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

        if(barCodeScanFlag == 1)
        {
            if(result != null)
            {
                if(result.getContents()==null)
                {
                    Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
                }
                else
                {
                    HSN.setText(result.getContents().toString());
                }
            }
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

    //HTTP Request
    private void httpRequest()
    {

    }
}
