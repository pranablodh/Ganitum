package com.ganitum.com.activities.chatActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ganitum.com.R;
import com.ganitum.com.recyclerViews.chat.chatMessageAdapter;
import com.ganitum.com.recyclerViews.chat.chatMessageDataBinder;

import java.util.ArrayList;
import java.util.List;

public class messaging extends AppCompatActivity
{

    private List<chatMessageDataBinder> chatList = new ArrayList<>();
    RecyclerView recyclerView;
    private ImageView sendButton;
    private EditText message;
    private static int chatFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        //Hiding Action Bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        //UI Elements
        sendButton = (ImageView) findViewById(R.id.sendButton);
        message = (EditText) findViewById(R.id.message);
        recyclerView = (RecyclerView) findViewById(R.id.chatView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(messaging.this));

        //On Click Listener
        sendButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (chatFlag == 0)
                {
                    sendMessage(chatFlag);
                    chatFlag = 1;
                }
                else
                {
                    sendMessage(chatFlag);
                    chatFlag = 0;
                }
            }
        });


    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        goToUserList();
    }

    private void goToUserList()
    {
        Intent userList = new Intent(messaging.this, com.ganitum.com.activities.chatActivity.userList.class);
        startActivity(userList);
        finish();
    }

    //Get The Flag
    public static int getChatFlag()
    {
        return chatFlag;
    }

    //Validating Text Fields
    private Boolean validateMessage()
    {
        if(String.valueOf(message.getText()).isEmpty())
        {
            message.setError(getResources().getString(R.string.empty_message));
            return false;
        }

        if(message.getText().toString().trim().length() == 0)
        {
            message.setError(getResources().getString(R.string.empty_message));
            return false;
        }

        else
        {
            message.setError(null);
            return true;
        }
    }

    private void sendMessage(int chatFlag)
    {
        if(!validateMessage())
        {
            return;
        }

        inflateRecyclerView(chatFlag);
    }

    //Inflating Recycler View
    private void inflateRecyclerView(int chatFlag)
    {
        chatList = new ArrayList<>();
        chatList.add(new chatMessageDataBinder(message.getText().toString(),"Pranab","Bumba",
                1234));
        chatMessageAdapter adapter =   new chatMessageAdapter(messaging.this,chatList,chatFlag);
        recyclerView.setAdapter(adapter);
    }
}
