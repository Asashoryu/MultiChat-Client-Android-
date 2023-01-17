package com.mcc.multichatclone.model;

public class Messaggio {
    private String mittente;
    private String contenuto;

    public Messaggio(){}
    public Messaggio(String mittente, String contenuto) {
        setMittente(mittente);
        setContenuto(contenuto);
    }

    public String getMittente() {
        return mittente;
    }

    public void setMittente(String mittente) {
        this.mittente = mittente;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }
}
