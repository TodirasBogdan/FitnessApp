package com.example.fitnessapp.ui.about;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AboutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This app was created by Android developers Filip Vlad, Grosu Matei and Todiras Bogdan.\n" +
                "Subject to the terms of the Google LLC License Agreement.\n" +
                "All rights reserved.\n" +
                "Inquiry: support@fitfuel.com.\n");
    }
    public LiveData<String> getText() {
        return mText;
    }
}