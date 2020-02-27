package com.ganitum.com.recyclerViews.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ganitum.com.R;
import com.ganitum.com.activities.chatActivity.messaging;

import java.util.List;

public class chatMessageAdapter extends RecyclerView.Adapter<chatMessageAdapter.messageViewHolder>
{
    private Context mCtx;
    private List<chatMessageDataBinder> chatList;
    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;
    private int chatFlag = 0;

    public chatMessageAdapter(Context mCtx, List<chatMessageDataBinder> chatList, int chatFlag)
    {
        this.mCtx = mCtx;
        this.chatList = chatList;
        this.chatFlag = chatFlag;
    }

    @NonNull
    @Override
    public chatMessageAdapter.messageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        if(chatFlag == 0)
        {
            View view = inflater.inflate(R.layout.card_chat_left, parent,false);
            return new messageViewHolder(view);
        }
        else
        {
            View view = inflater.inflate(R.layout.card_chat_right, parent,false);
            return new messageViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull final chatMessageAdapter.messageViewHolder messageViewHolder, int i)
    {
        chatMessageDataBinder messageDataBind = chatList.get(i);
        messageViewHolder.message.setText(messageDataBind.getMessage());

    }

    @Override
    public int getItemCount()
    {
        return chatList.size();
    }

    class messageViewHolder extends RecyclerView.ViewHolder
    {
        TextView message;
        messageViewHolder(@NonNull View itemView)
        {
            super(itemView);
            message = itemView.findViewById(R.id.message);
        }
    }

//    @Override
//    public int getItemViewType(int position)
//    {
//        int flag = messaging.getChatFlag();
//        if(flag == MSG_TYPE_RIGHT)
//        {
//            return MSG_TYPE_RIGHT;
//        }
//
//        else
//        {
//            return MSG_TYPE_LEFT;
//        }
//    }
}
