package com.example.thecanon001.webir.model;

import android.app.Application;

public class UserServiceStub implements UserService {
    @Override
    public boolean loginregister(Application application, String email, String password, Boolean isLoggin) {
        return true;
    }
}
