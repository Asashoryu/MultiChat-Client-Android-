package com.example.multichatclient;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.multichatclient.controller.Controller;

import java.net.SocketTimeoutException;

public class MainActivity extends AppCompatActivity {

    Controller c;
    AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = (Controller) getApplication();
    }

    public void onClick(View view) {
        try {
            c.connetti();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            alert = new AlertDialog.Builder(this);
            alert.setTitle("Errore");
            alert.setPositiveButton("Ok",null);
            alert.setCancelable(true);
            alert.setMessage ("Errore di Connessione");
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
            alert = new AlertDialog.Builder(this);
            alert.setTitle("Errore");
            alert.setPositiveButton("Ok",null);
            alert.setCancelable(true);
            alert.setMessage ("Altro tipo di errore");
            alert.show();
        }
    }
}