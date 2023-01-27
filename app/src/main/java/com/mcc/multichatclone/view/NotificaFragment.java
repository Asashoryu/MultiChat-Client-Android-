package com.mcc.multichatclone.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mcc.multichatclone.R;
import com.mcc.multichatclone.databinding.FragmentGruppiBinding;
import com.mcc.multichatclone.databinding.FragmentNotificaBinding;
import com.mcc.multichatclone.viewcontroller.GruppiViewModel;
import com.mcc.multichatclone.viewcontroller.NotificaViewModel;

public class NotificaFragment extends Fragment {

    FragmentNotificaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNotificaBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        NotificaViewModel notificaModel = new ViewModelProvider(this).get(NotificaViewModel.class);
        binding.setLoginViewModel(notificaModel);

        return view;
    }
}