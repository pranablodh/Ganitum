package com.ganitum.com.recyclerViews.sale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ganitum.com.R;

import java.util.List;

public class directSaleAdapter extends RecyclerView.Adapter<directSaleAdapter.directSaleViewHolder>
{
    private Context mCtx;
    private List<directSaleDataBinder> directSaleList;

    public directSaleAdapter(Context mCtx, List<directSaleDataBinder> directSaleList)
    {
        this.mCtx = mCtx;
        this.directSaleList = directSaleList;
    }

    @NonNull
    @Override
    public directSaleAdapter.directSaleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_sale, null);
        return new directSaleAdapter.directSaleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final directSaleAdapter.directSaleViewHolder directSaleViewHolder, int i)
    {
        directSaleDataBinder dsDataBind = directSaleList.get(i);
        directSaleViewHolder.date.setText(dsDataBind.getDate());
        directSaleViewHolder.customer.setText(dsDataBind.getCustomer());
        directSaleViewHolder.amount.setText(dsDataBind.getAmount());
    }

    @Override
    public int getItemCount()
    {
        return directSaleList.size();
    }

    class directSaleViewHolder extends RecyclerView.ViewHolder
    {
        TextView date;
        TextView customer;
        TextView amount;

        directSaleViewHolder(@NonNull View itemView)
        {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            customer = itemView.findViewById(R.id.customer);
            amount = itemView.findViewById(R.id.amount);
        }
    }
}
