package com.mcc.multichatclone.model;

import java.util.ArrayList;

public class Gruppo {
    private String nome;

    private String proprietario;

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

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public ArrayList<Messaggio> getMessaggi() {
        return messaggi;
    }

    public void setMessaggi(ArrayList<Messaggio> messaggi) {
        this.messaggi = messaggi;
    }
}
