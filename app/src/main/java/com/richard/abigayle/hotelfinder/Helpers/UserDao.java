package com.richard.abigayle.hotelfinder.Helpers;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

/**
 * Created by LENOVO on 5/10/2018.
 */
@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   void insertAll(User user);

    @Query("SELECT * FROM user")
    User getUser();

    @Query("UPDATE user SET userLocation=:location")
    void setUserLocation(String location);

    @Query("UPDATE user SET locationImage=:image")
    void setLocationImage(String image);

    @Query("UPDATE user SET dayOfMonthIn =:dateIn,dayOfWeekIn=:dayIn,dayOfWeekOut=:dateOut,dayOfMonthOut=:dayOut")
    void setDate(String dateIn, String dayIn, String dateOut,String dayOut);






}
