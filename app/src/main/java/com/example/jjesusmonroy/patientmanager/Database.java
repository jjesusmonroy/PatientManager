package com.example.jjesusmonroy.patientmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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
        sqLiteDatabase.execSQL("CREATE TABLE checkdate(checkdate date, idPatient integer," +
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

    public void updatePatient(String id,String name, String address, String phone, String email
    , String date){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("address",address);
        contentValues.put("phonenumber",phone);
        contentValues.put("email",email);
        contentValues.put("date",date);
        db.update("patient",contentValues,"idPatient='"+id+"'",null);
    }

    public void updateMed(String id,String id2, String medname, String instructions, String firstdate
    , String lastdate, String suffering){
        ContentValues cv1 = new ContentValues();
        cv1.put("medname",medname);
        cv1.put("instructions",instructions);
        cv1.put("firstdate",firstdate);
        cv1.put("lastdate",lastdate);
        db.update("medicine",cv1,"idMed='"+id+"'",null);
        String query="update patient_med set suffering = '"+suffering+"' where " +
                "idPatient = '"+id2+"' and idMed ='"+id+"'";
        db.execSQL(query);
    }

    public void insertCheckDate(String idPatient,String date){
        if(exist(idPatient))db.execSQL("update checkdate set checkdate='"+date+"' where " +
                "idPatient='"+idPatient+"'");
        else db.execSQL("insert into checkdate values ('"+date+"','"+idPatient+"')");
    }
    public boolean exist(String idPatient){
        boolean flag=false;
        Cursor c = db.rawQuery("select * from checkdate",null);
        ArrayList<String> list = new ArrayList<>();
        if(c.moveToFirst()){
            do{
                list.add(c.getString(1));
            }while(c.moveToNext());
        }
        if(c!=null)c.close();
        for(String element:list){
            if(element.equals(idPatient))flag=true;
        }
        return flag;
    }



}
