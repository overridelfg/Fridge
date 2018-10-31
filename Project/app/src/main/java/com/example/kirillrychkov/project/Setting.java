package com.example.kirillrychkov.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Setting extends AppCompatActivity {
    private final static String FILE_NAMEBUFFER = "content.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkFirstStart();
        setContentView(R.layout.activity_setting);
        TextView writeinformation = findViewById(R.id.writeinformation);
        writeinformation.setText(openText());
    }

    public void onMain2(View view) {
        Intent intent = new Intent(Setting.this, MainActivity.class);
        startActivity(intent);
    }

    public void onMain3(View view) {
        Intent intent = new Intent(Setting.this, UserSetting.class);
        startActivity(intent);
    }
    public void saveTextBuffer(String obmen){
        FileOutputStream fos = null;
        try {

            String text=obmen;
            fos = openFileOutput(FILE_NAMEBUFFER, MODE_PRIVATE);
            fos.write(text.getBytes());
        }
        catch(IOException ex) {
            boolean kostil=true;
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){
                boolean kostil=true;
            }
        }
    }

    private void checkFirstStart() {

        SharedPreferences sp2 = getSharedPreferences("hasVisited2",
                Context.MODE_PRIVATE);
        boolean hasVisited = sp2.getBoolean("hasVisited2", false);

        if (!hasVisited) {
            saveTextBuffer("Ничего не выбрано");
            SharedPreferences.Editor e = sp2.edit();
            e.putBoolean("hasVisited2", true);
            e.commit();


        } else {

        }
    }
    public void onFavorite(View view){
        Intent intent = new Intent (Setting.this,Favorite.class);
        startActivity(intent);
    }
    public String openText(){

        FileInputStream fin = null;
        try {
            fin = openFileInput(FILE_NAMEBUFFER);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            return text;
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            return FILE_NAMEBUFFER;
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
