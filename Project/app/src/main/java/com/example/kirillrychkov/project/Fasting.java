package com.example.kirillrychkov.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Fasting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fasting);
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Fasting.this,MainActivity.class);
        startActivity(intent);


    }
}
