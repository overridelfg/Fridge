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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        TextView writeinformation = findViewById(R.id.writeinformation);
        writeinformation.setText(UserSetting.user);
    }

    public void onMain2(View view) {
        Intent intent = new Intent(Setting.this, MainActivity.class);
        startActivity(intent);
    }

    public void onMain3(View view) {
        Intent intent = new Intent(Setting.this, UserSetting.class);
        startActivity(intent);
    }
}
