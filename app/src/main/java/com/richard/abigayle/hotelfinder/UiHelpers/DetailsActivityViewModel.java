package com.richard.abigayle.hotelfinder.UiHelpers;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.richard.abigayle.hotelfinder.Helpers.Hotels;

/**
 * Created by LENOVO on 3/22/2018.
 */

public class DetailsActivityViewModel extends ViewModel {


    MutableLiveData<Hotels> mHotels;

    public DetailsActivityViewModel(){
        mHotels = new MutableLiveData<>();

    }

    public MutableLiveData<Hotels> getHotels(){
        return mHotels;
    }
    public void setHotels(Hotels hotels){
        mHotels.postValue(hotels);

    }
}
