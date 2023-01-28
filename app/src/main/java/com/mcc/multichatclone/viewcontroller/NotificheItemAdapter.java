package com.mcc.multichatclone.viewcontroller;

import androidx.recyclerview.widget.RecyclerView;

import com.mcc.multichatclone.model.Notifica;

import java.util.ArrayList;

public class NotificheItemAdapter extends RecyclerView<NotificheItemAdapter>.ViewHolder {

    private ArrayList<Notifica> data = new ArrayList<>();

    public void setData(ArrayList<Notifica> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
