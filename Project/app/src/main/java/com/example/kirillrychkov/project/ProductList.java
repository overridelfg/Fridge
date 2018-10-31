package com.example.kirillrychkov.project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductList extends AppCompatActivity {
    private final static String FILE_NAMEBUFFER = "content.txt";
    ListView choiceList;
    TextView selection;
    Map<Integer, Integer> map = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkFirstStart();
        setContentView(R.layout.activity_productlist);
        final List<String> productList=new ArrayList<String>();
        InputStream is = getResources().openRawResource(R.raw.user_preferences);
        JSONObject userPreferences;
        InputStream is2 = getResources().openRawResource(R.raw.products);
        JSONArray products;
        try {
            products = new JSONArray(Utils.readJson(is2));
            userPreferences = new JSONObject(Utils.readJson(is));
            JSONArray productIds = userPreferences.getJSONArray(openText());
            for (int i = 0; i < productIds.length(); i++) {
                int productId = productIds.getInt(i);
                String product = products.getString(productId);
                productList.add(product);
                map.put(i,productId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        ListView lvMain = findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,productList );
        selection =  findViewById(R.id.choose1);
        choiceList =  findViewById(R.id.listview);
        lvMain.setAdapter(adapter);




        checked.add("Выберите продукты:");
        String r= checked.get(0);
        selection.setText(r);


    }
    List <String> checked=new ArrayList<>();
    public void onDishes(View view){
        SparseBooleanArray chosen = choiceList.getCheckedItemPositions();
        ArrayList<Integer> productIds = new ArrayList<>();
        for(int i = 0;i<chosen.size();i++) {
            if (chosen.valueAt(i)) {
                productIds.add(map.get(chosen.keyAt(i)));
            }
        }
        Intent intent=new Intent(ProductList.this,Dishes.class);
        intent.putExtra("products", productIds);
        startActivity(intent);
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
