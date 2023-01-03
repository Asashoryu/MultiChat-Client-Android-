package com.example.multichatclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class ListaGruppiActivity extends AppCompatActivity {

    String[] prova;
    final ArrayList<String> listp = new ArrayList<String>();
    ListView mylist;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_gruppi);
        prova = new String[] { "Product1", "Product2", "Product3" };
        Collections.addAll(listp, prova);
        mylist = (ListView) findViewById(R.id.listView1);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listp);
        mylist.setAdapter(adapter);
    }
}