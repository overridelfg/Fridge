package com.example.kirillrychkov.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        checkFirstStart();
        ImageView imageViewDishes = findViewById(R.id.image);
        Intent intent = getIntent();
        ArrayList<Integer> products = intent.getIntegerArrayListExtra("products");
        if(products != null){
            openByProducts();
        } else {
            openFavorite();
        }
        if (hideFavorites()) {
            View btn = findViewById(R.id.favorite2);
            btn.setVisibility(View.INVISIBLE);
        }
        Bitmap mbitmap = ((BitmapDrawable)imageViewDishes.getDrawable()).getBitmap();
        Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
        Canvas canvas = new Canvas(imageRounded);
        Paint mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint);// Round Image Corner 100 100 100 100
        imageViewDishes.setImageBitmap(imageRounded);
    }
    public void openFavorite(){
        ArrayList<JSONObject> dishesList = new ArrayList<>();
        InputStream is = getResources().openRawResource(R.raw.recipes);
        JSONArray recipes;
        Intent intent = getIntent();
        String caption = intent.getStringExtra("caption");
        try {
            recipes = new JSONArray(Utils.readJson(is));
            for (int i = 0; i < recipes.length(); i++) {
                JSONObject recipe = recipes.getJSONObject(i);
                if (recipe.getString("caption").equals(caption)) {
                    dishesList.add(recipe);
                    break;
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        TextView captionView = findViewById(R.id.caption);
        ImageView image = findViewById(R.id.image);
        TextView description = findViewById(R.id.description);
        for (JSONObject dish : dishesList) {
            try {
                captionView.setText(dish.getString("caption"));
                Resources resources = getResources();
                int resourceId = resources.getIdentifier(dish.getString("image"), "drawable", getPackageName());
                image.setImageResource(resourceId);
                description.setText(dish.getString("description"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public void openByProducts(){
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
        boolean hasVisited = sp.getBoolean("hasVisited", false);

        if (!hasVisited) {
            saveTextBuffer("");
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.commit();


        }

    }
    public void Savefav(View view){
        checkFirstStart();
        String str=openText();
        TextView caption = findViewById(R.id.caption);
         if(!str.contains(caption.getText().toString())){
            str+="(";
            str+=caption.getText().toString();
            str+=")";
            saveTextBuffer(str);
            Button button = findViewById(R.id.favorite2);
            button.setVisibility(View.INVISIBLE);

        }
    }
    public boolean hideFavorites() {
        String str = openText();
        TextView captionView = findViewById(R.id.caption);
        String caption = captionView.getText().toString();
        return str.contains(caption) || caption.equals("Вы не выбрали продукты") || caption.equals("Такого блюда не существует");
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
