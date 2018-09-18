package com.example.kirillrychkov.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Meateater extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meateater);
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Meateater.this,MainActivity.class);
        startActivity(intent);


    }
}
