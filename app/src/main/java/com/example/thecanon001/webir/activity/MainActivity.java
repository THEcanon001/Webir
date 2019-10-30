package com.example.thecanon001.webir.activity;

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
import android.widget.Toast;

import com.example.thecanon001.webir.R;
import com.example.thecanon001.webir.adapter.CarViewAdapter;
import com.example.thecanon001.webir.entity.Filter;
import com.example.thecanon001.webir.model.Config;
import com.example.thecanon001.webir.model.ConfigProvider;
import com.example.thecanon001.webir.model.ContextProvider;
import com.example.thecanon001.webir.model.ServiceFactoryProvider;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
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
        ContextProvider.getInstance().setContext(getApplicationContext());
        showDialog();
    }

    private void setUpView() {
        setSupportActionBar(toolbar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        cardViewAdapter = new CarViewAdapter(new ArrayList<>(), this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cardViewAdapter);
    }

    private void init(ArrayList<Filter> filterList) {
        if(filterList.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Debe seleccionar un filtro de busqueda", Toast.LENGTH_SHORT).show();
//            ServiceFactoryProvider.getServiceFactory(stub).getCarService().getCarList(getApplication(), cardViewAdapter);
        } else if(filterList.size() == 1)
            ServiceFactoryProvider.getServiceFactory(ConfigProvider.getInstance().getStub()).getCarService().getCarList(getApplication(), filterList.get(0), cardViewAdapter);
        else
            ServiceFactoryProvider.getServiceFactory(ConfigProvider.getInstance().getStub()).getCarService().getCarList(getApplication(), filterList, cardViewAdapter);
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
        dialog.setCancelable(false);
        dialog.show();

        EditText editText_brand = view.findViewById(R.id.search_edit);
        EditText editText_price = view.findViewById(R.id.search_price);
        CheckBox check_usd = view.findViewById(R.id.usd);
        CheckBox check_$ = view.findViewById(R.id.pesos);
        CheckBox check_used = view.findViewById(R.id.used);
        CheckBox check_new = view.findViewById(R.id.nes);


        Button btn_ok = view.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(v-> {
            ArrayList<Filter> filterList = new ArrayList<>();
            Filter filter = new Filter();
            if(editText_brand.getText() != null && !editText_brand.getText().toString().isEmpty()){
                filter.setType("title");
                filter.setValue(editText_brand.getText().toString());
                filterList.add(filter);
                filter = new Filter();
            }
            if(editText_price.getText() != null && !editText_price.getText().toString().isEmpty()){
                filter.setType("price");
                filter.setValue(editText_price.getText().toString());
                filterList.add(filter);
                filter = new Filter();
            }

            if(check_usd.isChecked()){
                filter.setType("currency");
                filter.setValue("USD");
                filterList.add(filter);
                filter = new Filter();
            }

            if(check_$.isChecked()){
                filter.setType("currency");
                filter.setValue("UYU");
                filterList.add(filter);
                filter = new Filter();
            }

            if(check_used.isChecked()){
                filter.setType("condition");
                filter.setValue("used");
                filterList.add(filter);
                filter = new Filter();
            }

            if(check_new.isChecked()){
                filter.setType("condition");
                filter.setValue("new");
                filterList.add(filter);

            }

            init(filterList);
            if(!filterList.isEmpty())
                dialog.cancel();
        });

        Button btn_cancel = view.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(v -> dialog.cancel());
        Toast.makeText(getApplicationContext(),ConfigProvider.getInstance().getToken(),Toast.LENGTH_SHORT).show();
    }
}

