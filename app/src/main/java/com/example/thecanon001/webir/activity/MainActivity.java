package com.example.thecanon001.webir.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.thecanon001.webir.R;
import com.example.thecanon001.webir.adapter.CarViewAdapter;
import com.example.thecanon001.webir.entity.Filter;
import com.example.thecanon001.webir.model.ContextProvider;
import com.example.thecanon001.webir.model.ServiceFactoryProvider;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private int count = 0;
    private long startMillis=0;
    private boolean stub;
    private String url;
    private CarViewAdapter cardViewAdapter;

    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpView();
        loadPreferences();
        ContextProvider.getInstance().setContext(getApplicationContext());
        init();
    }

    private void setUpView() {
        setSupportActionBar(toolbar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        cardViewAdapter = new CarViewAdapter(new ArrayList<>(), this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cardViewAdapter);
    }

    private void loadPreferences() {
        SharedPreferences preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        stub = preferences.getBoolean("stub", false);
        url = preferences.getString("url","localhost");
    }

    private void init() {
        ServiceFactoryProvider.getServiceFactory(stub).getCarService().getCarList(getApplication(), cardViewAdapter);
    }

    private void init_filter(Filter filter) {
        ServiceFactoryProvider.getServiceFactory(stub).getCarService().getCarList(getApplication(), filter, cardViewAdapter);
    }

    @OnClick(R.id.fab)
    public void onTouchFab(){
        showDialog();
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_filter,null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        EditText editText_brand = view.findViewById(R.id.search_edit);
        EditText editText_price = view.findViewById(R.id.search_price);
        CheckBox check_usd = view.findViewById(R.id.usd);
        CheckBox check_$ = view.findViewById(R.id.pesos);
        CheckBox check_used = view.findViewById(R.id.used);
        CheckBox check_new = view.findViewById(R.id.nes);


        Button btn_ok = view.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(v-> {
            Filter filter = new Filter();
            filter.setBrand(editText_brand.getText().toString());
            if(editText_price != null && editText_price.getText() != null && !editText_price.getText().toString().isEmpty())
                filter.setPrice(Double.parseDouble(editText_price.getText().toString()));
            else
                filter.setPrice(null);
            filter.setUsd(check_usd.isChecked());
            filter.set$(check_$.isChecked());
            filter.setUsed(check_used.isChecked());
            filter.set_new(check_new.isChecked());
            init_filter(filter);
            dialog.cancel();
        });

        Button btn_cancel = view.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(v -> dialog.cancel());
    }


    private void showDialogConfig() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_config,null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        CheckBox checkBox = view.findViewById(R.id.checkbox);
        EditText editText = view.findViewById(R.id.search_edit);

        if(stub){
            checkBox.setChecked(true);
        }

        editText.setText(url);

        Button btn_ok = view.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(v->{
           savePreferences(checkBox.isChecked(), editText.getText().toString());
           init();
           dialog.cancel();
        });

        Button btn_cancel = view.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(v -> dialog.cancel());
    }

    private void savePreferences(boolean stub, String url) {
        SharedPreferences preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("stub",stub);
        editor.putString("url",url);
        this.stub = stub;
        this.url = url;
        editor.apply();
    }

    @OnClick(R.id.toolbar)
    public void onTouchToolbar(){
        long time= System.currentTimeMillis();
        //if it is the first time, or if it has been more than 3 seconds since the first tap ( so it is like a new try), we reset everything
        if (startMillis==0 || (time-startMillis> 3000) ) {
            startMillis=time;
            count=1;
        }
        //it is not the first, and it has been  less than 3 seconds since the first
        else{ //  time-startMillis< 3000
            count++;
        }
        if(count == 5){
            showDialogConfig();
        }
    }


}

