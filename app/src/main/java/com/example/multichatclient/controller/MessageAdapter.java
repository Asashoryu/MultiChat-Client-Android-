package com.example.multichatclient.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.multichatclient.R;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter  extends BaseAdapter {

    List<Message> message_list;
    Context context;

    public MessageAdapter(Context context, ArrayList<Message> message_list) {
        this.context = context;
        this.message_list = message_list;
    }

    public void add(Message message) {
        message_list.add(message);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return message_list.size();
    }

    @Override
    public Object getItem(int i) {
        return message_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        MessageViewHolder holder = new MessageViewHolder();
        LayoutInflater messageInflater = LayoutInflater.from(context);
        Message message = message_list.get(i);

        if (message.isBelongsToCurrentUser()) { // this message was sent by us so let's create a basic chat bubble on the right
            convertView = messageInflater.inflate(R.layout.message, viewGroup,false);
            holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);
        }
        else {
            convertView = messageInflater.inflate(R.layout.other_message, viewGroup,false);
            holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
            holder.name.setText(message.getName());
        }
        holder.messageBody.setText(message.getText());
        return convertView;
    }

    public static class MessageViewHolder {
        public TextView name;
        public TextView messageBody;
    }
}
