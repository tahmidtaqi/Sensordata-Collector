package com.example.sensorgpsservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserinfoActivity extends AppCompatActivity {

    private Button submit;
    //variables for radio button
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    private RadioGroup radioGroup2;
    private RadioButton radioButton2;

    private RadioGroup radioGroup3;
    private RadioButton radioButton3;

    private  String selecteduni;
    private  String selectedgender;
    private  String selectedage;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("UID ",uid);
        databaseReference= FirebaseDatabase.getInstance().getReference("SENSORDATA/USERS/" +uid+ "/USERINFO");
        submit=findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitdata();
            }
        });
    }

    //fetching the userinfo and submitting it to firebase
    public void  submitdata(){
        //code for fetching selected values

        radioGroup = (RadioGroup) findViewById(R.id.uni_radio_btn);
        // get selected radio button from radioGroup
        int selectedId = radioGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radioButton = (RadioButton) findViewById(selectedId);

        selecteduni=radioButton.getText().toString();
        //Log.d("SHOWUNI", selecteduni);

        //code for gender selection value fetch
        radioGroup2 = (RadioGroup) findViewById(R.id.gender_radio_btn);
        int selectedId2 = radioGroup2.getCheckedRadioButtonId();
        radioButton2 = (RadioButton) findViewById(selectedId2);
        selectedgender=radioButton2.getText().toString();
        //Log.d("SHOWgender", selectedgender);
        //code for age selection value fetch
        radioGroup3 = (RadioGroup) findViewById(R.id.age_range_radio);
        int selectedId3 = radioGroup3.getCheckedRadioButtonId();
        radioButton3 = (RadioButton) findViewById(selectedId3);
        selectedage=radioButton3.getText().toString();
        Log.d("SHOWage", selectedage);

        String key =databaseReference.push().getKey();

        Users users=new Users(selecteduni,selectedgender,selectedage);

       // databaseReference.child(key).setValue(users);
        databaseReference.setValue(users);



        Intent intent =new Intent(UserinfoActivity.this,MainActivity2.class);
        startActivity(intent);
        finish();


    }

}

