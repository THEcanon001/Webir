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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

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
        m_text_brand.findViewById(R.id.text_brand);
        m_text_condition.findViewById(R.id.text_condition);
        m_text_price.findViewById(R.id.text_price);
        m_text_currency.findViewById(R.id.text_currency);
        m_image.findViewById(R.id.image_view);
        m_galery.findViewById(R.id.galery);

        m_image.setOnClickListener(v -> {
            getDialog().dismiss();
        });

        m_text_brand.setText(vehicle.getBrand());
        m_text_condition.setText(vehicle.getCondition());
        m_text_currency.setText(vehicle.getCondition());
        m_title.setText(vehicle.getTitle());
        m_text_price.setText(String.valueOf(vehicle.getPrice()));
        m_galery.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        assert getArguments() != null;
        vehicle = (Vehicle)  getArguments().getSerializable("vehicle");
        String[] vehicles = vehicle.getPhotos().split(",");
        imageList = new ArrayList<>();
        imageList.addAll(Arrays.asList(vehicles));
        adapter = new RecyclerAdapter(imageList, new RecyclerAdapter.OnclickRecycler() {
            @Override
            public void OnclickItemRecycler(String vehicleImage) {
                Glide.with(getContext()).load(vehicleImage).into(m_image);
            }
        });
        return view;
    }

}
