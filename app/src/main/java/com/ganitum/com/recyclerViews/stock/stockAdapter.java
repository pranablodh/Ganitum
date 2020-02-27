package com.ganitum.com.recyclerViews.stock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ganitum.com.R;
import com.ganitum.com.recyclerViews.purchase.purchaseAdapter;
import com.ganitum.com.recyclerViews.purchase.purchaseDataBinder;

import java.util.List;

public class stockAdapter extends RecyclerView.Adapter<stockAdapter.stockViewHolder>
{
    private Context mCtx;
    private List<stockDataBinder> stockList;

    public stockAdapter(Context mCtx, List<stockDataBinder> stockList)
    {
        this.mCtx = mCtx;
        this.stockList = stockList;
    }

    @NonNull
    @Override
    public stockAdapter.stockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_stock, null);
        return new stockAdapter.stockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final stockAdapter.stockViewHolder stockViewHolder, int i)
    {
        stockDataBinder sDataBind = stockList.get(i);
        stockViewHolder.productName.setText(sDataBind.getProductName());
        stockViewHolder.amount.setText(sDataBind.getQuantity());
    }

    @Override
    public int getItemCount()
    {
        return stockList.size();
    }

    class stockViewHolder extends RecyclerView.ViewHolder
    {
        TextView productName;
        TextView amount;

        stockViewHolder(@NonNull View itemView)
        {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            amount = itemView.findViewById(R.id.amount);
        }
    }
}
