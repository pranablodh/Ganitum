package com.ganitum.com.activities.chatActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ganitum.com.R;
import com.ganitum.com.activities.homePage;
import com.ganitum.com.recyclerViews.chat.chatPeopleAdapter;
import com.ganitum.com.recyclerViews.chat.chatPeopleDataBinder;
import com.ganitum.com.recyclerViews.purchase.purchaseDataBinder;

import java.util.ArrayList;
import java.util.List;

public class userList extends AppCompatActivity
{

    private ImageView companyPhoto;
    private TextView companyName;
    private List<chatPeopleDataBinder> chatPeopleList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        //Hiding Action Bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        //UI Elements
        companyPhoto = (ImageView) findViewById(R.id.companyPhoto);
        companyName = (TextView) findViewById(R.id.companyName);
        recyclerView = (RecyclerView) findViewById(R.id.people);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(userList.this));

        inflateRecyclerView();

    }

    @Override
    public void onBackPressed() 
    {
        super.onBackPressed();
        Intent backToChatLogin = new Intent(userList.this, homePage.class);
        startActivity(backToChatLogin);
        finish();
    }

    //Inflating Recycler View
    private void inflateRecyclerView()
    {
        chatPeopleList = new ArrayList<>();

        chatPeopleList.add(new chatPeopleDataBinder("Aniruddha","https://secure.gravatar.com/avatar/73da4e136f3c8e67eb9b849dbedbef38?s=60&d=wavatar&r=g"));

        chatPeopleList.add(new chatPeopleDataBinder("Pranab","https://secure.gravatar.com/avatar/73da4e136f3c8e67eb9b849dbedbef38?s=60&d=wavatar&r=g"));

        chatPeopleList.add(new chatPeopleDataBinder("Arpan","https://secure.gravatar.com/avatar/73da4e136f3c8e67eb9b849dbedbef38?s=60&d=wavatar&r=g"));

        chatPeopleList.add(new chatPeopleDataBinder("Tanmay","https://secure.gravatar.com/avatar/73da4e136f3c8e67eb9b849dbedbef38?s=60&d=wavatar&r=g"));

        chatPeopleList.add(new chatPeopleDataBinder("Gurucharan","https://secure.gravatar.com/avatar/73da4e136f3c8e67eb9b849dbedbef38?s=60&d=wavatar&r=g"));

        chatPeopleList.add(new chatPeopleDataBinder("Suman","https://secure.gravatar.com/avatar/73da4e136f3c8e67eb9b849dbedbef38?s=60&d=wavatar&r=g"));

        chatPeopleList.add(new chatPeopleDataBinder("Sovan","https://secure.gravatar.com/avatar/73da4e136f3c8e67eb9b849dbedbef38?s=60&d=wavatar&r=g"));

        chatPeopleList.add(new chatPeopleDataBinder("Sivprakash","https://secure.gravatar.com/avatar/73da4e136f3c8e67eb9b849dbedbef38?s=60&d=wavatar&r=g"));

        chatPeopleList.add(new chatPeopleDataBinder("Ajoy","https://secure.gravatar.com/avatar/73da4e136f3c8e67eb9b849dbedbef38?s=60&d=wavatar&r=g"));

        chatPeopleAdapter adapter =   new chatPeopleAdapter(userList.this,chatPeopleList);
        recyclerView.setAdapter(adapter);
    }
}
