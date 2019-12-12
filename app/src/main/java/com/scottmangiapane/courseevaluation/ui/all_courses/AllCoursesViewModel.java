package com.scottmangiapane.courseevaluation.ui.all_courses;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AllCoursesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AllCoursesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is all courses fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}