package com.example.micaim;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.micaim.data.Mes;
import com.example.micaim.data.Semana;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class module_graphics extends AppCompatActivity {

    DatabaseReference sensoresRef;
    ArrayList<Semana> listSemana = new ArrayList<>();
    ArrayList<Mes> listMes = new ArrayList<>();
    BarChart barChart;
    BarChart barChart2;
    private boolean datosCargadosSemana = false;
    private boolean datosCargadosMes = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_graphics);
        setupToolbar();
        barChart = findViewById(R.id.chart);
        barChart2 = findViewById(R.id.chart2);
        obtenerDatosSemana();
        obtenerDatosMensual();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void obtenerDatosSemana() {
        if (datosCargadosSemana) {
            graficaSemanal();
            return;
        }

        sensoresRef = FirebaseDatabase.getInstance().getReference().child("Semana");

        sensoresRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listSemana.clear();
                if (snapshot.exists()) {
                    Float lunes = snapshot.child("Lunes").getValue(Float.class);
                    Float martes = snapshot.child("Martes").getValue(Float.class);
                    Float miercoles = snapshot.child("Miercoles").getValue(Float.class);
                    Float jueves = snapshot.child("Jueves").getValue(Float.class);
                    Float viernes = snapshot.child("Viernes").getValue(Float.class);
                    Float sabado = snapshot.child("Sabado").getValue(Float.class);
                    Float domingo = snapshot.child("Domingo").getValue(Float.class);

                    if (lunes != null && martes != null && miercoles != null && jueves != null && viernes != null && sabado != null && domingo != null) {
                        listSemana.add(new Semana(lunes, martes, miercoles, jueves, viernes, sabado, domingo));
                        Log.d("datos:", listSemana.toString());
                        datosCargadosSemana = true;
                        graficaSemanal();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("FirebaseError", "Error en Firebase: " + error.getMessage());
            }
        });
    }

    private void obtenerDatosMensual() {
        if (datosCargadosMes) {
            graficaMensual();
            return;
        }

        sensoresRef = FirebaseDatabase.getInstance().getReference().child("Mes");

        sensoresRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listMes.clear();
                if (snapshot.exists()) {
                    Float Semana1 = snapshot.child("Semana1").getValue(Float.class);
                    Float Semana2 = snapshot.child("Semana2").getValue(Float.class);
                    Float Semana3 = snapshot.child("Semana3").getValue(Float.class);
                    Float Semana4 = snapshot.child("Semana4").getValue(Float.class);
                    Float Semana5 = snapshot.child("Semana5").getValue(Float.class);

                    if (Semana1 != null && Semana2 != null && Semana3 != null && Semana4 != null && Semana5 != null) {
                        listMes.add(new Mes(Semana1, Semana2, Semana3, Semana4, Semana5));
                        Log.d("datos:", listMes.toString());
                        datosCargadosMes = true;
                        graficaMensual();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("FirebaseError", "Error en Firebase: " + error.getMessage());
            }
        });
    }

    private void graficaSemanal() {
        if (listSemana.isEmpty()) {
            return;
        }

        ArrayList<BarEntry> entries = new ArrayList<>();
        Semana semana = listSemana.get(0);

        entries.add(new BarEntry(0, semana.getLunes()));
        entries.add(new BarEntry(1, semana.getMartes()));
        entries.add(new BarEntry(2, semana.getMiercoles()));
        entries.add(new BarEntry(3, semana.getJueves()));
        entries.add(new BarEntry(4, semana.getViernes()));
        entries.add(new BarEntry(5, semana.getSabado()));
        entries.add(new BarEntry(6, semana.getDomingo()));

        BarDataSet dataSet = new BarDataSet(entries, "Semanal");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        BarData barData = new BarData(dataSet);
        barChart.setData(barData);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setAxisMinimum(0f);

        barChart.getDescription().setEnabled(false);
        barChart.invalidate();

        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setAxisMinimum(0f);

        List<String> xValuesSemana = Arrays.asList("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo");
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xValuesSemana));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setGranularityEnabled(true);
    }

    private void graficaMensual() {
        if (listMes.isEmpty()) {
            return;
        }

        ArrayList<BarEntry> entries = new ArrayList<>();
        Mes mes = listMes.get(0);

        entries.add(new BarEntry(0, mes.getSemana1() ));
        entries.add(new BarEntry(1, mes.getSemana2()));
        entries.add(new BarEntry(2, mes.getSemana3()));
        entries.add(new BarEntry(3, mes.getSemana4()));
        entries.add(new BarEntry(4, mes.getSemana5()));

        BarDataSet dataSet = new BarDataSet(entries, "Mensual");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        BarData barData = new BarData(dataSet);
        barChart2.setData(barData);

        YAxis leftAxis = barChart2.getAxisLeft();
        leftAxis.setAxisMinimum(0f);

        barChart2.getDescription().setEnabled(false);
        barChart2.invalidate();

        YAxis rightAxis = barChart2.getAxisRight();
        rightAxis.setAxisMinimum(0f);

        List<String> xValuesMes = Arrays.asList("Semana 1", "Semana 2", "Semana 3", "Semana 4", "Semana 5");
        barChart2.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xValuesMes));
        barChart2.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart2.getXAxis().setGranularity(1f);
        barChart2.getXAxis().setGranularityEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}