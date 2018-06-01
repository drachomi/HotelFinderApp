package com.richard.abigayle.hotelfinder.UiHelpers;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.richard.abigayle.hotelfinder.Activities.DetailsActivity.HotelDetailsActivity;
import com.richard.abigayle.hotelfinder.Helpers.Hotels;
import com.richard.abigayle.hotelfinder.R;

import java.io.File;
import java.util.List;

/**
 * Created by LENOVO on 4/21/2018.
 */

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.myViewHolder> {
     class myViewHolder extends RecyclerView.ViewHolder{
        private TextView name,address,km_away;
        RatingBar ratingBar;
        ImageView imageView;


        private myViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            km_away = itemView.findViewById(R.id.km_away);
            imageView = itemView.findViewById(R.id.display_image);
            ratingBar = itemView.findViewById(R.id.rating);

        }
    }
    private List<Hotels>mHotels;
    private LayoutInflater mLayoutInflater;
    public HotelAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }



//    private Bitmap getImage(String path,int position){
//        File imgFile;
//
//         Bitmap myBitmap = null;
//
//
//            imgFile = new File(path);
//            Log.d("bitmap","path is " + path);
//            Log.d("bitmap","Image exist " + position);
//            myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//
//        return myBitmap;
//
//    }
    public void setHotels(List<Hotels> hotel){
        mHotels = hotel;
        notifyDataSetChanged();
    }





    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.hotel_list_model,parent,false);
        return new myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        Hotels hotel = mHotels.get(position);
        if(hotel.placeName!=null){

            holder.name.setText(hotel.placeName);
            holder.address.setText(hotel.placeAd);
            holder.km_away.setText(String.valueOf(hotel.duration));
            holder.ratingBar.setRating(hotel.placeRating);
            if (hotel.imageId1 == null){
                holder.imageView.setImageResource(R.drawable.wifi_purple);
            }
            else {

                Glide.with(holder.imageView.getContext())
                        .load(new File(hotel.imageId2))
                        .into(holder.imageView);
            }




        }

        if(hotel.placeName== null){
            holder.name.setText("Hotel Name");
            holder.address.setText("Address incoming");
            holder.km_away.setText("35000");
            holder.ratingBar.setRating(3);
            holder.imageView.setImageBitmap(BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.hotelroom));
        }


    }

    @Override
    public int getItemCount() {
        if(mHotels!=null){
            return mHotels.size();
        }
        else{
            return 0;
        }

    }

}
