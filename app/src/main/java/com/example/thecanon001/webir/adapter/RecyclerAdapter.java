package com.example.thecanon001.webir.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.thecanon001.webir.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<String> vehicleImageList;
    private OnclickRecycler listener;
    private final Context context;

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

    public RecyclerAdapter(List<String> vehicleImageList, OnclickRecycler listener, Context context){
        this.vehicleImageList = vehicleImageList;
        this.listener = listener;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public MyViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.image_galery);
        }

        public void bind(final String vehicleImage, final OnclickRecycler listener){
            Picasso.with(context).load(vehicleImage).placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageView, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {

                        }
                    });
            itemView.setOnClickListener(v -> {
                listener.OnclickItemRecycler(vehicleImage);
            });
        }
    }
}
