package com.mcc.multichatclone.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mcc.multichatclone.R;
import com.mcc.multichatclone.databinding.FragmentLoginBinding;
import com.mcc.multichatclone.databinding.FragmentRegistrazioneBinding;
import com.mcc.multichatclone.viewcontroller.LoginViewModel;
import com.mcc.multichatclone.viewcontroller.RegistrazioneViewModel;

public class RegistrazioneFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentRegistrazioneBinding binding = FragmentRegistrazioneBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        RegistrazioneViewModel registrazioneModel = new ViewModelProvider(this).get(RegistrazioneViewModel.class);
        binding.setRegistrazioneViewModel(registrazioneModel);

        final Observer<Boolean> osservaSeTornareIndietro = nuovoValore -> {
            // Naviga
            if (nuovoValore == true) {
                Navigation.findNavController(view).popBackStack();
            }
        };

        registrazioneModel.registrato.observe(getViewLifecycleOwner(), (registrato) -> {
            if (registrato.equals("false")) {

            }
            else if (registrato.equals("true")) {
                registrazioneModel.setRegistratoFalse();
                Navigation.findNavController(view).navigate(R.id.action_registrazioneFragment_to_gruppiFragment);
            }
            else {
                Toast.makeText(binding.getRoot().getContext(), registrato, Toast.LENGTH_SHORT).show();
                registrazioneModel.setRegistratoFalse();
            }
        });

        registrazioneModel.indietro.observe(getViewLifecycleOwner(), osservaSeTornareIndietro);

        return view;
    }
}