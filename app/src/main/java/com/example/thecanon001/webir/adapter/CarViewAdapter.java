package com.example.thecanon001.webir.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.thecanon001.webir.R;
import com.example.thecanon001.webir.entity.Vehicle;
import com.example.thecanon001.webir.fragment.DialogFragmentGalery;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CarViewAdapter extends RecyclerView.Adapter<CarViewAdapter.ViewHolderView>{

    private ArrayList<Vehicle> vehicleListView;
    private Context context;

    public CarViewAdapter(ArrayList<Vehicle> vehicleListView, Context context) {
        this.vehicleListView = vehicleListView;
        this.context = context;
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
        if(vehicleListView.get(position).getPhotos() != null || !vehicleListView.get(position).getPhotos().isEmpty()) {
            String[] photos = vehicleListView.get(position).getPhotos().split(",");
            Picasso.with(context).load(photos[0]).placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(holder.mCarImage, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {

                        }
                    });
        }
        holder.mBrand.setText(vehicleListView.get(position).getBrand());
        holder.mCondition.setText(vehicleListView.get(position).getCondition());
        holder.mPrice.setText(String.valueOf(vehicleListView.get(position).getPrice()));
        holder.mCurrency.setText(vehicleListView.get(position).getCurrency());
        holder.mCardView.setOnClickListener(v -> {
            FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
            DialogFragmentGalery galery = new DialogFragmentGalery();
            Bundle bundle = new Bundle();
            bundle.putSerializable("vehicle", vehicleListView.get(position));
            galery.setArguments(bundle);
            galery.setStyle(DialogFragment.STYLE_NO_FRAME, R.style.transparent);
            galery.show(manager,"");
        });
    }


    @Override
    public int getItemCount() {
        return vehicleListView.size();
    }

    public void updateListView(ArrayList<Vehicle> vehicleList){
        this.vehicleListView = vehicleList;
        notifyDataSetChanged();
    }

    public class ViewHolderView extends RecyclerView.ViewHolder {
        android.support.v7.widget.CardView mCardView;
        ImageView mCarImage;
        TextView mBrand;
        TextView mCondition;
        TextView mPrice;
        TextView mCurrency;

        public ViewHolderView(View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.list_view);
            mCarImage = itemView.findViewById(R.id.image_view);
            mBrand = itemView.findViewById(R.id.text_brand);
            mCondition = itemView.findViewById(R.id.text_condition);
            mPrice = itemView.findViewById(R.id.text_price);
            mCurrency = itemView.findViewById(R.id.text_currency);
        }
    }

}
