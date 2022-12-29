package com.example.multichatclient;

import static com.example.multichatclient.R.layout.activity_login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.multichatclient.controller.SigUp;

public class RegisterActivity extends AppCompatActivity {

    EditText nome_et;
    EditText password_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nome_et = findViewById(R.id.et_email);
        password_et = findViewById(R.id.et_password);
    }



    public void goBack(View view) {
        setContentView(activity_login);
    }

    public void sig_up(View view) {
        String nome;
        String password;
        nome = String.valueOf(nome_et.getText());
        password = String.valueOf(password_et.getText());
        SigUp sigUp = new SigUp();
        sigUp.execute(nome,password);

    }
}