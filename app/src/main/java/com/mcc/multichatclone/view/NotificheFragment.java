package com.mcc.multichatclone.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mcc.multichatclone.databinding.FragmentNotificheBinding;
import com.mcc.multichatclone.viewcontroller.GruppiItemAdapter;
import com.mcc.multichatclone.viewcontroller.NotificheItemAdapter;
import com.mcc.multichatclone.viewcontroller.NotificheViewModel;

public class NotificheFragment extends Fragment {

    FragmentNotificheBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNotificheBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        NotificheViewModel notificheModel = new ViewModelProvider(this).get(NotificheViewModel.class);
        binding.setNotificheViewModel(notificheModel);

        NotificheItemAdapter adapter = new NotificheItemAdapter();
        binding.notificheList.setAdapter(adapter);

        notificheModel.messaggio.observe(getViewLifecycleOwner(), messaggio ->
        {
            if (!messaggio.equals("false")) {
                Toast.makeText(binding.getRoot().getContext(), messaggio, Toast.LENGTH_SHORT).show();
                notificheModel.setMessaggioFalse();
            }
        });

        notificheModel.listaNotifiche.observe(getViewLifecycleOwner(), lista ->
        {
            adapter.setData(lista);
        });

        return view;
    }
}