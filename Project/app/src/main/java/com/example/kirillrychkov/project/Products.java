package com.example.kirillrychkov.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Products extends AppCompatActivity {
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;
    private CheckBox checkBox7;
    private CheckBox checkBox8;
    private CheckBox checkBox9;
    private CheckBox checkBox10;
    private CheckBox checkBox11;
    private CheckBox checkBox12;
    private CheckBox checkBox13;
    private CheckBox checkBox14;
    private CheckBox checkBox15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersetting);


        String str=openText();
        if(str=="Вегетарианец"){
            Intent intentveg=new Intent(Products.this,Vegetarian.class);
            startActivity(intentveg);
        }



        checkBox1 = findViewById(R.id.Product1);
        checkBox2 = findViewById(R.id.Product2);
        checkBox3 = findViewById(R.id.Product3);
        checkBox4 = findViewById(R.id.Product4);
        checkBox5 = findViewById(R.id.Product5);
        checkBox6 = findViewById(R.id.Product6);
        checkBox7 = findViewById(R.id.Product7);
        checkBox8 = findViewById(R.id.Product8);
        checkBox9 = findViewById(R.id.Product9);
        checkBox10 = findViewById(R.id.Product10);
        checkBox11 = findViewById(R.id.Product11);
        checkBox12 = findViewById(R.id.Product12);
        checkBox13 = findViewById(R.id.Product13);
        checkBox14 = findViewById(R.id.Product14);
        checkBox15 = findViewById(R.id.Product15);

    }
    private final static String FILE_NAME = "content.txt";


    public String openText(){

        FileInputStream fin = null;
        try {
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            return text;
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            return FILE_NAME;
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


}