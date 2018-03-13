package com.example.jjesusmonroy.patientmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        holder.name.setText("name : "+data[position][0]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
        }
    }
}
