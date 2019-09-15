package com.example.thecanon001.webir.model;

import android.content.Context;

public class ContextProvider {
    private static final ContextProvider ourInstance = new ContextProvider();
    private Context context;

    public static ContextProvider getInstance() {
        return ourInstance;
    }

    private ContextProvider() {
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
