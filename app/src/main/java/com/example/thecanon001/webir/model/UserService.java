package com.example.thecanon001.webir.model;

import android.app.Application;

public interface UserService {

    String getSesion(Application application, String email, String password);
}
