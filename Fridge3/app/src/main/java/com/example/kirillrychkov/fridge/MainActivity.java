package com.example.kirillrychkov.fridge;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onProducts(View view) {
        Intent intent = new Intent(MainActivity.this,Products.class);
        startActivity(intent);
    }
    public void onUserSetting(View view){
        Intent intent = new Intent(MainActivity.this,UserSetting.class);
        startActivity(intent);
    }
    public void onSetting(View view){
        Intent intent = new Intent(MainActivity.this,Setting.class);
        startActivity(intent);
    }


}