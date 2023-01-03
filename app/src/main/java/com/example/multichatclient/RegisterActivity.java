package com.example.multichatclient;

import static com.example.multichatclient.R.layout.activity_login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.multichatclient.controller.Controller;

public class RegisterActivity extends AppCompatActivity {

    Controller c;
    EditText name_et;
    EditText password_et;
    AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name_et = findViewById(R.id.et_email);
        password_et = findViewById(R.id.et_password);
        c = (Controller)getApplication();
    }

    public void goBack(View view) {
        setContentView(activity_login);
    }

    public void sig_up(View view) {
        String nome = String.valueOf(name_et.getText());
        String password = String.valueOf(password_et.getText());

        try {
            c.register(nome,password);
        } catch (InterruptedException e) {
            e.printStackTrace();
            alert = new AlertDialog.Builder(this);
            alert.setTitle("Error");
            alert.setPositiveButton("Ok",null);
            alert.setCancelable(true);
            alert.setMessage (e.getMessage());
            alert.show();
        }
    }
}