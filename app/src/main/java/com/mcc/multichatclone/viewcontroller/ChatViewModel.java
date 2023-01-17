package com.mcc.multichatclone.viewcontroller;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mcc.multichatclone.controller.Controller;
import com.mcc.multichatclone.model.Gruppo;
import com.mcc.multichatclone.model.Messaggio;

import java.util.ArrayList;

public class ChatViewModel extends ViewModel{
    Gruppo gruppoChat;
    ArrayList<Messaggio> messaggiChat;
    private Controller controller;

    public MutableLiveData<ArrayList<Messaggio>> listaMessaggi = new MutableLiveData<ArrayList<Messaggio>>();
    public MutableLiveData<Boolean> messaggioInviato = new MutableLiveData<>(false);

    public ChatViewModel() {
        Controller controller = Controller.getInstance();
        gruppoChat = controller.getGruppoNavigato();
        messaggiChat = gruppoChat.getMessaggi();
        listaMessaggi.setValue(messaggiChat);
    }

    public String getNomeGruppo() {
        return controller.getGruppoNavigato().getNome();
    }

    public void onMessaggioInviato(String testo) {
        messaggiChat.add(new Messaggio("alex", testo));
        listaMessaggi.setValue(messaggiChat);
        echo(testo);
        messaggioInviato.setValue(true);
        messaggioInviato.setValue(false);
    }

    private void echo(String testo) {
        messaggiChat.add(new Messaggio("echo", testo));
        listaMessaggi.setValue(messaggiChat);
    }
}
