package com.example.multichatclient;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.multichatclient.controller.Message;
import com.example.multichatclient.controller.MessageAdapter;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    EditText message_et;
    MessageAdapter adapter;
    ListView lView;
    ArrayList<Message> message_list = new ArrayList<>();
    TextView nomeGruppo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        lView = findViewById(R.id.message_view);
        adapter = new MessageAdapter(this, message_list);
        lView.setAdapter(adapter);
        message_et = findViewById(R.id.message_text);
        nomeGruppo = findViewById(R.id.nome_gruppo_chat);
        nomeGruppo.setText(getIntent().getExtras().getString("NomeGruppo"));
    }

    public void sendMessage(View view) {
        String messageT = message_et.getText().toString();
        Message message1 = new Message("cur",messageT,true);
        Message message2 = new Message ("user",messageT,false);
        adapter.add(message1);
        adapter.add(message2);
        lView.setSelection(adapter.getCount());
        message_et.getText().clear();
    }
}