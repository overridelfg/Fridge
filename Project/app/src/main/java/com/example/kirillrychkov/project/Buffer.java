package com.example.kirillrychkov.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;

public class Buffer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffer);


        String str=openText();
        char[] chArray = str.toCharArray();
        for(int i=2;i<chArray.length;i++){
            if(chArray[i-2]=='V' && chArray[i-1]=='e' && chArray[i]=='g'){
                Intent intentveg=new Intent(Buffer.this,Vegetarian.class);
                startActivity(intentveg);
            }
            if(chArray[i-2]=='M' && chArray[i-1]=='a' && chArray[i]=='s'){
                Intent intentmas=new Intent(Buffer.this,Meateater.class);
                startActivity(intentmas);
            }
            if(chArray[i-2]=='P' && chArray[i-1]=='o' && chArray[i]=='s'){
                Intent intentpos=new Intent(Buffer.this,Fasting.class);
                startActivity(intentpos);
            }
            if(chArray[i-2]=='D' && chArray[i-1]=='i' && chArray[i]=='t'){
                Intent intentdit=new Intent(Buffer.this,Diet.class);
                startActivity(intentdit);
            }
            if(chArray[i-2]=='B' && chArray[i-1]=='e' && chArray[i]=='r'){
                Intent intentber=new Intent(Buffer.this,Beremm.class);
                startActivity(intentber);
            }
        }


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
