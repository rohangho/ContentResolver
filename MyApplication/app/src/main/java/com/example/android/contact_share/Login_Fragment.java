package com.example.android.contact_share;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by ROHAN on 01-11-2017.
 */

public class Login_Fragment extends Fragment {

    public Login_Fragment()
    {

    }

    EditText name;
    EditText phone_number;
    Button login;
    TextView CreateAccount;
    private FirebaseAuth fbAuth;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        phone_number =(EditText)  rootView.findViewById(R.id.number);
        login=(Button)rootView.findViewById(R.id.login);
        CreateAccount=(TextView)rootView.findViewById(R.id.create_account);

        fbAuth=FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





            }
        });

        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent=new Intent(getActivity(),CreateAccount.class);
                startActivity(myintent);
            }
        });


        return rootView;
    }








}
