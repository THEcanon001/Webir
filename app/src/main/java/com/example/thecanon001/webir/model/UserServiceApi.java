package com.example.thecanon001.webir.model;

import android.app.Application;
import android.util.Log;

import com.example.thecanon001.webir.client.WebServiceClient;
import com.example.thecanon001.webir.injection.BaseAplicattion;


import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserServiceApi implements UserService {
    private String token;

    @Inject
    WebServiceClient webServiceClient;

    @Override
    public String getSesion(Application application, String email, String password) {
        try {
            setUpDagger(application);
            Call<String> call = webServiceClient.getSesion(email, password);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    token = (String) response.body();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    token = "dummy_token_api";
                    Log.d("Error", t.getMessage());
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    private void setUpDagger(Application application) {
        ((BaseAplicattion)application).getRetrofitComponent().inject(this);
    }
}
