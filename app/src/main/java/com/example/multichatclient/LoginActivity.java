package com.example.multichatclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.multichatclient.controller.Controller;


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
        c = (Controller)getApplication();
    }

    public void goBack(View view) {
        //TODO ciclo di vita delle activity
        //setContentView(R.layout.activity_main);
    }

    public void sigIn(View view) {
        Intent sigIn = new Intent(this, RegisterActivity.class);
        startActivity(sigIn);
    }

    public void log_in(View view) {
        String nome = String.valueOf(name_et.getText());
        String password = String.valueOf(password_et.getText());
        Intent intent = new Intent(this, ListaGruppiActivity.class);
        //Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
        //TODO implementare server
        /*try {
            c.log_in(nome,password);
            Intent intent = new Intent(this, ListaGruppiActivity.class);
            //Intent intent = new Intent(this, ChatActivity.class);
            startActivity(intent);
        } catch (NullPointerException e) {
            e.printStackTrace();
            alert = new AlertDialog.Builder(this);
            alert.setTitle("Account non trovato");
            alert.setPositiveButton("Ok",null);
            alert.setCancelable(true);
            alert.setMessage (e.getMessage());
            alert.show();
        } catch (InterruptedException e)  {
            e.printStackTrace();
            alert = new AlertDialog.Builder(this);
            alert.setTitle("Altro tipo di errore");
            alert.setPositiveButton("Ok",null);
            alert.setCancelable(true);
            alert.setMessage (e.getMessage());
            alert.show();
        }*/
    }
}