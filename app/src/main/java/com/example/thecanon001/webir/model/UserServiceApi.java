package com.example.thecanon001.webir.model;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.thecanon001.webir.client.WebServiceClient;
import com.example.thecanon001.webir.entity.User;
import com.example.thecanon001.webir.injection.BaseAplicattion;


import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserServiceApi implements UserService {

    @Inject
    WebServiceClient webServiceClient;

    @Override
    public boolean loginregister(Application application, String email, String password, Boolean isLoggin) {
        boolean ret = false;
        try {
            ConfigProvider.getInstance().setUrl_type(2);
            setUpDagger(application);
            User user = new User(email, password);
            if(isLoggin) {
                Call<String> call = webServiceClient.login(user);
                try {
                    Response<String> response = call.execute();
                    String token = response.body();
                    ConfigProvider.getInstance().setToken(token);
                    ret = true;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else{
                try {
                    Call<User> call = webServiceClient.register(user);
                    Response<User> response = call.execute();
                    if(response.isSuccessful())
                        ret = true;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            ConfigProvider.getInstance().setUrl_type(1);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }

    private void setUpDagger(Application application) {
        ((BaseAplicattion)application).getRetrofitComponent().inject(this);
    }
}
