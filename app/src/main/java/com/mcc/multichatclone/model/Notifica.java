package com.mcc.multichatclone.model;

public class Notifica {

    private String richiedente;

    private String gruppoRichiesto;

    private String messaggioNotifica = "";

    public Notifica(String richiedente, String gruppoRichiesto) {
        this.richiedente = richiedente;
        this.gruppoRichiesto = gruppoRichiesto;
        setMessaggioNotifica("Richiesta di accesso al gruppo " + gruppoRichiesto + " da parte di " + richiedente);
    }

    public String getRichiedente() {
        return richiedente;
    }

    public void setRichiedente(String richiedente) {
        this.richiedente = richiedente;
    }

    public String getGruppoRichiesto() {
        return gruppoRichiesto;
    }

    public void setGruppoRichiesto(String gruppoRichiesto) {
        this.gruppoRichiesto = gruppoRichiesto;
    }

    public String getMessaggioNotifica() {
        return messaggioNotifica;
    }

    public void setMessaggioNotifica(String messaggioNotifica) {
        this.messaggioNotifica = messaggioNotifica;
    }
}
