package com.example.jjesusmonroy.patientmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jjesusmonroy on 12/03/18.
 */

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.RecyclerViewHolder>{
    String [][] data;

    public PatientAdapter(String[][] data) {
        this.data = data;
    }

    @Override
    public PatientAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_layout,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PatientAdapter.RecyclerViewHolder holder, int position) {
        holder.name.setText("name : "+data[position][1]);
        holder.phone.setText("phone : "+data[position][3]);
        holder.age.setText("age : "+calculateAge(data[position][5]));
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView name,phone,age,modify,meds;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            phone=itemView.findViewById(R.id.ptphonenumber);
            age=itemView.findViewById(R.id.ptage);
            modify=itemView.findViewById(R.id.ptmodify);
            meds=itemView.findViewById(R.id.ptmeds);
        }
    }

    private int calculateAge(String birthdate){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String actualdate =dateFormat.format(date);
        String [] a = actualdate.split("-");
        int aday = Integer.parseInt(a[0]);
        int amonth = Integer.parseInt(a[1]);
        int ayear = Integer.parseInt(a[2]);
        String [] b = birthdate.split("-");
        int day = Integer.parseInt(b[0]);
        int month = Integer.parseInt(b[1]);
        int year = Integer.parseInt(b[2]);
        return (((ayear*365)+(amonth*30)+aday)-((year*365)+(month*30)+day))/365;

    }
}
