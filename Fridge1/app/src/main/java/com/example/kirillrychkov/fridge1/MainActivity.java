package com.example.kirillrychkov.fridge1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
    public void onTest(View view){
        Intent intent = new Intent(MainActivity.this,test.class);
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

