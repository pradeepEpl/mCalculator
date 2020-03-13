package com.ap.android.navcomponent.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ap.android.navcomponent.BuildConfig;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(BuildConfig.FLAVOR);
    }

    public LiveData<String> getText() {
        return mText;
    }
}