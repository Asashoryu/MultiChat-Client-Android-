package com.mcc.multichatclone.viewcontroller;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mcc.multichatclone.controller.Controller;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<Boolean> indietro = new MutableLiveData<>(false);
    public MutableLiveData<String> loggato = new MutableLiveData<>("false");
    public MutableLiveData<Boolean> registrazione = new MutableLiveData<>(false);

    Controller controller;
    public void tornaIndietro() {
        indietro.setValue(true);
        indietro.setValue(false);
    }

    public void setLoggatoFalse() {
        loggato.setValue("false");
    }

    public void setLoggato(String text) {
        System.err.println("Entrato in loggato con : " + text);
        loggato.postValue("false");
        loggato.postValue(text);
    }

    public void registrati() {
        registrazione.setValue(true);
        registrazione.setValue(false);
    }

    public void login(String nome, String password) {
        try {
            controller = Controller.getNewInstance();
            controller.setLoginModel(this);
            controller.login(nome, password);
        } catch (Exception e) {
            setLoggato(e.getMessage());
        }
    }

}
