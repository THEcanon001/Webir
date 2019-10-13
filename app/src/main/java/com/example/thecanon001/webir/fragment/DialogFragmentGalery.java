package com.example.thecanon001.webir.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thecanon001.webir.R;
import com.example.thecanon001.webir.adapter.RecyclerAdapter;
import com.example.thecanon001.webir.entity.Vehicle;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;


public class DialogFragmentGalery extends DialogFragment {
    private View view;
    private TextView m_text_brand;
    private TextView m_text_condition;
    private TextView m_text_price;
    private TextView m_text_currency;
    private TextView m_title;
    private ImageView m_image;
    private android.support.v7.widget.RecyclerView m_galery;
    private ArrayList<String> imageList;
    private RecyclerAdapter adapter;
    private Vehicle vehicle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dialog_fragment_galery, container, false);
        m_text_brand = view.findViewById(R.id.text_brand);
        m_text_condition = view.findViewById(R.id.text_condition);
        m_text_price = view.findViewById(R.id.text_price);
        m_text_currency = view.findViewById(R.id.text_currency);
        m_title = view.findViewById(R.id.title);
        m_image = view.findViewById(R.id.image);
        m_galery = view.findViewById(R.id.galery);

        m_image.setOnClickListener(v -> {
            getDialog().dismiss();
        });

        assert getArguments() != null;
        vehicle = (Vehicle) getArguments().getSerializable("vehicle");
        m_text_brand.setText(vehicle.getBrand());
        m_text_condition.setText(vehicle.getCondition());
        m_text_currency.setText(vehicle.getCurrency());
        m_title.setText(vehicle.getTitle());
        m_text_price.setText(String.valueOf(vehicle.getPrice()));
        m_galery.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        String[] vehicles = vehicle.getPhotos().split(",");
        imageList = new ArrayList<>();
        imageList.addAll(Arrays.asList(vehicles));
        adapter = new RecyclerAdapter(imageList, vehicleImage -> Picasso.with(view.getContext()).load(vehicleImage).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(m_image, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                }), view.getContext());
        m_galery.setAdapter(adapter);
        return view;
    }

}
