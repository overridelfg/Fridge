package com.example.kirillrychkov.project;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private final static String FILE_NAME2 = "content2.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onProducts(View view) {
        Intent intent = new Intent(MainActivity.this,Buffer.class);
        startActivity(intent);
    }
    public void onUserSetting(View view){
        Intent intent2 = new Intent(MainActivity.this,UserSetting.class);
        startActivity(intent2);
    }
    public void onSetting(View view){
        Intent intent3 = new Intent(MainActivity.this,Setting.class);
        startActivity(intent3);
    }

}
