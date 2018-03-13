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
                "varchar(50),address varchar(100),phonenumber integer, email varchar(50),date date)");
        sqLiteDatabase.execSQL("CREATE TABLE medicine(idMed integer primary key autoincrement, medname " +
                "varchar(50), suffering varchar(300), instructions varchar(300), condate date," +
                "firstdate date, lastdate date)");
        sqLiteDatabase.execSQL("CREATE TABLE patient_med (idPatient integer, idMed integer," +
                "foreign key (idPatient) references patient(idPatient)," +
                "foreign key (idMed) references medicine(idMed))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertPatient(String name, String address, int number, String email,
                              String date){
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("address",address);
        values.put("phonenumber",number);
        values.put("email",email);
        values.put("date",date);
        db.insert("patient",null,values);
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
