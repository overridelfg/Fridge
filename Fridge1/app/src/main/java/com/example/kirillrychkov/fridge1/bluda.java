
package com.example.kirillrychkov.fridge1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class bluda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluda);
    }
    public void onvegan(View view) {
        Intent intent1 = new Intent(bluda.this,vegan.class);
        startActivity(intent1);
    }
    public void onmiasoed(View view)
    {
        Intent intent2=new Intent(bluda.this,miasoed.class);
        startActivity(intent2);
    }
    public void onPostnayaeda(View view)
    {
        Intent intent3=new Intent(bluda.this,Postnayaeda.class);
        startActivity(intent3);
    }
}

