package com.richard.abigayle.hotelfinder.Helpers;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

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

    @Query("SELECT * FROM hotel WHERE id = :htelId")
    Hotels singleHotel(int hotelId);

}
