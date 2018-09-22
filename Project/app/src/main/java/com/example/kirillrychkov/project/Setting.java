package com.example.kirillrychkov.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;

public class Setting extends AppCompatActivity {
    private final static String FILE_NAME = "content.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        TextView writeinformation = findViewById(R.id.writeinformation);
        String test="";

        String str=openText();
        char[] chArray = str.toCharArray();
        for(int i=2;i<chArray.length;i++){
            if(chArray[i-2]=='V' && chArray[i-1]=='e' && chArray[i]=='g'){
                test="Вегетариангец";
                break;
            }
            if(chArray[i-2]=='M' && chArray[i-1]=='a' && chArray[i]=='s'){
                test="Мясоед";
                break;
            }
            if(chArray[i-2]=='P' && chArray[i-1]=='o' && chArray[i]=='s'){
                test="Постящийся";
                break;
            }
            if(chArray[i-2]=='D' && chArray[i-1]=='i' && chArray[i]=='t'){
                test="На диете";
                break;
            }
            if(chArray[i-2]=='B' && chArray[i-1]=='e' && chArray[i]=='r'){
                test="Еда для беременных";
                break;

            }
        }

        writeinformation.setText(test);
    }
    public void onMain2(View view){
        Intent intent = new Intent(Setting.this,MainActivity.class);
        startActivity(intent);
    }
    public void onMain3(View view){
        Intent intent = new Intent(Setting.this,UserSetting.class);
        startActivity(intent);
    }
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
