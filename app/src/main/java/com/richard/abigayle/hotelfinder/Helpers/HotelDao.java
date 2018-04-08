package com.richard.abigayle.hotelfinder.Helpers;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.util.Log;

import java.util.List;

/**
 * Created by LENOVO on 3/22/2018.
 */

@Dao
public interface HotelDao {

    @Query("SELECT * FROM hotel")
    List<Hotels> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Hotels...hotel);

    @Delete
    void  delete(Hotels...hotels);

    @Query("SELECT * FROM hotel WHERE id = :hotelId")
    LiveData<Hotels> singleHotel(int hotelId);

    @Query("UPDATE hotel SET imageId1 = :img1 WHERE placeId=:id")
    void insertImage1(String id,String img1);

    @Query("UPDATE hotel SET imageId2 = :img2 WHERE placeId=:id")
    void insertImage2 (String id,String img2);

    @Query("UPDATE hotel SET imageId3 = :img3 WHERE placeId=:id")
    void insertImage3 (String id,String img3);



}
