package com.mcc.multichatclone.viewcontroller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.compose.ui.text.android.style.PlaceholderSpan;
import androidx.recyclerview.widget.RecyclerView;

import com.mcc.multichatclone.R;
import com.mcc.multichatclone.databinding.GruppoItemV2Binding;
import com.mcc.multichatclone.generated.callback.OnClickListener;
import com.mcc.multichatclone.model.Gruppo;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class GruppiItemAdapter extends RecyclerView.Adapter<GruppiItemAdapter.ViewHolder> {

    private ArrayList<Gruppo> data = new ArrayList<>();
    NavigaGruppo clickListener;

    public GruppiItemAdapter(NavigaGruppo clickListener ) {
        this.clickListener = clickListener;
    }

    public void setData(ArrayList<Gruppo> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        GruppoItemV2Binding binding;

        public ViewHolder(@NonNull GruppoItemV2Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public static ViewHolder inflateFrom(ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            @NonNull GruppoItemV2Binding binding = GruppoItemV2Binding.inflate(layoutInflater, parent, false);
            return new ViewHolder(binding);
        }

        public void bind(Gruppo gruppo, NavigaGruppo clickListener) {
            binding.setGruppo(gruppo);
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.lambda(gruppo);
                }
            });
        }
    }

    @NonNull
    @Override
    public GruppiItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        return ViewHolder.inflateFrom(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull GruppiItemAdapter.ViewHolder holder, int position) {
        Gruppo item = data.get(position);
        holder.bind(item, clickListener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}

