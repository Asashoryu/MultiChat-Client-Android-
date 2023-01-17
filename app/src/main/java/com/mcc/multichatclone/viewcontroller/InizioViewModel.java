package com.mcc.multichatclone.viewcontroller;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InizioViewModel extends ViewModel {
    public MutableLiveData<Boolean> prosegui = new MutableLiveData<>(false);

    public void set_prosegui() {
        prosegui.setValue(true);
        prosegui.setValue(false);
    }

}
