package com.example.kirillrychkov.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserSetting extends AppCompatActivity {
    private final static String FILE_NAME = "content.txt";
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersetting);
        checkBox1 =  findViewById(R.id.checkBox1);
        checkBox2 =  findViewById(R.id.checkBox2);
        checkBox3 =  findViewById(R.id.checkBox3);
        checkBox4 =  findViewById(R.id.checkBox4);
        checkBox5 =  findViewById(R.id.checkBox5);

    }

    public void onMain(View view) {
        String obmen = "";

        if (checkBox1.isChecked() && !checkBox3.isChecked() && !checkBox2.isChecked() &&
                !checkBox4.isChecked() && !checkBox5.isChecked()){
            obmen = "Veg";
            saveTextBuffer(obmen);
            Intent intent = new Intent(UserSetting.this,MainActivity.class);
            startActivity(intent);
        }
        if (!checkBox1.isChecked() && !checkBox3.isChecked() && checkBox2.isChecked() &&
                !checkBox4.isChecked() && !checkBox5.isChecked()){
            obmen += "Mas";
            saveTextBuffer(obmen);
            Intent intent = new Intent(UserSetting.this,Setting.class);
            startActivity(intent);
        }
        if (!checkBox1.isChecked() && checkBox3.isChecked() && !checkBox2.isChecked() &&
                !checkBox4.isChecked() && !checkBox5.isChecked()){
            obmen += "Pos";
            saveTextBuffer(obmen);
            Intent intent = new Intent(UserSetting.this,Setting.class);
            startActivity(intent);
        }
        if (!checkBox1.isChecked() && !checkBox3.isChecked() && !checkBox2.isChecked() &&
                checkBox4.isChecked() && !checkBox5.isChecked()){
            obmen += "Dit";
            saveTextBuffer(obmen);
            Intent intent = new Intent(UserSetting.this,Setting.class);
            startActivity(intent);
        }
        if (!checkBox1.isChecked() && !checkBox3.isChecked() && !checkBox2.isChecked() &&
                !checkBox4.isChecked() && checkBox5.isChecked()){
            obmen+= "Ber";
            saveTextBuffer(obmen);
            Intent intent = new Intent(UserSetting.this,Setting.class);
            startActivity(intent);

        }



    }
    public void saveTextBuffer(String obmen){
        java.io.FileOutputStream fos = null;
        try {
            String text=obmen;
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
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