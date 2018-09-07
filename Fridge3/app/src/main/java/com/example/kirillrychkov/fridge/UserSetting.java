package com.example.kirillrychkov.fridge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserSetting extends AppCompatActivity {
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersetting);
        checkBox1 =  findViewById(R.id.checkBox1);
        checkBox2 =  findViewById(R.id.checkBox2);
        checkBox3 =  findViewById(R.id.checkBox3);
        checkBox4 =  findViewById(R.id.checkBox4);
        checkBox5 =  findViewById(R.id.checkBox5);

    }

    public void onMain(View view) {

        if (checkBox1.isChecked() && !checkBox3.isChecked() && !checkBox2.isChecked() &&
                !checkBox4.isChecked() && !checkBox5.isChecked()){
            Intent intent = new Intent(UserSetting.this,MainActivity.class);
            startActivity(intent);
        }
        if (!checkBox1.isChecked() && !checkBox3.isChecked() && checkBox2.isChecked() &&
                !checkBox4.isChecked() && !checkBox5.isChecked()){
            Intent intent = new Intent(UserSetting.this,MainActivity.class);
            startActivity(intent);
        }
        if (!checkBox1.isChecked() && checkBox3.isChecked() && !checkBox2.isChecked() &&
                !checkBox4.isChecked() && !checkBox5.isChecked()){
            Intent intent = new Intent(UserSetting.this,MainActivity.class);
            startActivity(intent);
        }
        if (!checkBox1.isChecked() && !checkBox3.isChecked() && !checkBox2.isChecked() &&
                checkBox4.isChecked() && !checkBox5.isChecked()){
            Intent intent = new Intent(UserSetting.this,MainActivity.class);
            startActivity(intent);
        }
        if (!checkBox1.isChecked() && !checkBox3.isChecked() && !checkBox2.isChecked() &&
                !checkBox4.isChecked() && checkBox5.isChecked()){
            Intent intent = new Intent(UserSetting.this,MainActivity.class);
            startActivity(intent);
        }



    }
}