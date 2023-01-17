package com.mcc.multichatclone.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.mcc.multichatclone.viewcontroller.InizioViewModel;
import com.mcc.multichatclone.R;
import com.mcc.multichatclone.databinding.FragmentInizioBinding;


import androidx.lifecycle.Observer;

public class InizioFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentInizioBinding binding = FragmentInizioBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        InizioViewModel inizioModel = new ViewModelProvider(this).get(InizioViewModel.class);
        binding.setInizioViewModel(inizioModel);

        //live data
        final Observer<Boolean> osservatore = prosegui -> {
            // Naviga
            if (prosegui == true) {
                Navigation.findNavController(view).navigate(R.id.action_inizioFragment_to_loginFragment);
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        inizioModel.prosegui.observe(getViewLifecycleOwner(), osservatore);

        return view;
    }
}