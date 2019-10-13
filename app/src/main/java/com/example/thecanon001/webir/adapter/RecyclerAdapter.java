package com.example.thecanon001.webir.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.thecanon001.webir.R;
import com.example.thecanon001.webir.entity.Vehicle;

import java.util.List;

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private List<String> vehicleImageList;
    private OnclickRecycler listener;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.galery_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        String vehicleImage = vehicleImageList.get(position);
        holder.bind(vehicleImage, listener);
    }

    @Override
    public int getItemCount(){
        return vehicleImageList.size();
    }

    public interface OnclickRecycler{
        void OnclickItemRecycler(String vehicleImage);
    }

    public RecyclerAdapter(List<String> vehicleImageList, OnclickRecycler listener){
        this.vehicleImageList = vehicleImageList;
        this.listener = listener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public MyViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.image_galery);
        }

        public void bind(final String vehicleImage, final OnclickRecycler listener){
            Glide.with(imageView.getContext()).load(vehicleImage).into(imageView);
            itemView.setOnClickListener(v -> {
                listener.OnclickItemRecycler(vehicleImage);
            });
        }
    }
}
