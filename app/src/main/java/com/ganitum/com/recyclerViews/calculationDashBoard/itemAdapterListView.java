package com.ganitum.com.recyclerViews.calculationDashBoard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ganitum.com.R;
import com.ganitum.com.fragments.calculationDashBoard;

import java.util.List;

public class itemAdapterListView extends RecyclerView.Adapter<itemAdapterListView.itemViewHolder>
{
    private Context mCtx;
    private List<itemDataBinder> itemList;

    public itemAdapterListView(Context mCtx, List<itemDataBinder> itemList)
    {
        this.mCtx = mCtx;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public itemAdapterListView.itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_liear_view_product, null);
        return new itemAdapterListView.itemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final itemAdapterListView.itemViewHolder itemViewHolder, int i)
    {
        itemDataBinder iDataBind = itemList.get(i);
        itemViewHolder.productName.setText(iDataBind.getProductName());
        itemViewHolder.productPrice.setText(iDataBind.getProductPrice());
        Glide.with(mCtx).load(iDataBind.getUrlImage()).into(itemViewHolder.productImage);

        itemViewHolder.product.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                calculationDashBoard.addPrice(itemViewHolder.productPrice.getText().toString());
            }
        });

        itemViewHolder.product.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view)
            {
                calculationDashBoard.removePrice(itemViewHolder.productPrice.getText().toString());
                return true;
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return itemList.size();
    }

    class itemViewHolder extends RecyclerView.ViewHolder
    {
        TextView productName;
        TextView productPrice;
        ImageView productImage;
        CardView product;

        itemViewHolder(@NonNull View itemView)
        {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productImage = itemView.findViewById(R.id.productImage);
            product = itemView.findViewById(R.id.product);
        }
    }
}
