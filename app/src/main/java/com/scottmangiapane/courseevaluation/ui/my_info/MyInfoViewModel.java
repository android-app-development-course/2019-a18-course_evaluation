package com.scottmangiapane.courseevaluation.ui.my_info;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyInfoViewModel extends ViewModel {

    private MutableLiveData<String> username;

    public MyInfoViewModel() {
        username = new MutableLiveData<>();
        //username.setValue("听见下雨的声音");
    }

    public LiveData<String> getText() {
        return username;
    }
}