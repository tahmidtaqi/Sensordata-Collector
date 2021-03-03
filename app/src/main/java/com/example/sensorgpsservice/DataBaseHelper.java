


package com.example.sensorgpsservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.gms.location.LocationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String SENSORGPS_TABLE = "SENSORGPS_TABLE";
    public static final String SENSORGPS_LATITUDE = "SENSORGPS_LATITUDE";
    public static final String SENSORGPS_LONGITUDE = "SENSORGPS_LONGITUDE";
    public static final String GPSID = "GPSID";
    public static final String USERID = "userID";



    public static final String SENSORACCELEROMETER_TABLE = "SENSORACCELEROMETER_TABLE";
    public static final String ACCELEROMETER_X = "ACCELEROMETER_X";
    public static final String ACCELEROMETER_Y = "ACCELEROMETER_Y";
    public static final String ACCELEROMETERID = "ACCELEROMETERID";
    public static final String ACCELEROMETER_Z = "ACCELEROMETER_Z";

    public static final String GYROSCOPE_X = "GYROSCOPE_X";
    public static final String GYROSCOPE_Y = "GYROSCOPE_Y";

    public static final String GYROSCOPE_Z = "GYROSCOPE_Z";



    public static final String SENSORORIENTATION_TABLE = "SENSORORIENTATION_TABLE";
    public static final String ORIENTATIONID = "ORIENTATIONID";
    public static final String ORIENTATION_AZIMUTH = "ORIENTATION_AZIMUTH";
    public static final String ORIENTATION_PITCH = "ORIENTATION_PITCH";
    public static final String ORIENTATION_ROLL = "ORIENTATION_ROLL";

    public static final String SENSORLIGHT_TABLE = "SENSORLIGHT_TABLE";
    public static final String LIGHTID = "LIGHTID";
    public static final String LIGHT_V = "LIGHT_V";


    public static final String BATTERY_LEVEL = "BATTERY_LEVEL";


    public static final String GRAV_X = "GRAV_X";
    public static final String GRAV_Y = "GRAV_Y";
    public static final String GRAV_Z = "GRAV_Z";

    public static final String MAG_X = "MAG_X";
    public static final String MAG_Y = "MAG_Y";
    public static final String MAG_Z = "MAG_Z";
    public static final String DATE_TIME = "DATE_TIME";
    public static final String  SENSORDATA=" SENSORDATA";
    public DataBaseHelper(@Nullable Context context) {
        super(context, "sensor.db", null, 3);
    }


    //function for creating the table  in sqlite
    @Override
    public void onCreate(SQLiteDatabase db) {


       String createTableSensor="CREATE TABLE " + SENSORDATA + " (" + SENSORGPS_LATITUDE + " REAL , "
               + SENSORGPS_LONGITUDE + " REAL," + ACCELEROMETER_X + " REAL," + ACCELEROMETER_Y + " REAL ,"
               + ACCELEROMETER_Z + " REAL," + ORIENTATION_AZIMUTH + " REAL," + ORIENTATION_PITCH + " REAL,"
               + ORIENTATION_ROLL + " REAL," + LIGHT_V + " REAL," + GYROSCOPE_X + " REAL,"
               + GYROSCOPE_Y + " REAL," + GYROSCOPE_Z + " REAL," + GRAV_X + " REAL," +
               GRAV_Y + " REAL," + GRAV_Z + " REAL,"  +
               MAG_X + " REAL," + MAG_Y + " REAL," + MAG_Z + " REAL," + BATTERY_LEVEL + " VARCHAR(20),"
               + DATE_TIME + " REAL)" ;

        //Log.d("DATABASE",createTableSensor);

        db.execSQL(createTableSensor);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    //function for inserting value in the sqlite database

    public boolean addsensordata() {
        SQLiteDatabase dbAccelerometer = this.getWritableDatabase();

        ContentValues contentValuessesnordata = new ContentValues();

        contentValuessesnordata.put(SENSORGPS_LATITUDE, Constants.latitude);
        contentValuessesnordata.put(SENSORGPS_LONGITUDE, Constants.longitude);

        contentValuessesnordata.put(ACCELEROMETER_X, Constants.acc_x);
        contentValuessesnordata.put(ACCELEROMETER_Y, Constants.acc_y);
        contentValuessesnordata.put(ACCELEROMETER_Z, Constants.acc_z);



        contentValuessesnordata.put(ORIENTATION_AZIMUTH, Constants.azimuth);
        contentValuessesnordata.put(ORIENTATION_PITCH, Constants.pitch);
        contentValuessesnordata.put(ORIENTATION_ROLL, Constants.roll);
        contentValuessesnordata.put(LIGHT_V, Constants.light_v);
        contentValuessesnordata.put(GYROSCOPE_X, Constants.gyro_x);
        contentValuessesnordata.put(GYROSCOPE_Y, Constants.gyro_y);
        contentValuessesnordata.put(GYROSCOPE_Z, Constants.gyro_z);
        contentValuessesnordata.put(BATTERY_LEVEL,Constants.battery_level);



        contentValuessesnordata.put(MAG_X, Constants.mag_x);
        contentValuessesnordata.put(MAG_Y, Constants.mag_y);
        contentValuessesnordata.put(MAG_Z, Constants.mag_z);

        contentValuessesnordata.put(GRAV_X, Constants.grav_x);
        contentValuessesnordata.put(GRAV_Y, Constants.grav_y);
       contentValuessesnordata.put(GRAV_Z, Constants.grav_z);

        contentValuessesnordata.put(DATE_TIME, java.text.DateFormat.getDateTimeInstance().format(new Date()));


        long insertsensordata = dbAccelerometer.insert( SENSORDATA, null, contentValuessesnordata);

        if (insertsensordata == -1) {
            return false;
        } else {
            return true;
        }


    }

    //function for pushing the data from sqlite to firebase
    public ArrayList getsensordata() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> array_list = new ArrayList<String>();
        Cursor cursor = db.rawQuery( "select * from "+SENSORDATA, null );

        if (cursor.moveToFirst()) {
            do {
                String gyroX,gyroY,gyroZ,firegravX,firegravY,firegravZ,firemagX,firemagY,firemagZ,goingbattery, goingLatitude,goingLongitude,goingdatetime,goingX,goingY,goingZ,goingAzimuth,goingPitch,goingRoll,goinglight;
                goingLatitude=cursor.getString(cursor.getColumnIndex("SENSORGPS_LATITUDE"));
                goingLongitude=cursor.getString(cursor.getColumnIndex("SENSORGPS_LONGITUDE"));

                goingX=cursor.getString(cursor.getColumnIndex("ACCELEROMETER_X"));
                goingY=cursor.getString(cursor.getColumnIndex("ACCELEROMETER_Y"));
                goingZ=cursor.getString(cursor.getColumnIndex("ACCELEROMETER_Z"));



                goingAzimuth=cursor.getString(cursor.getColumnIndex("ORIENTATION_AZIMUTH"));
                goingPitch=cursor.getString(cursor.getColumnIndex("ORIENTATION_PITCH"));
                goingRoll=cursor.getString(cursor.getColumnIndex("ORIENTATION_ROLL"));

                goinglight=cursor.getString(cursor.getColumnIndex("LIGHT_V"));

                gyroX=cursor.getString(cursor.getColumnIndex("GYROSCOPE_X"));
                gyroY=cursor.getString(cursor.getColumnIndex("GYROSCOPE_Y"));
                gyroZ=cursor.getString(cursor.getColumnIndex("GYROSCOPE_Z"));


                goingbattery=cursor.getString(cursor.getColumnIndex("BATTERY_LEVEL"));


                firemagX=cursor.getString(cursor.getColumnIndex("MAG_X"));
                firemagY=cursor.getString(cursor.getColumnIndex("MAG_Y"));
                firemagZ=cursor.getString(cursor.getColumnIndex("MAG_Z"));
                firegravX=cursor.getString(cursor.getColumnIndex("GRAV_X"));
               firegravY=cursor.getString(cursor.getColumnIndex("GRAV_Y"));
                firegravZ=cursor.getString(cursor.getColumnIndex("GRAV_Z"));
                goingdatetime=cursor.getString(cursor.getColumnIndex("DATE_TIME"));


                Log.d("FIREBASE",goingLatitude+" "+goingLongitude);
                // Write a message to the database
                String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
                Log.d("Userid:" ,uid);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("SENSORDATA/USERS/" +uid+ "/SENSORDATA");

                //myRef.setValue(going);

                HashMap<String, Object> result = new HashMap<>();

                result.put("SENSORGPS_LATITUDE", goingLatitude);
                result.put("SENSORGPS_LONGITUDE", goingLongitude);
                result.put("ACCELEROMETER_X", goingX);
                result.put("ACCELEROMETER_Y", goingY);
                result.put("ACCELEROMETER_Z", goingZ);

                result.put("ORIENTATION_AZIMUTH", goingAzimuth);
                result.put("ORIENTATION_PITCH", goingPitch);
                result.put("ORIENTATION_ROLL", goingRoll);
                result.put("Light_v", goinglight);


                result.put("GYROSCOPE_X", gyroX);
                result.put("GYROSCOPE_Y", gyroY);
                result.put("GYROSCOPE_Z", gyroZ);

               result.put("BATTERY_LEVEL", goingbattery);
                result.put("GRAV_X", firegravX);
                result.put("GRAV_Y", firegravY);
                result.put("GRAV_Z", firegravZ);
                result.put("MAG_X", firemagX);
                result.put("MAG_Y", firemagY);
                result.put("MAG_Z", firemagZ);

                result.put("Date_time", goingdatetime);
                myRef.push().setValue(result);




            } while (cursor.moveToNext());
            deleteAllsensordata();
        }
        return null;
    }

    //function for deleting the data from sqlite
    public void deleteAllsensordata(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ SENSORDATA );
        Log.d("SENSORTABLE","deleted");
        db.close();

    }

}

