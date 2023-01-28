package com.mcc.multichatclone.viewcontroller;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mcc.multichatclone.controller.Controller;
import com.mcc.multichatclone.model.Gruppo;
import com.mcc.multichatclone.model.Notifica;

import java.util.ArrayList;

public class CercaGruppoViewModel extends ViewModel {
    public MutableLiveData<ArrayList<Gruppo>> listaGruppi = new MutableLiveData<ArrayList<Gruppo>>();

    public MutableLiveData<String> trovatiGruppi = new MutableLiveData<>("false");

    Controller controller;

    public void setlistaGruppiCercati() {
        controller = Controller.getInstance();
        listaGruppi.setValue(controller.getGruppiCercati());
    }

    public void setTrovatiGruppi(String text) {
        System.err.println("Entrato in setTrovatiGruppi con : " + text);
        trovatiGruppi.postValue(text);
    }

    public void cercaGruppo(String text) {
        try {
            controller = Controller.getInstance();
            controller.setCercaGruppoModel(this);
            controller.cercaGruppo(text);
        } catch (Exception e) {
            setTrovatiGruppi(e.getMessage());
        }
    }

}
