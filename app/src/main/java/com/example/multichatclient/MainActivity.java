package com.example.multichatclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.multichatclient.controller.Controller;

public class MainActivity extends AppCompatActivity {

    Controller c;
    //hello

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = new Controller();
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("controller",c);
        startActivity(intent);
    }
}