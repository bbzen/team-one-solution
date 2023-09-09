package ru.practikum.teamonesolution.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Util {
    public static Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        return gsonBuilder.create();
    }
}
