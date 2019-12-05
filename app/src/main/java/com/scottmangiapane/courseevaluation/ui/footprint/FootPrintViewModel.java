package com.scottmangiapane.courseevaluation.ui.footprint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FootPrintViewModel extends ViewModel {

    //绑定相关的observerse
    private MutableLiveData<String> mText;

    private  MutableLiveData<Footprint>collect;

    private MutableLiveData<Footprint>participate ;

    public FootPrintViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("教师所属");

       Footprint user_one=new Footprint(2,"数据库原理","李建国",4.5,"aaaa");

        collect=new MutableLiveData<>();
        collect.setValue(user_one);

    }

    public  LiveData<Footprint>getUserInfo(){
        return collect;
    }

    public LiveData<String> getText() {

        return mText;
    }
}