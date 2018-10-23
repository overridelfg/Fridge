package com.example.kirillrychkov.project;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class UserSetting extends AppCompatActivity {
    public static String user;
    private final static String FILE_NAME = "content.txt";
    private final static String FILE_NAMEBUFFER = "content.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersetting);

    }

    public void onMain(View view) {
        RadioGroup radioGroup = findViewById(R.id.radiogroup);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = radioGroup.findViewById(radioButtonID);
//        user = radioButton.getText().toString();
        if (radioGroup.getCheckedRadioButtonId() == -1)
        {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Выберите предпочтение!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else
        {
            saveTextBuffer(radioButton.getText().toString());
            onSetting();
        }

    }
    public void onSetting(){
        Intent intent = new Intent(UserSetting.this,Setting.class);
        startActivity(intent);

    }
    public void saveTextBuffer(String obmen){
        java.io.FileOutputStream fos = null;
        try {

            String text=obmen;
            fos = openFileOutput(FILE_NAMEBUFFER, MODE_PRIVATE);
            fos.write(text.getBytes());
        }

        catch(java.io.IOException ex) {
            boolean kostil=true;
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(java.io.IOException ex){
                boolean kostil=true;
            }
        }
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
