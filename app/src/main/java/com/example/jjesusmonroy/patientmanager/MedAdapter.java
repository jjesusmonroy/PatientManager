package com.example.jjesusmonroy.patientmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jjesusmonroy on 13/03/18.
 */

public class MedAdapter extends RecyclerView.Adapter<MedAdapter.RecyclerViewHolder>{

    public static String key="";
    String [][] data;
    Context context;

    public MedAdapter(String[][] data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public MedAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater layoutInflater;
        layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.med_layout,parent,false);
        return new MedAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MedAdapter.RecyclerViewHolder holder, int position) {
        holder.medname.setText("medicine: "+data[position][0]);
        holder.suffering.setText("suffering: "+data[position][1]);
        holder.instructions.setText("instructions: "+data[position][2]);
        holder.treatment.setText(treamentString(treatment(data[position][3])));
        final String medkey=data[position][4];
        holder.modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewMed newMed = new NewMed();
                newMed.medKey=medkey;
                Intent i = new Intent(context,NewMed.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView medname,suffering,instructions,modify,treatment;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            medname=itemView.findViewById(R.id.imedname);
            suffering=itemView.findViewById(R.id.isuffering);
            instructions=itemView.findViewById(R.id.iinstructions);
            modify=itemView.findViewById(R.id.imodify);
            treatment=itemView.findViewById(R.id.itreatment);
        }
    }
    private int treatment(String lastdate){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String actualdate =dateFormat.format(date);
        String [] a = actualdate.split("-");
        int aday = Integer.parseInt(a[0]);
        int amonth = Integer.parseInt(a[1]);
        int ayear = Integer.parseInt(a[2]);
        String [] b = lastdate.split("-");
        int day = Integer.parseInt(b[0]);
        int month = Integer.parseInt(b[1]);
        int year = Integer.parseInt(b[2]);
        return (((year*365)+(month*30)+day)-((ayear*365)+(amonth*30)+aday));
    }
    private String treamentString(int a){
        if(a<0)return "Treatment finished";
        else if(a==0)return "Treatment finish today";
        else return "Finish on "+a+" days";
    }

}
