package com.mcc.multichatclone.viewcontroller;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mcc.multichatclone.controller.Controller;

public class RegistrazioneViewModel extends ViewModel {

    public MutableLiveData<Boolean> indietro = new MutableLiveData<>(false);
    public MutableLiveData<String> registrato = new MutableLiveData<>("false");

    private Controller controller;

    public void tornaIndietro() {
        indietro.setValue(true);
        indietro.setValue(false);
    }

    public void setRegistrato(String text) {
        System.err.println("Entrato in loggato con : " + text);
        registrato.postValue("false");
        registrato.postValue(text);
    }

    public void setRegistratoFalse() {
        registrato.setValue("false");
    }

    public void signin(String nome, String password) {
        try {
            controller = Controller.getNewInstance();
            controller.setRegistrazioneModel(this);
            controller.signin(nome, password);
        } catch (Exception e) {
            setRegistrato(e.getMessage());
        }
    }

}
