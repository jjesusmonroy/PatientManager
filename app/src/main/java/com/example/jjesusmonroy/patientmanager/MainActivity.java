package com.example.jjesusmonroy.patientmanager;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    Database db;
    Button newPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new Database(this);
        init();
        listAll();

        newPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewPatient newPatient = new NewPatient();
                newPatient.titulo="Inserting new patient";
                newPatient.key="";
                Intent i = new Intent(MainActivity.this,NewPatient.class);
                startActivity(i);
                listAll();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listAll();
    }

    private void init(){
        recycler= findViewById(R.id.recyclerView);
        newPatient=findViewById(R.id.newPatient);
    }

    private void listAll(){
        String [][] data = db.query("select * from patient order by name");
        adapter=new PatientAdapter(data,this);
        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
    }


}
