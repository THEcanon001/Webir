package com.example.thecanon001.webir.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.thecanon001.webir.R;
import com.example.thecanon001.webir.entity.Car;

import java.util.ArrayList;

public class CarViewAdapter extends RecyclerView.Adapter<CarViewAdapter.ViewHolderView>{

    private ArrayList<Car> carListView;

    public CarViewAdapter(ArrayList<Car> carListView) {
        this.carListView = carListView;
    }

    @NonNull
    @Override
    public ViewHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view, null, false);
        return new ViewHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderView holder, final int position) {
        if(carListView.get(position).getImage() != null || !carListView.get(position).getImage().isEmpty()) {
            byte[] decodedString = Base64.decode(carListView.get(position).getImage(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.mCarImage.setImageBitmap(decodedByte);
        }
        holder.mModel.setText(carListView.get(position).getModel());
        holder.mKm.setText(carListView.get(position).getKm());
        holder.mPrice.setText(carListView.get(position).getPrice());
        holder.mLocation.setText(carListView.get(position).getLocation());
    }


    @Override
    public int getItemCount() {
        return carListView.size();
    }

    public void updateListView(ArrayList<Car> carList){
        this.carListView = carList;
        notifyDataSetChanged();
    }

    public class ViewHolderView extends RecyclerView.ViewHolder {
        ImageView mCarImage;
        TextView mModel;
        TextView mKm;
        TextView mPrice;
        TextView mLocation;

        public ViewHolderView(View itemView) {
            super(itemView);
            mCarImage = itemView.findViewById(R.id.image_view);
            mModel = itemView.findViewById(R.id.text_model);
            mKm = itemView.findViewById(R.id.text_km);
            mPrice = itemView.findViewById(R.id.text_price);
            mLocation = itemView.findViewById(R.id.text_location);
        }
    }

}
