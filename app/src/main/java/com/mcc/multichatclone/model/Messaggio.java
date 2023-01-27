package com.mcc.multichatclone.model;

public class Messaggio {
    private String mittente;
    private String contenuto;

    private String minutaggio;

    public Messaggio(){}

    public Messaggio(String mittente, String contenuto) {
        setMittente(mittente);
        setContenuto(contenuto);
    }
    public Messaggio(String mittente, String contenuto, String minutaggio) {
        setMittente(mittente);
        setContenuto(contenuto);
        setMinutaggio(minutaggio);
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

    public String getMinutaggio() {
        return minutaggio;
    }

    public void setMinutaggio(String minutaggio) {
        this.minutaggio = minutaggio;
    }
}
