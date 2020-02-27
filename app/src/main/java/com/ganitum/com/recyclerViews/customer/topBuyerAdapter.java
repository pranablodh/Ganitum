package com.ganitum.com.recyclerViews.customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ganitum.com.R;

import java.util.List;

public class topBuyerAdapter extends RecyclerView.Adapter<topBuyerAdapter.topBuyerViewHolder>
{
    private Context mCtx;
    private List<topBuyerDataBinder> topBuyerList;

    public topBuyerAdapter(Context mCtx, List<topBuyerDataBinder> topBuyerList)
    {
        this.mCtx = mCtx;
        this.topBuyerList = topBuyerList;
    }

    @NonNull
    @Override
    public topBuyerAdapter.topBuyerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_top_buyer, null);
        return new topBuyerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final topBuyerAdapter.topBuyerViewHolder topBuyerViewHolder, int i)
    {
        topBuyerDataBinder tBuyerDataBind = topBuyerList.get(i);
        topBuyerViewHolder.topCustomerName.setText(tBuyerDataBind.getTopCustomerName());
        topBuyerViewHolder.topCustomerAmount.setText(tBuyerDataBind.getTopCustomerAmount());
    }

    @Override
    public int getItemCount()
    {
        return topBuyerList.size();
    }

    class topBuyerViewHolder extends RecyclerView.ViewHolder
    {
        TextView topCustomerName;
        TextView topCustomerAmount;

        topBuyerViewHolder(@NonNull View itemView)
        {
            super(itemView);
            topCustomerName = itemView.findViewById(R.id.topCustomerName);
            topCustomerAmount = itemView.findViewById(R.id.topCustomerAmount);
        }
    }
}