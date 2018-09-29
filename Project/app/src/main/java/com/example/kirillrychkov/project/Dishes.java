package com.example.kirillrychkov.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
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
                        if(products.get(k) == ingredient){
                            ok=true;
                        }
                    }
                    if(ok==false){
                        canCook=false;
                    }
                }
                if(canCook==true){
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
        for (JSONObject dish:dishesList) {
            try {
                caption.setText(dish.getString("caption"));
                description.setText(dish.getString("description"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
