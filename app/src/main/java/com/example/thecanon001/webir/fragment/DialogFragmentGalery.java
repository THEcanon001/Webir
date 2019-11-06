package com.example.thecanon001.webir.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.thecanon001.webir.R;
import com.example.thecanon001.webir.adapter.RecyclerAdapter;
import com.example.thecanon001.webir.entity.Vehicle;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;


public class DialogFragmentGalery extends DialogFragment {
    private View view;
    private ImageView m_image;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dialog_fragment_galery, container, false);
        TextView m_text_brand = view.findViewById(R.id.text_brand);
        TextView m_text_condition = view.findViewById(R.id.text_condition);
        TextView m_text_price = view.findViewById(R.id.text_price);
        TextView m_text_currency = view.findViewById(R.id.text_currency);
//        TextView m_text_km = view.findViewById(R.id.text_km);
//        TextView m_text_year = view.findViewById(R.id.text_year);
        TextView m_title = view.findViewById(R.id.title);
        m_image = view.findViewById(R.id.image);
        android.support.v7.widget.RecyclerView m_galery = view.findViewById(R.id.galery);

        m_image.setOnClickListener(v -> {
            getDialog().dismiss();
        });

        assert getArguments() != null;
        Vehicle vehicle = (Vehicle) getArguments().getSerializable("vehicle");
        m_text_brand.setText(vehicle.getBrand());
        m_text_condition.setText(vehicle.getCondition());
        m_text_currency.setText(vehicle.getCurrency());
        m_title.setText(vehicle.getTitle());
        m_text_price.setText(String.valueOf(vehicle.getPrice()));
        m_galery.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        m_text_year.setText(String.valueOf(vehicle.getYear()));
//        m_text_km.setText(String.valueOf(vehicle.getKms()));
        String[] vehicles = vehicle.getPhotos().split(",");
        ArrayList<String> imageList = new ArrayList<>();
        imageList.addAll(Arrays.asList(vehicles));
        RecyclerAdapter adapter = new RecyclerAdapter(imageList, vehicleImage -> Picasso.with(view.getContext()).load(vehicleImage).placeholder(R.mipmap.ic_launcher)
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
