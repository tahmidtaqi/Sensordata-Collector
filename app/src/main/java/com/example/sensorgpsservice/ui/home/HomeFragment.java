package com.example.sensorgpsservice.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sensorgpsservice.R;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TextView thankyoutext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        RadioGroup radioGroup = (RadioGroup) root.findViewById(R.id.radioGroup);
       // final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               // textView.setText(s);
            }
        });
       // thankyoutext=root.findViewById(R.id.thankyou);
       // StringBuilder stringBuilder =new StringBuilder();
       // stringBuilder.append("Thank You");


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                switch(checkedId) {
                    case R.id.option1:
                        // switch to fragment 1

                        {
                            //thankyoutext.setText(stringBuilder);
                            //Log.d("Radio1","selected");
                            new SweetAlertDialog(
                                    getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Thank You!")
                                    .setContentText("")
                                    .show();
                            break;

                        }

                    case R.id.option2:
                        // Fragment 2
                        {
                            //thankyoutext.setText(stringBuilder);
                            //Log.d("Radio1","selected");
                            new SweetAlertDialog(
                                    getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Thank You!")
                                    .setContentText("")
                                    .show();
                            break;
                        }
                }
            }
        });
        return root;

    }



}