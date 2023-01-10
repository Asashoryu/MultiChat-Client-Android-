package com.example.multichatclient;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListaGruppiActivity extends AppCompatActivity {

    final ArrayList<String> aListp = new ArrayList<>();
    ListView vlist;
    ArrayAdapter<String> adapter;
    //Toolbar optionToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_gruppi);
        for (int i = 1; i<=20; i++) {
            aListp.add("Product" + i);
        }
        vlist = (ListView) findViewById(R.id.listView1);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, aListp);
        vlist.setAdapter(adapter);
        vlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adattatore, View componente, int pos, long index) {
                String elem = (String)adattatore.getItemAtPosition(pos);
                Log.d ("List","Elemento clickato = " + elem);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}