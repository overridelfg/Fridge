package com.example.kirillrychkov.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dishes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes);
        String str="";
        str=openText();
        int buf=0;
        int a[]=new int[1000];
        int count=0;
        char[] chArray=str.toCharArray();
        for(int i=0;i<str.length();i=i+2){
                if(chArray[i+1]=='1' || chArray[i+1]=='2' || chArray[i+1]=='3' || chArray[i+1]=='4' || chArray[i+1]=='5' || chArray[i+1]=='6' || chArray[i+1]=='7' || chArray[i+1]=='8' || chArray[i+1]=='9' || chArray[i+1]=='0'){
                    buf=Character.getNumericValue(chArray[i])*10;
                    buf+=Character.getNumericValue(chArray[i+1]);
                    a[count]=buf;
                    count++;
                    buf=0;
                    i++;

            }
                else {
                    buf += Character.getNumericValue(chArray[i]);
                    a[count]=buf;
                    count++;
                    buf=0;
                }
        }
        TextView textView=findViewById(R.id.Textpidor);
        String qwer="";
        for(int i=0;i<count;i++){
            qwer+=a[i];
            qwer+=" ";
        }
        textView.setText(qwer);
        for(int i = 0;i<count;i++){
            if(a[i]==1){
                int check=0;
                for(int j=i;j<count;j++){
                    if(a[j]==3){
                        check=1;
                    }
                }
                if(check==1){
                    textView.setText("один и три");
                }
                else textView.setText("False");
                //....
                //инициализируй текст и картинку
                //текст сет картинка сет
            }

        }
        for(int i = 0;i<count;i++){
            if(a[i]==2){
                int check=0;
                for(int j=i;j<count;j++){
                    if(a[j]==4){
                        check=1;
                    }
                    if(a[j]==5 && check==1){
                        check=2;
                    }
                }
                if(check==2){
                    textView.setText("1 i 4 i 5");
                }
                else textView.setText("False");
                //....
                //инициализируй текст и картинку
                //текст сет картинка сет
            }

        }

    }






    private final static String FILE_NAME="test.txt";

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

            boolean kostil=true;
            return FILE_NAME;
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                boolean kostil=true;
            }
        }
    }
}
