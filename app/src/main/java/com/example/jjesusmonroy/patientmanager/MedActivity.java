package com.example.jjesusmonroy.patientmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MedActivity extends AppCompatActivity {
    Button insertar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med);
        init();
        String key="";
        Intent intent = getIntent();
        if (intent != null) {
            key = intent.getStringExtra("key");
        }

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void init(){
        insertar=findViewById(R.id.medinsert);
    }

}
