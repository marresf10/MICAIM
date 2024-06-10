package com.example.micaim.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.micaim.R;
import com.example.micaim.models.Sensor;

import java.util.ArrayList;

public class SensoresAdapter extends RecyclerView.Adapter<SensoresAdapter.MyViewHolder> {

    Context context;
    ArrayList<Sensor> list;

    public SensoresAdapter(Context context, ArrayList<Sensor> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Sensor sensor = list.get(position);
        holder.sensorName.setText(sensor.getNombre() + ": ");
        holder.sensorValue.setText(sensor.getValor() + " ppm");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView sensorName, sensorValue;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sensorName = itemView.findViewById(R.id.sensorid);
            sensorValue = itemView.findViewById(R.id.sensorvalue);
        }
    }
}
