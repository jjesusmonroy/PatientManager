package com.example.jjesusmonroy.patientmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class NewMed extends AppCompatActivity {

    EditText medname,instructions,suffering;
    DatePicker firstdate,lastdate;
    Button save,cancel;
    TextView tv;
    Database db;
    public static String key="";
    public static String medKey="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_med);
        init();
        if(medKey.equals("")){
            tv.setText("Inserting new med");
            save.setText("Insert");
        }
        else {
            tv.setText("Modifying med");
            save.setText("Save");
            System.out.println(key);
            System.out.println(medKey);
            String query = "select m.medname,m.instructions,m.firstdate,m.lastdate,pm.suffering" +
                    " from patient p inner join patient_med pm on " +
                    "p.idPatient=pm.idPatient inner join medicine m on pm.idMed=" +
                    "m.idMed where p.idPatient ='" + key + "' and m.idMed ='"+medKey+"'group by m.idMed order by m.medname;";
            load(db.query(query));
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(medKey.equals("")) {
                    int day = firstdate.getDayOfMonth();
                    int month = firstdate.getMonth() + 1;
                    int year = firstdate.getYear();
                    String firstd = day + "-" + month + "-" + year;
                    int sday = lastdate.getDayOfMonth();
                    int smonth = lastdate.getMonth() + 1;
                    int syear = lastdate.getYear();
                    String lastd = sday + "-" + smonth + "-" + syear;
                    db.insertMed(medname.getText().toString(),
                            instructions.getText().toString(),
                            firstd, lastd,
                            suffering.getText().toString(), key);
                }
                else {
                    int day = firstdate.getDayOfMonth();
                    int month = firstdate.getMonth() + 1;
                    int year = firstdate.getYear();
                    String firstdate = day + "-" + month + "-" + year;
                    int lday = lastdate.getDayOfMonth();
                    int lmonth = lastdate.getMonth() + 1;
                    int lyear = lastdate.getYear();
                    String lastdate = lday + "-" + lmonth + "-" + lyear;
                    db.updateMed(medKey,key, medname.getText().toString(),
                            instructions.getText().toString(),firstdate,lastdate,
                            suffering.getText().toString()
                            );
                    onBackPressed();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
    private void load(String [][] data){
        medname.setText(data[0][0]);
        instructions.setText(data[0][1]);
        String [] a = data[0][2].split("-");
        firstdate.updateDate(Integer.parseInt(a[2])
                ,Integer.parseInt(a[1])-1,
                Integer.parseInt(a[0]));
        String [] b = data[0][3].split("-");
        lastdate.updateDate(Integer.parseInt(b[2])
                ,Integer.parseInt(b[1])-1,
                Integer.parseInt(b[0]));
        suffering.setText(data[0][4]);

    }
    private void init (){
        instructions=findViewById(R.id.instructions);
        suffering=findViewById(R.id.insertingsuffering);
        medname=findViewById(R.id.insertingmedname);
        tv=findViewById(R.id.textView4);
        firstdate=findViewById(R.id.firstdate);
        lastdate=findViewById(R.id.lastdate);

        save=findViewById(R.id.nmsave);
        cancel=findViewById(R.id.nmcancel);

        db=new Database(this);
    }
}
