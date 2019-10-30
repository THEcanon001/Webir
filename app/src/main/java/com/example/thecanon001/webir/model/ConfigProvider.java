package com.example.thecanon001.webir.model;

public class ConfigProvider {
    private static final ConfigProvider ourInstance = new ConfigProvider();

    public static ConfigProvider getInstance() {
        return ourInstance;
    }

    private ConfigProvider() {
    }

    private String url_api;
    private String url_Sesion;
    private Boolean stub;
    private String token;
    private int url_type;

    public void setUrl_api(String url_api) {
        this.url_api = url_api;
    }

    public void setUrl_Sesion(String url_Sesion) {
        this.url_Sesion = url_Sesion;
    }

    public Boolean getStub() {
        return stub;
    }

    public void setStub(Boolean stub) {
        this.stub = stub;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUrl_type() {
        return url_type;
    }

    public void setUrl_type(int url_type) {
        this.url_type = url_type;
    }

    public String getUrl(int type){
        if(type == 1){
            return url_api;
        } else
            return url_Sesion;
    }
}
