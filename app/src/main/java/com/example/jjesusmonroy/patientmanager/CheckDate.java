package com.example.jjesusmonroy.patientmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CheckDate extends AppCompatActivity {

    public static String key="";
    public static String patientname="";

    DatePicker datePicker;
    TextView title;
    Button save;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_date);
        init();
        title.setText("check date for \n"+patientname);
        if(db.exist(key)){
           load(db.query("select * from checkdate where idPatient ='"+key+"'"));
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                String date = day + "-" + month + "-" + year;
                System.out.println(key);
                System.out.println(date);
                db.insertCheckDate(key,date);
                onBackPressed();
            }
        });
    }

    private void init(){
        datePicker=findViewById(R.id.checkdatepicker);
        title=findViewById(R.id.checkdatetitle);
        save=findViewById(R.id.checkdatesave);
        db = new Database(this);
    }

    private void load(String[][] data){
        String [] a = data[0][0].split("-");
        datePicker.updateDate(Integer.parseInt(a[2])
                ,Integer.parseInt(a[1])-1,
                Integer.parseInt(a[0]));
    }
}
