package com.mcc.multichatclone.viewcontroller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcc.multichatclone.databinding.MessageBinding;
import com.mcc.multichatclone.databinding.OtherMessageBinding;
import com.mcc.multichatclone.model.Messaggio;

import java.sql.Driver;
import java.util.ArrayList;

public class ChatItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Messaggio> messaggi = new ArrayList<>();
    private final int SPEDITO = 1;
    private final int RICEVUTO = 2;

    public void setData(ArrayList<Messaggio> messaggi) {
        this.messaggi = messaggi;
        notifyDataSetChanged();
    }
    // ViewHolder Ricevuto
    public static class RicevutoViewHolder extends RecyclerView.ViewHolder {

        OtherMessageBinding binding;

        public RicevutoViewHolder(@NonNull OtherMessageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public static ChatItemAdapter.RicevutoViewHolder inflateFrom(ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            @NonNull OtherMessageBinding binding = OtherMessageBinding.inflate(layoutInflater, parent, false);
            return new ChatItemAdapter.RicevutoViewHolder(binding);
        }

        public void bind(Messaggio messaggio) {
            binding.setMessaggioRicevuto(messaggio);
        }
    }
    // ViewHolder Spedito
    public static class SpeditoViewHolder extends RecyclerView.ViewHolder {

        MessageBinding binding;

        public SpeditoViewHolder(@NonNull MessageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public static ChatItemAdapter.SpeditoViewHolder inflateFrom(ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            @NonNull MessageBinding binding = MessageBinding.inflate(layoutInflater, parent, false);
            return new ChatItemAdapter.SpeditoViewHolder(binding);
        }

        public void bind(Messaggio messaggio) {
            binding.setMessaggioInviato(messaggio);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        if (viewType == RICEVUTO) {
            return ChatItemAdapter.RicevutoViewHolder.inflateFrom(parent);
        }
        else if (viewType == SPEDITO) {
            return ChatItemAdapter.SpeditoViewHolder.inflateFrom(parent);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Messaggio item = messaggi.get(position);
        if (holder.getItemViewType() == RICEVUTO) {
            RicevutoViewHolder binding = (RicevutoViewHolder) holder;
            binding.bind(item);
        }
        else if (holder.getItemViewType() == SPEDITO) {
            SpeditoViewHolder binding = (SpeditoViewHolder) holder;
            binding.bind(item);
        }
    }

    @Override
    public int getItemCount() {
        return messaggi.size();
    }

    // specifica come interpretare i diversi messaggi
    @Override
    public int getItemViewType(int position) {
        if (messaggi.get(position).getMittente().equals("alex")) {
            return SPEDITO;
        }
        else {
            return RICEVUTO;
        }
    }
}
