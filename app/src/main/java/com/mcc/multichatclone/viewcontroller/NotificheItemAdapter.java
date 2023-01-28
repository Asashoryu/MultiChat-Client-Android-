package com.mcc.multichatclone.viewcontroller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcc.multichatclone.databinding.NotificaItemBinding;
import com.mcc.multichatclone.model.Notifica;

import java.util.ArrayList;

public class NotificheItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Notifica> data = new ArrayList<>();

    public void setData(ArrayList<Notifica> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        NotificaItemBinding binding;

        public ViewHolder(@NonNull NotificaItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public static NotificheItemAdapter.ViewHolder inflateFrom(ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            @NonNull NotificaItemBinding binding = NotificaItemBinding.inflate(layoutInflater, parent, false);
            return new NotificheItemAdapter.ViewHolder(binding);
        }

        public void bind(Notifica notifica) {
            binding.setNotifica(notifica);
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    @NonNull
    @Override
    public NotificheItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        return NotificheItemAdapter.ViewHolder.inflateFrom(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Notifica item = data.get(position);
        ViewHolder binding = (ViewHolder) holder;
        binding.bind(item);
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
}
