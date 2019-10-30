package com.example.thecanon001.webir.model;
public final class Config {

    private Config(){ }

    private final static String URL_SESION = "url_sesion";
    private final static String URL_api = "url_api";
    private final static String DEFAULT_URL_SESION = "http://localhost:63274/api/";
    private final static String DEFAULT_URL_API = "http://localhost:8080/webir/service/";
    private final static String STUB = "stub";
    private final static Boolean DEFAULT_STUB = false;
    private final static String TOKEN = "token";
    private final static String DEFAULT_TOKEN = "dummy_token";
    private final static String CONFIG = "config";

    public static String getUrlSesion() {
        return URL_SESION;
    }

    public static String getURL_api() {
        return URL_api;
    }

    public static String getDefaultUrlSesion() {
        return DEFAULT_URL_SESION;
    }

    public static String getDefaultUrlApi() {
        return DEFAULT_URL_API;
    }

    public static String getSTUB() {
        return STUB;
    }

    public static Boolean getDefaultStub() {
        return DEFAULT_STUB;
    }

    public static String getTOKEN() {
        return TOKEN;
    }

    public static String getDefaultToken() {
        return DEFAULT_TOKEN;
    }

    public static String getCONFIG() {
        return CONFIG;
    }
}
