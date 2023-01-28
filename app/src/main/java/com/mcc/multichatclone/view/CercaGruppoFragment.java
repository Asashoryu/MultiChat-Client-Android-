package com.mcc.multichatclone.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mcc.multichatclone.R;
import com.mcc.multichatclone.databinding.FragmentCercaGruppoBinding;
import com.mcc.multichatclone.databinding.FragmentNotificheBinding;
import com.mcc.multichatclone.viewcontroller.CercaGruppoItemAdapter;
import com.mcc.multichatclone.viewcontroller.CercaGruppoViewModel;
import com.mcc.multichatclone.viewcontroller.NotificheItemAdapter;
import com.mcc.multichatclone.viewcontroller.NotificheViewModel;

public class CercaGruppoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        FragmentCercaGruppoBinding binding = FragmentCercaGruppoBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        CercaGruppoViewModel cercaGruppoModel = new ViewModelProvider(this).get(CercaGruppoViewModel.class);
        binding.setCercaGruppoViewModel(cercaGruppoModel);

        CercaGruppoItemAdapter adapter = new CercaGruppoItemAdapter();
        binding.notificheList.setAdapter(adapter);

        cercaGruppoModel.listaGruppi.observe(getViewLifecycleOwner(), lista ->
        {
            adapter.setData(lista);
        });

        cercaGruppoModel.trovatiGruppi.observe(getViewLifecycleOwner(), messaggio ->
        {
            if (!messaggio.equals("false")) {
                if (messaggio.equals("true")) {
                    adapter.setData(cercaGruppoModel.listaGruppi.getValue());
                }
                else {
                    Toast.makeText(binding.getRoot().getContext(), messaggio, Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}