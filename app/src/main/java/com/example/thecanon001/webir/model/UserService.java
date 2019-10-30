package com.example.thecanon001.webir.model;

import android.app.Application;

public interface UserService {

    boolean loginregister(Application application, String email, String password, Boolean isLoggin);
}
