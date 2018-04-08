package com.richard.abigayle.hotelfinder.UiHelpers;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.richard.abigayle.hotelfinder.Helpers.HotelRepository;
import com.richard.abigayle.hotelfinder.Helpers.Hotels;

/**
 * Created by LENOVO on 3/22/2018.
 */

public class DetailsActivityViewModel extends ViewModel {


    private MutableLiveData<Hotels> mHotels;

    public DetailsActivityViewModel(){
        mHotels = new MutableLiveData<>();

    }

    public MutableLiveData<Hotels> getHotels(){
        return mHotels;
    }
    public void setHotels(Hotels hotels){
        Log.d("shoot","Entered Set hotel in ViewModel class");

        mHotels.postValue(hotels);
        Log.d("shoot","Finished posting value to ROOM from view model");

    }

}
