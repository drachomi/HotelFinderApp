package com.richard.abigayle.hotelfinder.UiHelpers;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), HotelDetailsActivity.class);

                }
            });
        }
    }
    private List<Hotels>mHotels;
    private LayoutInflater mLayoutInflater;
    public HotelAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }



    private Bitmap getImage(String path){
        File imgFile = new File(path);
        Bitmap myBitmap = null;

        if(imgFile.exists()) {

             myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }
        else {
            myBitmap = BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.hotelroom);
        }
        return myBitmap;

    }
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
        if(mHotels!=null){
            Log.d("ari","Hotel is not null");
            Hotels hotel = mHotels.get(position);
            Log.d("ari","Got position");
            holder.name.setText(hotel.placeName.toString());
            holder.address.setText(hotel.placeAd.toString());
            holder.km_away.setText(String.valueOf(hotel.distance));
            holder.imageView.setImageBitmap(getImage(hotel.imageId1));
            holder.ratingBar.setRating(hotel.placeRating);

        }
        else {
            holder.name.setText("Hotel Name");
            holder.address.setText("Address incoming");
            holder.km_away.setText("35000");
            holder.imageView.setImageBitmap(BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.hotelroom));
            holder.ratingBar.setRating(3);

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
