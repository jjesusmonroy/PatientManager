package com.example.jjesusmonroy.patientmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MedActivity extends AppCompatActivity {
    Button insertar;
    String key="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med);
        init();
        Intent intent = getIntent();
        if (intent != null) {
            key = intent.getStringExtra("key");
        }

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MedActivity.this,NewMed.class);
                i.putExtra("key",key);
                startActivity(i);
            }
        });
    }

    private void init(){
        insertar=findViewById(R.id.medinsert);
    }

}
