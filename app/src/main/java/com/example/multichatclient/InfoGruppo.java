package com.example.multichatclient;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class InfoGruppo extends AppCompatActivity {

    final ArrayList<String> aListp = new ArrayList<>();
    ListView vlist;
    ArrayAdapter<String> adapter;
    TextView nomeGruppo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_gruppo);
        nomeGruppo = findViewById(R.id.nome_gruppo);
        vlist = findViewById(R.id.listaGruppo);
        nomeGruppo.setText(getIntent().getExtras().getString("Nome"));
        for (int i=0;i<20;i++) {
            String contatto = "Contatto " + i;
            aListp.add(contatto);
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, aListp);
        vlist.setAdapter(adapter);
    }
}