package com.mcc.multichatclone.viewcontroller;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mcc.multichatclone.controller.Controller;
import com.mcc.multichatclone.model.Gruppo;

import java.util.ArrayList;

public class GruppiViewModel extends ViewModel {
    public MutableLiveData<ArrayList<Gruppo>> gruppoModel = new MutableLiveData<ArrayList<Gruppo>>();

    Controller controller = Controller.getInstance();
    Gruppo g;

    public MutableLiveData<Boolean> navigare = new MutableLiveData<>(false);

    public GruppiViewModel(){
        gruppoModel.setValue(controller.getGruppi());
    }

    public void naviga() {
        navigare.setValue(true);
        navigare.setValue(false);
    }

    public void onGruppoClicked(Gruppo gruppo) {
        controller.setGruppoNavigato(gruppo);
        naviga();
    }

    public void aggiungiQualcheGruppo() {
        g = new Gruppo();
        g.setNome("Gruppo " + controller.getGruppi().size());
        controller.getGruppi().add(g);
        gruppoModel.setValue(controller.getGruppi());
    }

}
