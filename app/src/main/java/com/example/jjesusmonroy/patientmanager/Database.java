package com.example.jjesusmonroy.patientmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jjesusmonroy on 12/03/18.
 */

public class Database extends SQLiteOpenHelper {

    private static String dbname="Database.db";

    public SQLiteDatabase db;

    public Database(Context context) {
        super(context, dbname, null, 1);
        db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE patient(idPatient integer primary key " +
                "autoincrement, name " +
                "varchar(50),address varchar(100),phonenumber varchar(15), email varchar(50),date date)");
        sqLiteDatabase.execSQL("CREATE TABLE medicine(idMed integer primary key autoincrement, medname " +
                "varchar(50), instructions varchar(300)," +
                "firstdate date, lastdate date)");
        sqLiteDatabase.execSQL("CREATE TABLE patient_med (idPatient integer, idMed integer," +
                "suffering varchar(300)," +
                "foreign key (idPatient) references patient(idPatient)," +
                "foreign key (idMed) references medicine(idMed))");
        sqLiteDatabase.execSQL("CREATE TABLE checkDate(checkdate date, idPatient integer," +
                "foreign key (idPatient) references patient(idPatient))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertPatient(String name, String address, String number, String email,
                              String date){
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("address",address);
        values.put("phonenumber",number);
        values.put("email",email);
        values.put("date",date);
        db.insert("patient",null,values);
    }

    public void insertMed(String medname, String instructions,
                          String firstdate, String lastdate, String suffering,
                          String idPatient){
        ContentValues values = new ContentValues();
        values.put("medname",medname);
        values.put("instructions",instructions);
        values.put("firstdate",firstdate);
        values.put("lastdate",lastdate);
        db.insert("medicine",null,values);
        String idmed=getId("select * from medicine where instructions='"+instructions+"' and " +
                "firstdate='"+firstdate+"' and lastdate ='"+lastdate+"'");
        ContentValues values2 = new ContentValues();
        values2.put("idPatient",idPatient);
        values2.put("idMed",idmed);
        values2.put("suffering",suffering);
        db.insert("patient_med",null,values2);
    }

    private String getId(String sql){
        String string="";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            string= cursor.getString(0);
        }
        if(cursor!=null){cursor.close();}
        return string;
    }

    public String [][] query(String sql){
        Cursor c = db.rawQuery(sql,null);
        String [][] elements = new String [c.getCount()][c.getColumnCount()+1];
        if(c.moveToFirst()){
            int counter=0;
            do{
                for(int i=0;i<c.getColumnCount();i++){
                    elements[counter][i]=c.getString(i);
                }
                counter++;
            }while(c.moveToNext());
        }
        if(c!=null)c.close();
        return elements;
    }


}
