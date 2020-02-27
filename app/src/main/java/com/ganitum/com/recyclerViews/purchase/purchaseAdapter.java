package com.ganitum.com.recyclerViews.purchase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ganitum.com.R;

import java.util.List;

public class purchaseAdapter extends RecyclerView.Adapter<purchaseAdapter.purchaseViewHolder>
{
    private Context mCtx;
    private List<purchaseDataBinder> purchaseList;

    public purchaseAdapter(Context mCtx, List<purchaseDataBinder> purchaseList)
    {
        this.mCtx = mCtx;
        this.purchaseList = purchaseList;
    }

    @NonNull
    @Override
    public purchaseAdapter.purchaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_sale, null);
        return new purchaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final purchaseViewHolder purchaseViewHolder, int i)
    {
        purchaseDataBinder pDataBind = purchaseList.get(i);
        purchaseViewHolder.date.setText(pDataBind.getDate());
        purchaseViewHolder.customer.setText(pDataBind.getCustomer());
        purchaseViewHolder.amount.setText(pDataBind.getAmount());
    }

    @Override
    public int getItemCount()
    {
        return purchaseList.size();
    }

    class purchaseViewHolder extends RecyclerView.ViewHolder
    {
        TextView date;
        TextView customer;
        TextView amount;

        purchaseViewHolder(@NonNull View itemView)
        {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            customer = itemView.findViewById(R.id.customer);
            amount = itemView.findViewById(R.id.amount);
        }
    }
}
