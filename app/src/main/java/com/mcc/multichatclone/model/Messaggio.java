package com.mcc.multichatclone.model;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class Messaggio {
    private String mittente;
    private String contenuto;

    private String minutaggio;

    private String minutaggioDaMostrare;

    public Messaggio(){}

    public Messaggio(String mittente, String contenuto) {
        setMittente(mittente);
        setContenuto(contenuto);
    }
    public Messaggio(String mittente, String contenuto, String minutaggio) {
        setMittente(mittente);
        setContenuto(contenuto);
        setMinutaggio(minutaggio);
        setMinutaggioDaMostrare();
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

    public String getMinutaggioDaMostrare() {
        return minutaggioDaMostrare;
    }

    public void setMinutaggioDaMostrare() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        this.minutaggioDaMostrare = formatter.format(Date.from(Instant.ofEpochMilli(Long.parseLong(minutaggio))));
    }
}
