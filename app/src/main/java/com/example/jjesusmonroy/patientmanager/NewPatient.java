package com.example.jjesusmonroy.patientmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewPatient extends AppCompatActivity {

    Database db;
    EditText name,address,phone,email;
    DatePicker datePicker;
    Button insertar,cancelar;
    TextView tv;

    public static String titulo="";
    public static String key="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient);
        db=new Database(this);
        init();
        tv.setText(titulo);
        if(key!="") {
            load(db.query("select * from patient where idPatient='" + key + "'"));
            insertar.setText("Modificar");
        }
        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(key!=""){
                    int day = datePicker.getDayOfMonth();
                    int month = datePicker.getMonth() + 1;
                    int year = datePicker.getYear();
                    String date = day + "-" + month + "-" + year;
                    db.updatePatient(key,name.getText().toString(),
                            address.getText().toString(),phone.getText().toString(),
                            email.getText().toString(),date);
                    onBackPressed();
                }
                else {
                    int day = datePicker.getDayOfMonth();
                    int month = datePicker.getMonth() + 1;
                    int year = datePicker.getYear();
                    String date = day + "-" + month + "-" + year;
                    db.insertPatient(name.getText().toString(),
                            address.getText().toString(),
                            phone.getText().toString(),
                            email.getText().toString(),
                            date);
                }
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private void load(String [][] data){
        name.setText(data[0][1]);
        address.setText(data[0][2]);
        phone.setText(data[0][3]);
        email.setText(data[0][4]);
        String [] a = data[0][5].split("-");
        datePicker.updateDate(Integer.parseInt(a[2])
                ,Integer.parseInt(a[1])-1,
                Integer.parseInt(a[0]));
    }
    private void init(){
        name=findViewById(R.id.name);
        address=findViewById(R.id.address);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        datePicker = findViewById(R.id.date);
        insertar=findViewById(R.id.insertar);
        cancelar=findViewById(R.id.cancelar);
        tv=findViewById(R.id.textView);
    }

    private void clear(){
        name.setText("");
        address.setText("");
        phone.setText("");
        email.setText("");
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String actualdate =dateFormat.format(date);
        String [] split = actualdate.split("-");
        int day=Integer.parseInt(split[0]);
        int month=Integer.parseInt(split[1]);
        int year=Integer.parseInt(split[2]);
        datePicker.updateDate(year,month,day);
    }
}
