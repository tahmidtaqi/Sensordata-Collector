package com.example.sensorgpsservice;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class Sensordatacollector extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }


}
