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
import com.example.thecanon001.webir.entity.Car;
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
    private ArrayList<Car> carList = new ArrayList<Car>();
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
        cardViewAdapter = new CarViewAdapter(carList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cardViewAdapter);
    }

    private void loadPreferences() {
        SharedPreferences preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        stub = preferences.getBoolean("stub", false);
        url = preferences.getString("url","localhost");
    }

    private void init() {
        carList = ServiceFactoryProvider.getServiceFactory(stub).getCarService().getCarList(getApplication());
        cardViewAdapter.updateListView(carList);
    }

    private void init_filter(String filter) {
        carList = ServiceFactoryProvider.getServiceFactory(stub).getCarService().getCarList(getApplication(), filter);
        cardViewAdapter.updateListView(carList);
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

        EditText editText = view.findViewById(R.id.search_edit);

        Button btn_ok = view.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(v-> {
            init_filter(editText.getText().toString());
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

