package com.ganitum.com.activities;

import android.content.Intent;
import android.os.Bundle;

import com.ganitum.com.R;
import com.ganitum.com.activities.userManagementActivity.loginMain;
import com.ganitum.com.fragments.chatLogin;
import com.ganitum.com.fragments.dashboard;
import com.ganitum.com.fragments.calculationDashBoard;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

public class homePage extends AppCompatActivity
{
    private dashboard Dashboard;
    private chatLogin ChatLogin;
    private calculationDashBoard calcDash;
    private FrameLayout main_frame;

    private int chatFlag = 0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.navigation_home:
                    setFragments(Dashboard);
                    return true;

                case R.id.calculate:
                    setFragments(calcDash);
                    return true;

                case R.id.chat:
                    setFragments(ChatLogin);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Dashboard = new dashboard();
        ChatLogin = new chatLogin();
        calcDash = new calculationDashBoard();
        main_frame = (FrameLayout) findViewById(R.id.main_frame);

        //Hiding Action Bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        setFragments(Dashboard);
    }

    private void setFragments(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent loginMain = new Intent(homePage.this, com.ganitum.com.activities.userManagementActivity.loginMain.class);
        startActivity(loginMain);
        finish();
    }
}
