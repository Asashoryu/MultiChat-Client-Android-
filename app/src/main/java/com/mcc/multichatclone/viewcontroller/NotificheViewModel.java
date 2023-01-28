package com.mcc.multichatclone.viewcontroller;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mcc.multichatclone.controller.Controller;
import com.mcc.multichatclone.model.Messaggio;
import com.mcc.multichatclone.model.Notifica;

import java.util.ArrayList;

public class NotificheViewModel extends ViewModel {

    Controller controller;
    public MutableLiveData<Boolean> navigare = new MutableLiveData<>(false);

    public MutableLiveData<ArrayList<Notifica>> listaNotifiche = new MutableLiveData<ArrayList<Notifica>>();

    public MutableLiveData<String> messaggio = new MutableLiveData<>("false");

    private ArrayList<Notifica> notifiche;

    public NotificheViewModel() {
        Controller controller = Controller.getInstance();
        controller.setNotificheModel(this);
        notifiche = controller.getNotifiche();
        listaNotifiche.setValue(notifiche);
    }

    public void aggiornaNotifiche() {
        listaNotifiche.postValue(notifiche);
    }

    public void setMessaggio(String messaggio) {
        this.messaggio.postValue(messaggio);
    }

    public void setMessaggioFalse() {
        this.messaggio.setValue("false");
    }

    public void naviga() {
        navigare.setValue(true);
        navigare.setValue(false);
    }
}
