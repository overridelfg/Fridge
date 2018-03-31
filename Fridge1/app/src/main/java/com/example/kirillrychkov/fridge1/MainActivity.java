package com.example.kirillrychkov.fridge1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void hello(View view)
    {
        Toast.makeText(this, "Пока что не работает" ,Toast.LENGTH_SHORT).show();
    }
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this,bluda.class);
        startActivity(intent);
    }
}

