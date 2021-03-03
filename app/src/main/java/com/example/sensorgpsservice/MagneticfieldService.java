package com.example.sensorgpsservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MagneticfieldService  extends Service implements SensorEventListener {


    private SensorManager mSensorManager;
    private Sensor mSensorMagnetometer;
    @Nullable

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

            mSensorManager.registerListener(this, mSensorMagnetometer, 600000000,600000000);

        return Service.START_STICKY;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            Constants.mag_x = event.values[0];
            Constants.mag_y = event.values[1];
            Constants.mag_z = event.values[2];

            Log.d("MagneticfieldData",Constants.mag_x +" "+ Constants.mag_y +" "+ Constants.mag_z);




        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
