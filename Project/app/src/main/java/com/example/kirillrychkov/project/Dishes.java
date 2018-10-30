package com.example.kirillrychkov.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Dishes extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes);
        ArrayList<JSONObject> dishesList = new ArrayList<>();
        InputStream is = getResources().openRawResource(R.raw.recipes);
        JSONArray recipes;
        Intent intent = getIntent();
        ArrayList<Integer> products = intent.getIntegerArrayListExtra("products");
        try {
            recipes = new JSONArray(Utils.readJson(is));
            for (int i = 0; i < recipes.length(); i++) {
                JSONObject recipe = recipes.getJSONObject(i);
                JSONArray ingredients = recipe.getJSONArray("ingredients");
                boolean canCook = true;
                for (int j = 0; j < ingredients.length(); j++) {
                    int ingredient = ingredients.getInt(j);
                    boolean ok = false;
                    for (int k = 0; k < products.size(); k++) {
                        if (products.get(k) == ingredient) {
                            ok = true;
                        }
                    }
                    if (ok == false) {
                        canCook = false;
                    }
                }
                if (canCook == true) {
                    dishesList.add(recipe);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView caption = findViewById(R.id.caption);
        ImageView image = findViewById(R.id.image);
        TextView description = findViewById(R.id.description);
        for (JSONObject dish : dishesList) {
            try {
                caption.setText(dish.getString("caption"));
                Resources resources = getResources();
                int resourceId = resources.getIdentifier(dish.getString("image"), "drawable", getPackageName());
                image.setImageResource(resourceId);
                description.setText(dish.getString("description"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
    private void checkFirstStart() {

        SharedPreferences sp = getSharedPreferences("hasVisited",
                Context.MODE_PRIVATE);
        // проверяем, первый ли раз открывается программа (Если вход первый то вернет false)
        boolean hasVisited = sp.getBoolean("hasVisited", false);

        if (!hasVisited) {
            // Сработает если Вход первый
            saveTextBuffer("");
            //Ставим метку что вход уже был
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.commit(); //После этого hasVisited будет уже true и будет означать, что вход уже был

            //Ниже запускаем активность которая нужна при первом входе

        } else {

            //Сработает если вход в приложение уже был
            //Ниже запускаем активность которая нужна при последующих входах
        }
    }
    public void Savefav(View view){
        String str=openText();
        checkFirstStart();
        TextView caption = findViewById(R.id.caption);
        if(caption.getText().toString().contains("Вы не выбрали продукты") || caption.getText().toString().contains("Такого блюда не существует")){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Выберите валидные продукты", Toast.LENGTH_SHORT);
            toast.show();
        }
        //TextView caption = findViewById(R.id.caption);
        else if(!str.contains(caption.getText().toString())){
            str+="(";
            str+=caption.getText().toString();
            str+=")";
            saveTextBuffer(str);
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Такое блюдо уже находится в Избранном", Toast.LENGTH_SHORT);
            toast.show();
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
            String kostil="Slomalos";
            return kostil;
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
    private final static String FILE_NAME = "content2.txt";

    // 3 мильярда лошадиных сил
    public void saveTextBuffer(String obmen){
        FileOutputStream fos = null;
        try {

            String text=obmen;
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
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
}
