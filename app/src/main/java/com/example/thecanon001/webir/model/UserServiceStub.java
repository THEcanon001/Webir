package com.example.thecanon001.webir.model;

import android.app.Application;

public class UserServiceStub implements UserService {
    @Override
    public String getSesion(Application application, String email, String password) {
        return "dummy_token_stub";
    }
}
