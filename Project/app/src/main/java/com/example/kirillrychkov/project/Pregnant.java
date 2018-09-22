package com.example.kirillrychkov.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Pregnant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregnant);
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Pregnant.this,MainActivity.class);
        startActivity(intent);


    }
}
