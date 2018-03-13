package com.example.jjesusmonroy.patientmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class NewPatient extends AppCompatActivity {

    Database db;
    EditText name,address,phone,email;
    DatePicker datePicker;
    Button insertar,cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient);
        db=new Database(this);
        init();

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth()+1;
                int year = datePicker.getYear();
                String date = day+"-"+month+"-"+year;
                db.insertPatient(name.getText().toString(),
                        address.getText().toString(),
                        Integer.parseInt(phone.getText().toString()),
                        email.getText().toString(),
                        date);
            }
        });
    }
    private void init(){
        name=findViewById(R.id.name);
        address=findViewById(R.id.address);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        datePicker = findViewById(R.id.date);
        insertar=findViewById(R.id.insertar);
        cancelar=findViewById(R.id.cancelar);
    }
}
