package com.mcc.multichatclone.viewcontroller;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mcc.multichatclone.controller.Controller;
import com.mcc.multichatclone.model.Gruppo;
import com.mcc.multichatclone.model.Messaggio;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ChatViewModel extends ViewModel{
    Gruppo gruppoChat;
    ArrayList<Messaggio> messaggiChat;

    public MutableLiveData<String> ricevutoMessaggio = new MutableLiveData<>("false");

    private Controller controller;

    public MutableLiveData<ArrayList<Messaggio>> listaMessaggi = new MutableLiveData<ArrayList<Messaggio>>();
    public ChatViewModel() {
        Controller controller = Controller.getInstance();
        controller.setChatModel(this);
        gruppoChat = controller.getGruppoNavigato();
        messaggiChat = gruppoChat.getMessaggi();
        listaMessaggi.setValue(messaggiChat);
    }

    public void setRicevutoMessaggio(@NotNull String text) {
        System.err.println("Entrato in ricevuto messaggio con : " + text);
        ricevutoMessaggio.postValue(text);
    }

    public void setRicevutoMessaggioFalse() {
        ricevutoMessaggio.setValue("false");
    }

    public String getNomeGruppo() {
        return controller.getGruppoNavigato().getNome();
    }

    public void aggiorna() {
        listaMessaggi.postValue(messaggiChat);
    }

    public void onMessaggioInviato(@NotNull String testo) {
        try {
            if (testo == null) {
                System.err.println("il testo del messaggio è null");
            }
            else {
                System.err.println("Il testo è :" + testo);
                controller = Controller.getInstance();
                controller.sendMessaggio(testo);
            }
        } catch (InterruptedException e) {
            setRicevutoMessaggio(e.getMessage());
        }
    }
}
