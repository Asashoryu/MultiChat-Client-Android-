package com.mcc.multichatclone.model;

import java.util.ArrayList;

public class Gruppo {
    private String nome;

    private String amministratore;

    private ArrayList<Messaggio> messaggi;

    public Gruppo() {
        messaggi = new ArrayList<>();
    }

    public Gruppo(String nome) {
        this.nome = nome;
        messaggi = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAmministratore() {
        return amministratore;
    }

    public void setAmministratore(String amministratore) {
        this.amministratore = amministratore;
    }

    public ArrayList<Messaggio> getMessaggi() {
        return messaggi;
    }

    public void setMessaggi(ArrayList<Messaggio> messaggi) {
        this.messaggi = messaggi;
    }
}
