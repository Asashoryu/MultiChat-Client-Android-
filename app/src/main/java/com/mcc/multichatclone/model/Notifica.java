package com.mcc.multichatclone.model;

public class Notifica {

    private String richiedente;

    private String gruppo_richiesto;

    public Notifica(String richiedente, String gruppo_richiesto) {
        this.richiedente = richiedente;
        this.gruppo_richiesto = gruppo_richiesto;
    }

    public String getRichiedente() {
        return richiedente;
    }

    public void setRichiedente(String richiedente) {
        this.richiedente = richiedente;
    }

    public String getGruppo_richiesto() {
        return gruppo_richiesto;
    }

    public void setGruppo_richiesto(String gruppo_richiesto) {
        this.gruppo_richiesto = gruppo_richiesto;
    }
}
