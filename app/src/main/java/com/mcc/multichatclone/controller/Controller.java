package com.mcc.multichatclone.controller;

import com.mcc.multichatclone.model.Gruppo;

import java.util.ArrayList;

public class Controller {

    private ArrayList<Gruppo> gruppi;
    private Gruppo gruppoNavigato;

    public Controller() {
        gruppi = new ArrayList<>();
        gruppi.add(new Gruppo("Prova 1"));
        gruppi.add(new Gruppo("Ciao Trincalex"));

        gruppoNavigato = null;
    }

    private static Controller controller = null;

    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    public ArrayList<Gruppo> getGruppi() {
        return gruppi;
    }

    public void setGruppi(ArrayList<Gruppo> gruppi) {
        this.gruppi = gruppi;
    }

    public Gruppo getGruppoNavigato() {
        return gruppoNavigato;
    }

    public void setGruppoNavigato(Gruppo gruppoNavigato) {
        this.gruppoNavigato = gruppoNavigato;
    }
}
