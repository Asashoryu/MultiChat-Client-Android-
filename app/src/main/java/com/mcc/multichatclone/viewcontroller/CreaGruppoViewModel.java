package com.mcc.multichatclone.viewcontroller;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mcc.multichatclone.controller.Controller;
import com.mcc.multichatclone.model.Gruppo;

public class CreaGruppoViewModel extends ViewModel {

    public MutableLiveData<Boolean> indietro = new MutableLiveData<>(false);

    Controller controller = Controller.getInstance();
    Gruppo g;

    public void aggiungiGruppo(String text) {
        g = new Gruppo();
        g.setNome(text);
        controller.getGruppi().add(g);
        tornaIndietro();
    }

    private void tornaIndietro () {
        indietro.setValue(true);
        indietro.setValue(false);
    }

}
