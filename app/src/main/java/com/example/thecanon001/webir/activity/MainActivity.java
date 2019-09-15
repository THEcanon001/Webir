package com.example.thecanon001.webir.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import com.example.thecanon001.webir.R;
import com.example.thecanon001.webir.adapter.CarViewAdapter;
import com.example.thecanon001.webir.entity.Car;
import com.example.thecanon001.webir.model.ServiceFactoryApi;
import com.example.thecanon001.webir.model.ServiceFactoryStub;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private int count = 0;
    private long startMillis=0;
    private boolean stub = false;
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
        init();
    }

    private void setUpView() {
        setSupportActionBar(toolbar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        cardViewAdapter = new CarViewAdapter(carList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cardViewAdapter);
    }

    private void init() {
        if(stub){
            carList = new ServiceFactoryStub().getCarService().getCarList();
        }
        else{
            carList = new ServiceFactoryApi().getCarService().getCarList();
        }
        cardViewAdapter.updateListView(carList);
    }

    @OnClick(R.id.fab)
    public void onTouchFab(){
        Toast.makeText(getApplicationContext(), "Jill manda", Snackbar.LENGTH_LONG).show();
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
        if(count == 10){
            stub = true;
            Toast.makeText(getApplicationContext(),"Se activa Stub", Toast.LENGTH_SHORT).show();
        }

        if(count == 20){
            count = 0;
            stub = false;
            Toast.makeText(getApplicationContext(),"Se desactiva Stub", Toast.LENGTH_SHORT).show();
        }
        init();
    }
}
