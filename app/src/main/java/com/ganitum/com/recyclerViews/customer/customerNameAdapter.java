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

public class customerNameAdapter extends RecyclerView.Adapter<customerNameAdapter.customerNameViewHolder>
{
    private Context mCtx;
    private List<customerNameDataBinder> customerList;

    public customerNameAdapter(Context mCtx, List<customerNameDataBinder> customerList)
    {
        this.mCtx = mCtx;
        this.customerList = customerList;
    }

    @NonNull
    @Override
    public customerNameAdapter.customerNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_customer_name, null);
        return new customerNameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final customerNameAdapter.customerNameViewHolder customerNameViewHolder, int i)
    {
        customerNameDataBinder cNameDataBind = customerList.get(i);
        customerNameViewHolder.customerName.setText(cNameDataBind.getCustomerName());
    }

    @Override
    public int getItemCount()
    {
        return customerList.size();
    }

    class customerNameViewHolder extends RecyclerView.ViewHolder
    {
        TextView customerName;

        customerNameViewHolder(@NonNull View itemView)
        {
            super(itemView);
            customerName = itemView.findViewById(R.id.customerName);
        }
    }
}