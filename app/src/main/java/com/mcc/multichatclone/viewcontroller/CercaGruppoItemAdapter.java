package com.mcc.multichatclone.viewcontroller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcc.multichatclone.controller.Controller;
import com.mcc.multichatclone.databinding.GruppoItemV2Binding;
import com.mcc.multichatclone.databinding.NotificaItemBinding;
import com.mcc.multichatclone.model.Gruppo;
import com.mcc.multichatclone.model.Notifica;

import java.util.ArrayList;

public class CercaGruppoItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Gruppo> data = new ArrayList<>();

    public void setData(ArrayList<Gruppo> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        GruppoItemV2Binding binding;

        Controller controller;

        public ViewHolder(@NonNull GruppoItemV2Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public static CercaGruppoItemAdapter.ViewHolder inflateFrom(ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            @NonNull GruppoItemV2Binding binding = GruppoItemV2Binding.inflate(layoutInflater, parent, false);
            return new CercaGruppoItemAdapter.ViewHolder(binding);
        }

        public void bind(Gruppo gruppo) {
            binding.setGruppo(gruppo);

            controller = Controller.getInstance();
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        System.err.println("il valore di gruppo è " + gruppo);
                        if (gruppo != null) System.out.println(gruppo.getNome());
                        controller.mandaNotifica(gruppo.getNome());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public CercaGruppoItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        return CercaGruppoItemAdapter.ViewHolder.inflateFrom(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Gruppo item = data.get(position);
        CercaGruppoItemAdapter.ViewHolder binding = (CercaGruppoItemAdapter.ViewHolder) holder;
        binding.bind(item);
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
}
