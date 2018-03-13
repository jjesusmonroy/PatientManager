package com.example.jjesusmonroy.patientmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class NewMed extends AppCompatActivity {

    EditText medname,instructions,suffering;
    DatePicker firstdate,lastdate;
    Button save,cancel;

    Database db;
    String key="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_med);
        init();
        Intent i = getIntent();
        key=i.getStringExtra("key");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day = firstdate.getDayOfMonth();
                int month = firstdate.getMonth()+1;
                int year = firstdate.getYear();
                String firstd = day+"-"+month+"-"+year;
                int sday = lastdate.getDayOfMonth();
                int smonth = lastdate.getMonth()+1;
                int syear = lastdate.getYear();
                String lastd = sday+"-"+smonth+"-"+syear;
                db.insertMed(medname.getText().toString(),
                        instructions.getText().toString(),
                        firstd, lastd,
                        suffering.getText().toString(),key);
            }
        });

    }

    private void init (){
        instructions=findViewById(R.id.instructions);
        suffering=findViewById(R.id.insertingsuffering);
        medname=findViewById(R.id.insertingmedname);

        firstdate=findViewById(R.id.firstdate);
        lastdate=findViewById(R.id.lastdate);

        save=findViewById(R.id.nmsave);
        cancel=findViewById(R.id.nmcancel);

        db=new Database(this);
    }
}
