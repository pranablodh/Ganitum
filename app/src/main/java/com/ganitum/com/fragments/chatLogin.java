package com.ganitum.com.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ganitum.com.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class chatLogin extends Fragment
{

    private EditText userId;
    private EditText password;
    private Button login;

    public chatLogin()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_login, container, false);

        //UI Elements
        userId = (EditText) view.findViewById(R.id.userId);
        password = (EditText) view.findViewById(R.id.password);
        login = (Button) view.findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                logInToChat();
            }
        });
        return view;
    }

    //Validating Our Text Input
    private Boolean validateUserId()
    {
        if(String.valueOf(userId.getText()).isEmpty())
        {
            userId.setError(getResources().getString(R.string.valid_user_id));
            return false;
        }

        if(userId.getText().toString().trim().length() == 0)
        {
            userId.setError(getResources().getString(R.string.valid_user_id));
            return false;
        }

        else
        {
            userId.setError(null);
            return true;
        }
    }

    private Boolean validatePassword()
    {
        if(String.valueOf(password.getText()).isEmpty())
        {
            password.setError(getResources().getString(R.string.valid_password));
            return false;
        }

        if(password.getText().toString().trim().length() == 0)
        {
            password.setError(getResources().getString(R.string.valid_password));
            return false;
        }

        else
        {
            password.setError(null);
            return true;
        }
    }

    //Chat Login Function
    private void logInToChat()
    {
        if(!validateUserId() | !validatePassword())
        {
            return;
        }

        goToChatUserList();
    }

    //Go To Chat User List
    private void goToChatUserList()
    {
        Intent goTOChatUserList = new Intent(getActivity(), com.ganitum.com.activities.chatActivity.userList.class);
        startActivity(goTOChatUserList);
        Objects.requireNonNull(getActivity()).finish();
    }
}
