package com.mcc.multichatclone.viewcontroller;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mcc.multichatclone.controller.Controller;
import com.mcc.multichatclone.model.Gruppo;

import org.jetbrains.annotations.NotNull;

public class CreaGruppoViewModel extends ViewModel {

    public MutableLiveData<Boolean> indietro = new MutableLiveData<>(false);
    public MutableLiveData<String> messaggioErrore = new MutableLiveData<>("false");

    Controller controller;
    Gruppo g;

    public void setMessaggioErrore(String text) {
        System.err.println("Entrato in setMessaggioErrore con : " + text);
        messaggioErrore.postValue(text);
    }

    public void setMessaggioErroreFalse() {
        messaggioErrore.setValue("false");
    }

    public void aggiungiGruppo(String text) {
        try {
            controller = Controller.getNewInstance();
            controller.setcreaGruppoModel(this);
            controller.creaGruppo(text);
        } catch (Exception e) {
            setMessaggioErrore(e.getMessage());
        }
        //tornaIndietro();
    }

    private void tornaIndietro () {
        indietro.setValue(true);
        indietro.setValue(false);
    }

}
