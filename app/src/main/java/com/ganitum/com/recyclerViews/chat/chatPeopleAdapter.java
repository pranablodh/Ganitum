package com.ganitum.com.recyclerViews.chat;

import android.content.Context;
import android.content.Intent;
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
import com.ganitum.com.activities.chatActivity.messaging;
import com.ganitum.com.activities.chatActivity.userList;

import java.util.List;



public class chatPeopleAdapter extends RecyclerView.Adapter<chatPeopleAdapter.chatViewHolder>
{
    private Context mCtx;
    private List<chatPeopleDataBinder> peopleList;

    public chatPeopleAdapter(Context mCtx, List<chatPeopleDataBinder> peopleList)
    {
        this.mCtx = mCtx;
        this.peopleList = peopleList;
    }

    @NonNull
    @Override
    public chatPeopleAdapter.chatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_chat_people, null);
        return new chatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final chatPeopleAdapter.chatViewHolder chatViewHolder, int i)
    {
        chatPeopleDataBinder chatDataBind = peopleList.get(i);
        chatViewHolder.peopleName.setText(chatDataBind.getPeopleName());
        Glide.with(mCtx).load(chatDataBind.getPeopleImageURL()).into(chatViewHolder.peoplePic);

        chatViewHolder.chatPeople.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent= new Intent(mCtx, messaging.class);
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return peopleList.size();
    }

    class chatViewHolder extends RecyclerView.ViewHolder
    {
        TextView peopleName;
        ImageView peoplePic;
        CardView chatPeople;

        chatViewHolder(@NonNull View itemView)
        {
            super(itemView);
            peopleName = itemView.findViewById(R.id.peopleName);
            peoplePic = itemView.findViewById(R.id.peoplePic);
            chatPeople = itemView.findViewById(R.id.chatPeople);
        }
    }
}
