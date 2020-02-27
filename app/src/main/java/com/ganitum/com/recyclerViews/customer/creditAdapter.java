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

public class creditAdapter extends RecyclerView.Adapter<creditAdapter.creditViewHolder>
{
    private Context mCtx;
    private List<creditDataBinder> creditList;

    public creditAdapter(Context mCtx, List<creditDataBinder> creditList)
    {
        this.mCtx = mCtx;
        this.creditList = creditList;
    }

    @NonNull
    @Override
    public creditAdapter.creditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_credit, null);
        return new creditViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final creditAdapter.creditViewHolder creditViewHolder, int i)
    {
        creditDataBinder cDataBind = creditList.get(i);
        creditViewHolder.creditCustomerName.setText(cDataBind.getCreditCustomerName());
        creditViewHolder.creditCustomerAmount.setText(cDataBind.getCreditCustomerAmount());
    }

    @Override
    public int getItemCount()
    {
        return creditList.size();
    }

    class creditViewHolder extends RecyclerView.ViewHolder
    {
        TextView creditCustomerName;
        TextView creditCustomerAmount;

        creditViewHolder(@NonNull View itemView)
        {
            super(itemView);
            creditCustomerName = itemView.findViewById(R.id.creditCustomerName);
            creditCustomerAmount = itemView.findViewById(R.id.creditCustomerAmount);
        }
    }
}
