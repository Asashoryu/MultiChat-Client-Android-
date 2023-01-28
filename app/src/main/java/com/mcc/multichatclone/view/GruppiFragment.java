package com.mcc.multichatclone.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.mcc.multichatclone.R;
import com.mcc.multichatclone.databinding.FragmentGruppiBinding;
import com.mcc.multichatclone.viewcontroller.GruppiItemAdapter;
import com.mcc.multichatclone.viewcontroller.GruppiViewModel;

public class GruppiFragment extends Fragment {

    FragmentGruppiBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGruppiBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        GruppiViewModel gruppiModel = new ViewModelProvider(this).get(GruppiViewModel.class);
        binding.setGruppiViewModel(gruppiModel);

        //Passa a ogni item dell'adapter creato la seguente azione da eseguire
        GruppiItemAdapter adapter = new GruppiItemAdapter((gruppo) -> {
            gruppiModel.onGruppoClicked(gruppo);
        });
        binding.gruppiList.setAdapter(adapter);

        // Osserva e rifletti i cambiamenti che avvengono alla lista di gruppi
        gruppiModel.gruppoModel.observe(getViewLifecycleOwner(), lista -> {
            adapter.setData(lista);
        });

        // Osserva se devi navigare al gruppo selezionato
        gruppiModel.navigare.observe(getViewLifecycleOwner(), navigare -> {
            if (navigare == true) {
                Navigation.findNavController(view).navigate(R.id.action_gruppiFragment_to_chatFragment);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        binding.toolbar.inflateMenu(R.menu.menu);

        binding.toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case (R.id.new_group):
                    Navigation.findNavController(view).navigate(R.id.action_gruppiFragment_to_creaGruppoFragment);
                    return true;
                case (R.id.search_group):
                    Navigation.findNavController(view).navigate(R.id.action_gruppiFragment_to_cercaGruppoFragment);
                    return true;
                case (R.id.notifica):
                    Navigation.findNavController(view).navigate(R.id.action_gruppiFragment_to_notificaFragment);
                    return true;
                default:
                    return false;
            }
        });
        binding.getGruppiViewModel().aggiornaGruppi();
    }


}