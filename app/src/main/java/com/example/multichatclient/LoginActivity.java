package com.example.multichatclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.multichatclient.controller.Controller;
import com.example.multichatclient.controller.LogIn;

public class LoginActivity extends AppCompatActivity {

    Controller c;
    EditText name_et;
    EditText password_et;
    AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name_et = findViewById(R.id.et_email);
        password_et = findViewById(R.id.et_password);
        c = (Controller)getIntent().getSerializableExtra("controller");
        alert = new AlertDialog.Builder(this);
        alert.setTitle("Error");
        alert.setPositiveButton("Ok",null);
        alert.setCancelable(true);
    }

    public void goBack(View view) {
        setContentView(R.layout.activity_main);
    }

    public void sigIn(View view) {
        Intent sigIn = new Intent(this, RegisterActivity.class);
        startActivity(sigIn);
    }

    public void log_in(View view) {
        String name;
        String password;
        name = String.valueOf(name_et.getText());
        password = String.valueOf(password_et.getText());
        LogIn logIn = new LogIn();
        logIn.execute(name,password);
    }
}