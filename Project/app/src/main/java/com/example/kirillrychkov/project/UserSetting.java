package com.example.kirillrychkov.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class UserSetting extends AppCompatActivity {
    public static String user;
    private final static String FILE_NAME="test.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersetting);

    }

    public void onMain(View view) {
        RadioGroup radioGroup = findViewById(R.id.radiogroup);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = radioGroup.findViewById(radioButtonID);
        user = radioButton.getText().toString();
        Intent intent = new Intent(UserSetting.this,Setting.class);
        startActivity(intent);
    }

}
