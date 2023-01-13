package com.example.multichatclient;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListaGruppiActivity extends AppCompatActivity implements NewGroupDialog.DialogInterface, PopupMenu.OnMenuItemClickListener {

    final ArrayList<String> aListp = new ArrayList<>();
    ListView vlist;
    ArrayAdapter<String> adapter;
    Context context = this;
    String gruppo;
    //Toolbar optionToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_gruppi);
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
        vlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adattatore, View componente, int pos, long index) {
                gruppo = (String)adattatore.getItemAtPosition(pos);
                PopupMenu gestione_gruppo = new PopupMenu(context,componente);
                gestione_gruppo.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) context);
                gestione_gruppo.inflate(R.menu.popup_group);
                gestione_gruppo.show();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.new_group):
                NewGroupDialog newGroup = new NewGroupDialog();
                newGroup.show(getSupportFragmentManager(),"Test");
                InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                return true;
            case (R.id.search_group):
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void addGroup(String name) {
        aListp.add(name);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_group:
                aListp.remove(gruppo);
                adapter.notifyDataSetChanged();
                return true;
            case R.id.info_group:
                Intent intent = new Intent(this,InfoGruppo.class);
                intent.putExtra("Nome",gruppo);
                startActivity(intent);
                return true;
        }
        return false;
    }
}