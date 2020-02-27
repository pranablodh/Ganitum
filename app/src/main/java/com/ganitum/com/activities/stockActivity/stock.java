package com.ganitum.com.activities.stockActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
import com.ganitum.com.recyclerViews.stock.stockAdapter;
import com.ganitum.com.recyclerViews.stock.stockDataBinder;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class stock extends AppCompatActivity
{

    private ImageView back;
    private ImageView pieChartButton;
    private ImageView barGraphButton;
    private TextView newPurchase;
    private PieChart pieChart;
    private BarChart barGraph;
    private List<stockDataBinder> stockList;
    private String barGraphApi = api.baseUrl + api.stockBarChart;
    private String pieChartApi = api.baseUrl + api.stockPieChart;
    private String dataApi = api.baseUrl + api.getStock;
    RecyclerView recyclerView;
    private Dialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        //Hiding Action Bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        //UI Elements
        back = (ImageView) findViewById(R.id.back);
        pieChartButton = (ImageView) findViewById(R.id.pieChartButton);
        barGraphButton = (ImageView) findViewById(R.id.barGraphButton);
        recyclerView = (RecyclerView) findViewById(R.id.stockRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(stock.this));
        progressDialog = new Dialog(stock.this);
        progressDialog.setCancelable(false);
        progressDialog.setContentView(R.layout.dialog_progress);
        Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Pie Chart & Bar Graph Element
        pieChart = (PieChart) findViewById(R.id.pieChart);
        barGraph = (BarChart) findViewById(R.id.barGraph);

        //On Click Listener
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToHomePage();
            }
        });

        pieChartButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                barGraph.setVisibility(View.INVISIBLE);
                pieChart.setVisibility(View.VISIBLE);
                populatePieChart();
            }
        });

        barGraphButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                pieChart.setVisibility(View.INVISIBLE);
                barGraph.setVisibility(View.VISIBLE);
                populateBarGraph();
            }
        });

        inflateRecyclerView();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        goToHomePage();
    }

    private void goToHomePage()
    {
        Intent homePage = new Intent(stock.this, com.ganitum.com.activities.homePage.class);
        startActivity(homePage);
        finish();
    }

    //Inflating Recycler View
    private void inflateRecyclerView()
    {
        progressDialog.show();
        stockList = new ArrayList<>();
        StringRequest recyclerViewRequest = new StringRequest(Request.Method.POST, dataApi,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            JSONObject stockData = new JSONObject(response);
                            String status = stockData.getString("status");

                            if(status.trim().toLowerCase().equals("success"))
                            {
                                JSONArray dataArray = stockData.getJSONArray("data");

                                for(int i=0; i < dataArray.length(); i++)
                                {
                                    JSONObject obj = dataArray.getJSONObject(i);
                                    String productName = obj.getString("product_name");
                                    String quantity = obj.getString("quantity");
                                    stockList.add(new stockDataBinder(productName, quantity));
                                }

                                stockAdapter adapter =   new stockAdapter(stock.this,stockList);
                                recyclerView.setAdapter(adapter);
                                progressDialog.dismiss();
                                populatePieChart();
                            }

                            else
                            {
                                stockList.add(new stockDataBinder("Not Found", "Not Found"));
                                stockAdapter adapter =   new stockAdapter(stock.this,stockList);
                                recyclerView.setAdapter(adapter);
                                progressDialog.dismiss();
                            }
                        }

                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(stock.this, String.valueOf(error),Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                })
        {
            protected Map<String, String> getParams()
            {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("user_id", "2");
                MyData.put("type", "2");
                return MyData;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(recyclerViewRequest);
    }

    //Populate Pie Chart
    private void populatePieChart()
    {
        progressDialog.show();

        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setTransparentCircleRadius(65);
        pieChart.setDragDecelerationFrictionCoef(0.99f);
        pieChart.animateY(1000);

        StringRequest pieChartRequest = new StringRequest(Request.Method.POST, pieChartApi,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            JSONObject pieChartData = new JSONObject(response);
                            String status = pieChartData.getString("status");
                            JSONArray jsonArray = pieChartData.getJSONArray("data");
                            float[] yVal = new float[jsonArray.length()];
                            if(status.trim().toLowerCase().equals("success"))
                            {
                                ArrayList<PieEntry> yValues = new ArrayList<>();
                                yVal = JSONArrayToFloatArray(jsonArray);

                                for (float v : yVal)
                                {
                                    yValues.add((new PieEntry(v)));
                                }

                                PieDataSet dataSet = new PieDataSet(yValues,"Data");
                                dataSet.setSliceSpace(3f);
                                dataSet.setSelectionShift(5f);
                                dataSet.setSliceSpace(1f);
                                //dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                                dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                                PieData data = new PieData(dataSet);
                                data.setValueTextSize(10f);
                                data.setValueTextColor(Color.YELLOW);

                                pieChart.setData(data);
                                progressDialog.dismiss();
                            }

                           else
                            {
                                progressDialog.dismiss();
                            }
                        }

                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(stock.this, String.valueOf(error),Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                })
        {
            protected Map<String, String> getParams()
            {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("user_id", "2");
                MyData.put("type", "1");
                MyData.put("data_type", "1");
                return MyData;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(pieChartRequest);
    }

    //Populate Bar Graph
    private void populateBarGraph()
    {
        progressDialog.show();

        StringRequest barGraphRequest = new StringRequest(Request.Method.POST, barGraphApi,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            JSONObject pieChartData = new JSONObject(response);
                            String status = pieChartData.getString("status");
                            JSONArray jsonArray = pieChartData.getJSONArray("data");
                            float[] yVal = new float[jsonArray.length()];
                            if(status.trim().toLowerCase().equals("success"))
                            {
                                ArrayList<BarEntry> yValues = new ArrayList<>();
                                yVal = JSONArrayToFloatArray(jsonArray);
                                int i = 0;

                                for (float v : yVal)
                                {
                                    yValues.add(new BarEntry(i, v));
                                    i++;
                                }

                                BarDataSet set = new BarDataSet(yValues, "Data Sets");
                                set.setColors(ColorTemplate.JOYFUL_COLORS);
                                set.setDrawValues(true);

                                BarData data = new BarData(set);
                                barGraph.setData(data);
                                barGraph.invalidate();
                                barGraph.animateY(1000);
                                progressDialog.dismiss();
                            }

                            else
                            {
                                progressDialog.dismiss();
                            }
                        }

                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(stock.this, String.valueOf(error),Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                })
        {
            protected Map<String, String> getParams()
            {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("user_id", "2");
                MyData.put("type", "1");
                MyData.put("data_type", "1");
                return MyData;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(barGraphRequest);
    }

    //JSON Array to Float Converter
    private float[] JSONArrayToFloatArray(JSONArray jsonArray)
    {

        float[] fData = new float[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++)
        {
            try
            {
                fData[i] = Float.parseFloat(jsonArray.getString(i));
            }

            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }

        return fData;
    }
}
