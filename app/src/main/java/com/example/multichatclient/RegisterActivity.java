package com.example.multichatclient;

import static com.example.multichatclient.R.layout.activity_login;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void goBack(View view) {
        setContentView(activity_login);
    }
}