package com.richard.abigayle.hotelfinder.UiHelpers;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.richard.abigayle.hotelfinder.Helpers.HotelRepository;
import com.richard.abigayle.hotelfinder.Helpers.Hotels;

/**
 * Created by LENOVO on 3/22/2018.
 */

public class DetailsActivityViewModel extends AndroidViewModel {
   private HotelRepository hotelRepository;
   private LiveData<Hotels>mHotels;


    public DetailsActivityViewModel(Application application){
        super(application);
       hotelRepository = new HotelRepository(application);


    }



    public LiveData<Hotels> getHotels(int position){
        mHotels = hotelRepository.getSingleHotel(position);
        return mHotels;
    }

}
