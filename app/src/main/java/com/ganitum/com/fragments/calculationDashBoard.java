package com.ganitum.com.fragments;

import android.graphics.Canvas;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ganitum.com.R;
import com.ganitum.com.recyclerViews.calculationDashBoard.itemAdapter;
import com.ganitum.com.recyclerViews.calculationDashBoard.itemAdapterListView;
import com.ganitum.com.recyclerViews.calculationDashBoard.itemDataBinder;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class calculationDashBoard extends Fragment
{
    //Recycler View Elements
    private List<itemDataBinder> itemList;
    RecyclerView recyclerView;
    private int viewSwitchFlag = 0;
    private  ArrayList<itemDataBinder> filteredList = new ArrayList<>();
    private static double Price = 0;

    //UI Buttons and Elements
    private ImageView gridView;
    private EditText searchBar;
    public static TextView priceDisplay;

    public calculationDashBoard()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculaion_dash_board, container, false);

        //Search Initializer
        searchBar = (EditText) view.findViewById(R.id.searchBar);

        //UI Elements
        gridView = (ImageView) view.findViewById(R.id.gridView);
        priceDisplay = (TextView) view.findViewById(R.id.priceDisplay);
        recyclerView = (RecyclerView) view.findViewById(R.id.searchHolder);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        priceDisplay.setText(String.valueOf(Price));

        gridView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (viewSwitchFlag == 0)
                {
                    viewSwitchFlag = 1;
                    inflateRecyclerView(viewSwitchFlag);
                    viewModelChanger(viewSwitchFlag, filteredList);
                    gridView.setBackgroundResource(R.drawable.grid_view);

                }
                else
                {
                    viewSwitchFlag = 0;
                    inflateRecyclerView(viewSwitchFlag);
                    viewModelChanger(viewSwitchFlag, filteredList);
                    gridView.setBackgroundResource(R.drawable.menu_icon);
                }
            }
        });

        searchBar.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                    filter(s.toString());
            }
        });

        inflateRecyclerView(viewSwitchFlag);

        return view;
    }

    //Filtering Our Recycler View Items
    private void filter(String text)
    {
        filteredList.clear();
        for(itemDataBinder item: itemList)
        {
            if(item.getProductName().trim().toLowerCase().contains(text.trim().toLowerCase()))
            {
                filteredList.add(item);
            }
        }

        viewModelChanger(viewSwitchFlag, filteredList);
    }

    //Inflating Recycler View
    private void inflateRecyclerView(int viewSwitchFlag)
    {
        itemList = new ArrayList<>();

        itemList.add(new itemDataBinder("Samsung","130000","https://secure.gravatar.com/avatar/73da4e136f3c8e67eb9b849dbedbef38?s=60&d=wavatar&r=g"));

        itemList.add(new itemDataBinder("Apple","140000","https://secure.gravatar.com/avatar/73da4e136f3c8e67eb9b849dbedbef38?s=60&d=wavatar&r=g"));

        itemList.add(new itemDataBinder("MI","150000","https://secure.gravatar.com/avatar/73da4e136f3c8e67eb9b849dbedbef38?s=60&d=wavatar&r=g"));

        itemList.add(new itemDataBinder("Oppo","160000","https://secure.gravatar.com/avatar/73da4e136f3c8e67eb9b849dbedbef38?s=60&d=wavatar&r=g"));

        itemList.add(new itemDataBinder("Vivo","170000","https://secure.gravatar.com/avatar/73da4e136f3c8e67eb9b849dbedbef38?s=60&d=wavatar&r=g"));

        itemList.add(new itemDataBinder("Nokia","180000","https://secure.gravatar.com/avatar/73da4e136f3c8e67eb9b849dbedbef38?s=60&d=wavatar&r=g"));

        itemList.add(new itemDataBinder("Motorola","190000","https://secure.gravatar.com/avatar/73da4e136f3c8e67eb9b849dbedbef38?s=60&d=wavatar&r=g"));

        viewModelChanger(0, itemList);
        viewModelChanger(1, itemList);
        viewModelChanger(viewSwitchFlag, itemList);

    }

    //This Function Change The View Model and Also Serve As Search Filter
    private void viewModelChanger(int viewSwitchFlag, List<itemDataBinder> data)
    {
        switch (viewSwitchFlag)
        {
            //View Switcher
            case 0:
                GridLayoutManager layoutManagerList = new GridLayoutManager(getContext(), 1);
                recyclerView.setLayoutManager(layoutManagerList);
                itemAdapterListView adapterList =   new itemAdapterListView(getContext(),data);
                recyclerView.setAdapter(adapterList);
                break;

            case 1:
                GridLayoutManager layoutManagerGrid = new GridLayoutManager(getContext(), 2);
                recyclerView.setLayoutManager(layoutManagerGrid);
                itemAdapter adapterGrid =   new itemAdapter(getContext(),data);
                recyclerView.setAdapter(adapterGrid);
                break;
        }
    }

    //Adding Price To Text View
    public static void addPrice(String newText)
    {
        double p = Double.parseDouble(newText);
        Price = p + Price;
        final String price = String.valueOf(Price);
        priceDisplay.setText(price);
    }

    //Removing Price From Text View
    public static void removePrice(String newText)
    {
        double currentPrice = Double.parseDouble(newText);
        double previousPrice = Double.parseDouble(priceDisplay.getText().toString());

        if (previousPrice >= currentPrice)
        {
            Price = previousPrice - currentPrice;
        }

        else if(currentPrice > previousPrice)
        {
            Price = 0.0;
        }

        final String price = String.valueOf(Price);
        priceDisplay.setText(price);
    }
}
