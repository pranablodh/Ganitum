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


public class saleAdapter extends RecyclerView.Adapter<saleAdapter.saleViewHolder>
{
    private Context mCtx;
    private List<saleDataBinder> saleList;

    public saleAdapter(Context mCtx, List<saleDataBinder> saleList)
    {
        this.mCtx = mCtx;
        this.saleList = saleList;
    }

    @NonNull
    @Override
    public saleAdapter.saleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_sale, null);
        return new saleAdapter.saleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final saleAdapter.saleViewHolder saleViewHolder, int i)
    {
        saleDataBinder sDataBind = saleList.get(i);
        saleViewHolder.date.setText(sDataBind.getDate());
        saleViewHolder.customer.setText(sDataBind.getCustomer());
        saleViewHolder.amount.setText(sDataBind.getAmount());
    }

    @Override
    public int getItemCount()
    {
        return saleList.size();
    }

    class saleViewHolder extends RecyclerView.ViewHolder
    {
        TextView date;
        TextView customer;
        TextView amount;

        saleViewHolder(@NonNull View itemView)
        {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            customer = itemView.findViewById(R.id.customer);
            amount = itemView.findViewById(R.id.amount);
        }
    }
}
