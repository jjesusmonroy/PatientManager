package com.example.jjesusmonroy.patientmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jjesusmonroy on 12/03/18.
 */

public class Database extends SQLiteOpenHelper {

    private static String dbname="Database.db";
    private static String patientTable="patient";
    private static String medTable="medicine";
    private static String patMedTable="patient_med";

    public Database(Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+patientTable+"(idPatient int primary key " +
                "autoincrement, name " +
                "varchar(50),address varchar(100),phonenumber int, email varchar(50),date date)");
        sqLiteDatabase.execSQL("CREATE TABLE "+medTable+"(idMed int primary key, medname " +
                "varchar(50), suffering varchar(300), instructions varchar(300), condate date," +
                "firstdate date, lastdate date)");
        sqLiteDatabase.execSQL("CREATE TABLE "+patMedTable+" (idPatient int, idMed int," +
                "foreign key (idPatient) references patient(idPatient)," +
                "foreign key (idMed) references medicine(idMed))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertPatient(String name, String address, int number, String email,
                              String date){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("address",address);
        values.put("phonenumber",number);
        values.put("email",email);
        values.put("date",date);
        db.insert("patient",null,values);
    }

}
