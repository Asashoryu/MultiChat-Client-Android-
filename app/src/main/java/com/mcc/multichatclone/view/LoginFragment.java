package com.mcc.multichatclone.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.mcc.multichatclone.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mcc.multichatclone.viewcontroller.LoginViewModel;
import com.mcc.multichatclone.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        FragmentLoginBinding binding = FragmentLoginBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        LoginViewModel loginModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setLoginViewModel(loginModel);

        //live data
        final Observer<Boolean> osservaSeTornareIndietro = nuovoValore -> {
            // Naviga
            if (nuovoValore == true) {
                Navigation.findNavController(view).popBackStack();
            }
        };

        final Observer<Boolean> osservaSeAndareAvanti = avanti -> {
            // Naviga
            if (avanti == true) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_gruppiFragment);
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        loginModel.indietro.observe(getViewLifecycleOwner(), osservaSeTornareIndietro);
        loginModel.avanti.observe(getViewLifecycleOwner(), osservaSeAndareAvanti);


        return view;
    }
}