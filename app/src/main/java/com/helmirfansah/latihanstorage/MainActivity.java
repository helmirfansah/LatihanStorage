package com.helmirfansah.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonInternal = findViewById(R.id.buttonInternal);
        Button buttonExternal = findViewById(R.id.buttonExternal);
        buttonInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InternalStorageActivity.class);
                startActivity(intent);
            }
        });
        buttonExternal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, ExternalStorageActivity.class);
                startActivity(intent);
            }
        });
    }


}