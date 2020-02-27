package com.ganitum.com.activities.salesActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ganitum.com.R;
import com.ganitum.com.recyclerViews.sale.directSaleAdapter;
import com.ganitum.com.recyclerViews.sale.directSaleDataBinder;
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

import java.util.ArrayList;
import java.util.List;

public class directSale extends AppCompatActivity
{

    private ImageView back;
    private TextView newSale;
    private ImageView pieChartButton;
    private ImageView barGraphButton;
    private PieChart pieChart;
    private BarChart barGraph;
    private List<directSaleDataBinder> directSaleList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_sale);

        //Hiding Action Bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        //UI Elements
        back = (ImageView) findViewById(R.id.back);
        newSale = (TextView) findViewById(R.id.newSale);
        pieChartButton = (ImageView) findViewById(R.id.pieChartButton);
        barGraphButton = (ImageView) findViewById(R.id.barGraphButton);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerDirectSale);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(directSale.this));

        //Pie Chart & Bar Graph Element
        pieChart = (PieChart) findViewById(R.id.pieChart);
        barGraph = (BarChart) findViewById(R.id.barGraph);

        //On Click Listener
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToSale();
            }
        });

        newSale.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToNewSale();
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
        populatePieChart();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        goToSale();
    }

    private void goToSale()
    {
        Intent goTOSALE = new Intent(directSale.this, com.ganitum.com.activities.salesActivity.sale.class);
        startActivity(goTOSALE);
        finish();
    }

    private void goToNewSale()
    {
        Intent goTONewSale = new Intent(directSale.this, com.ganitum.com.activities.salesActivity.newSale.class);
        startActivity(goTONewSale);
        finish();
    }

    //Inflating Recycler View
    private void inflateRecyclerView()
    {
        directSaleList = new ArrayList<>();

        directSaleList.add(new directSaleDataBinder("Aniruddha","130000","30/07/2019"));

        directSaleList.add(new directSaleDataBinder("Pranab","130000","30/07/2019"));

        directSaleList.add(new directSaleDataBinder("Arpan","130000","30/07/2019"));

        directSaleList.add(new directSaleDataBinder("Tanmay","130000","30/07/2019"));

        directSaleList.add(new directSaleDataBinder("Gurucharan","130000","30/07/2019"));

        directSaleList.add(new directSaleDataBinder("Suman","130000","30/07/2019"));

        directSaleList.add(new directSaleDataBinder("Sovan","130000","30/07/2019"));

        directSaleList.add(new directSaleDataBinder("Sivprakash","130000","30/07/2019"));

        directSaleList.add(new directSaleDataBinder("Ajoy","130000","30/07/2019"));

        directSaleAdapter adapter =   new directSaleAdapter(directSale.this, directSaleList);
        recyclerView.setAdapter(adapter);
    }

    //Populate Pie Chart
    private void populatePieChart()
    {
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setTransparentCircleRadius(65);
        pieChart.setDragDecelerationFrictionCoef(0.99f);
        pieChart.animateY(1000);


        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(10,"India"));
        yValues.add(new PieEntry(30,"England"));
        yValues.add(new PieEntry(60,"Germany"));
        yValues.add(new PieEntry(5,"Spain"));
        yValues.add(new PieEntry(15,"France"));
        yValues.add(new PieEntry(70,"Italy"));
        yValues.add(new PieEntry(40,"Latvia"));

        PieDataSet dataSet = new PieDataSet(yValues,"Countries");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setSliceSpace(1f);
        //dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);
    }

    //Populate Bar Graph
    private void populateBarGraph()
    {
        ArrayList<BarEntry> yValues = new ArrayList<>();
        yValues.add(new BarEntry(1, 40));
        yValues.add(new BarEntry(2, 30));
        yValues.add(new BarEntry(3, 25));
        yValues.add(new BarEntry(4, 60));
        yValues.add(new BarEntry(5, 70));
        yValues.add(new BarEntry(6, 10));
        yValues.add(new BarEntry(7, 5));

        final ArrayList<String> xVals = new ArrayList<>();
        xVals.add("Present");
        xVals.add("Pres. Continuous");
        xVals.add("Simple Past");
        xVals.add("Past Perfect");
        xVals.add("Conditional");
        xVals.add("Cond. Perfect");
        xVals.add("Future");

        BarDataSet set = new BarDataSet(yValues, "Data Sets");
        set.setColors(ColorTemplate.JOYFUL_COLORS);
        set.setDrawValues(true);

        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("Mon");
        xAxisLabel.add("Tue");
        xAxisLabel.add("Wed");
        xAxisLabel.add("Thu");
        xAxisLabel.add("Fri");
        xAxisLabel.add("Sat");
        xAxisLabel.add("Sun");

        for(String x: xAxisLabel)
        {
            final String xLabel = x;
            XAxis xAxis = barGraph.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setValueFormatter(new ValueFormatter()
            {
                @Override
                public String getFormattedValue(float value)
                {
                    return  xLabel;
                }
            });
        }

        BarData data = new BarData(set);
        barGraph.setData(data);
        barGraph.invalidate();
        barGraph.animateY(1000);
    }
}
