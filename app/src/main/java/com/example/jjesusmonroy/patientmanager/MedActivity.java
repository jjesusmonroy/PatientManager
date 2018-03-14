package com.example.jjesusmonroy.patientmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MedActivity extends AppCompatActivity {
    Button insertar;
    RecyclerView recycler;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    Database db;

    public static String irving="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med);
        init();
        listAll();
        NewMed newMed = new NewMed();
        newMed.key=irving;
        newMed.medKey="";
        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MedActivity.this,NewMed.class);
                startActivity(i);
            }
        });
    }



    private void init(){
        db = new Database(this);
        insertar=findViewById(R.id.medinsert);
        recycler=findViewById(R.id.medrecycler);
    }

    private void listAll(){
        String query = "select m.medname,pm.suffering,m.instructions," +
                    "m.lastdate,m.idMed from patient p inner join patient_med pm on " +
                    "p.idPatient=pm.idPatient inner join medicine m on pm.idMed=" +
                    "m.idMed where p.idPatient ='" + irving + "' order by m.medname;";
        String [][] data = db.query(query);
        adapter=new MedAdapter(data,this);
        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listAll();
    }
}
