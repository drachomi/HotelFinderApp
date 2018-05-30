package com.richard.abigayle.hotelfinder.UiHelpers;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.richard.abigayle.hotelfinder.Helpers.HotelRepository;
import com.richard.abigayle.hotelfinder.Helpers.Hotels;

import java.util.List;

/**
 * Created by LENOVO on 4/22/2018.
 */

public class HotelListViewModel extends AndroidViewModel {
    private HotelRepository mHotelRepo;

    private LiveData<List<Hotels>> mgetAllHotel;

    public HotelListViewModel(Application application) {
        super(application);

        mHotelRepo = new HotelRepository(application);
        mgetAllHotel = mHotelRepo.getAllHotels();


    }
    public LiveData<List<Hotels>>getAllHotel(){
        return mgetAllHotel;
    }


}
