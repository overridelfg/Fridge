package com.example.kirillrychkov.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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

public class Favorite extends AppCompatActivity {

    List<String> main=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        ListView lvMain=findViewById(R.id.lvMain);
        String str=openText();
        checkFirstStart();
        //кодировка(aa)
        String buffer="";
        char[] chArray=str.toCharArray();
        for(int i=0;i<str.length();i++){
            if(chArray[i]=='('){
                i++;
                while(chArray[i]!=')'){
                    buffer+=chArray[i];
                    i++;
                }
                main.add(buffer);
                buffer="";
            }
        }
        ArrayAdapter<String> adapter2;
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,main);
        lvMain.setAdapter(adapter2);
        lvMain.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                String str=main.get(position);
                String all=openText();
                char[] chArray=all.toCharArray();
                int index=all.indexOf(str);
                index--;
                String buf1="";
                for(int i=0;i<index;i++){
                    buf1+=chArray[i];
                }
                String buf2="";
                while(chArray[index]!=')'){
                    index++;
                }
                index++;
                for(int i=index;i<all.length();i++){
                    buf2+=chArray[i];
                }
                String answer=buf1+buf2;
                saveTextBuffer(answer);
                Intent intent=new Intent(Favorite.this,Favorite.class);
                startActivity(intent);
                finish();
                return true;
            }
        });


        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id){
                String caption = ((TextView)itemClicked).getText().toString();
                Intent intent=new Intent(Favorite.this,Dishes.class);
                intent.putExtra("caption", caption);
                startActivity(intent);
            }
        });
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
                if (recipe.getString("caption") == caption) {
                    dishesList.add(recipe);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
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

    public void clear(View view){
        String clear="";
        saveTextBuffer(clear);
        ListView lvMain=findViewById(R.id.lvMain);
        ArrayAdapter<String> adapter2;
        List<String> main=new ArrayList<>();
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,main);
        lvMain.setAdapter(adapter2);


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
            String kostil="Broke";
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
    private void checkFirstStart() {

        SharedPreferences sp = getSharedPreferences("hasVisited",
                Context.MODE_PRIVATE);
        boolean hasVisited = sp.getBoolean("hasVisited", false);

        if (!hasVisited) {
            saveTextBuffer("");

            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.commit();

        } else {

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