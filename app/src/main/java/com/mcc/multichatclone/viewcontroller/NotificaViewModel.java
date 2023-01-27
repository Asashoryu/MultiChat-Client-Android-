package com.mcc.multichatclone.viewcontroller;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mcc.multichatclone.controller.Controller;

public class NotificaViewModel extends ViewModel {

    Controller controller;
    public MutableLiveData<Boolean> navigare = new MutableLiveData<>(false);

    public void naviga() {
        navigare.setValue(true);
        navigare.setValue(false);
    }
}
