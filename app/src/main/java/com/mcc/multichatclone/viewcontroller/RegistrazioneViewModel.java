package com.mcc.multichatclone.viewcontroller;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegistrazioneViewModel extends ViewModel {

    public MutableLiveData<Boolean> indietro = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> avanti = new MutableLiveData<>(false);

    public void tornaIndietro() {
        indietro.setValue(true);
        indietro.setValue(false);
    }

    public void vaiAvanti() {
        avanti.setValue(true);
        avanti.setValue(false);
    }

}
