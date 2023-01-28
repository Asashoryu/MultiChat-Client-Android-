package com.mcc.multichatclone.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mcc.multichatclone.R;
import com.mcc.multichatclone.databinding.FragmentCreaGruppoBinding;
import com.mcc.multichatclone.databinding.FragmentInizioBinding;
import com.mcc.multichatclone.viewcontroller.CreaGruppoViewModel;
import com.mcc.multichatclone.viewcontroller.InizioViewModel;


public class CreaGruppoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentCreaGruppoBinding binding = FragmentCreaGruppoBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        CreaGruppoViewModel creaGruppoModel = new ViewModelProvider(this).get(CreaGruppoViewModel.class);
        binding.setCreaGruppoModel(creaGruppoModel);

        binding.getCreaGruppoModel().messaggioErrore.observe(getViewLifecycleOwner(), messaggio->{
            if (messaggio.equals("true")) {
            Navigation.findNavController(view).popBackStack();
        }});

        binding.getCreaGruppoModel().indietro.observe(getViewLifecycleOwner(), tornaIndietro->{ if (tornaIndietro) {
            Navigation.findNavController(view).popBackStack();
        }});

        return view;
    }
}