package com.example.micaim;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class module_graphics extends AppCompatActivity {

    private List<String> xValues = Arrays.asList("Dato 1", "Dato 2", "Dato 3", "Dato 4", "Dato 5");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_graphics);
        setupToolbar();
        setGraphic1();
    }

    private void setGraphic1() {
        // Primer grafica (Semanal)
        BarChart barChart = findViewById(R.id.chart);
        barChart.getAxisRight().setDrawLabels(false);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 45f));
        entries.add(new BarEntry(1, 80f));
        entries.add(new BarEntry(2, 65f));
        entries.add(new BarEntry(3, 38f));
        entries.add(new BarEntry(4, 2f));
        entries.add(new BarEntry(5, 30f));
        entries.add(new BarEntry(6, 50f));

        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMaximum(100f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(10);

        BarDataSet dataSet = new BarDataSet(entries, "Semanal");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        BarData barData = new BarData(dataSet);
        barChart.setData(barData);

        barChart.getDescription().setEnabled(false);
        barChart.invalidate();

        List<String> xValuesSemana = Arrays.asList("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo");
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xValuesSemana));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setGranularityEnabled(true);

        // Segunda grafica (Mensual)
        BarChart barChart2 = findViewById(R.id.chart2);
        barChart2.getAxisRight().setDrawLabels(false);

        ArrayList<BarEntry> entries2 = new ArrayList<>();
        entries2.add(new BarEntry(0, 50f));
        entries2.add(new BarEntry(1, 70f));
        entries2.add(new BarEntry(2, 90f));
        entries2.add(new BarEntry(3, 20f));
        entries2.add(new BarEntry(4, 10f));

        YAxis yAxis2 = barChart2.getAxisLeft();
        yAxis2.setAxisMaximum(100f);
        yAxis2.setAxisMinimum(0f);
        yAxis2.setAxisLineWidth(2f);
        yAxis2.setAxisLineColor(Color.BLACK);
        yAxis2.setLabelCount(10);

        BarDataSet dataSet2 = new BarDataSet(entries2, "Mensual");
        dataSet2.setColors(ColorTemplate.MATERIAL_COLORS);

        BarData barData2 = new BarData(dataSet2);
        barChart2.setData(barData2);

        barChart2.getDescription().setEnabled(false);
        barChart2.invalidate();

        List<String> xValuesMes = Arrays.asList("Semana 1", "Semana 2", "Semana 3", "Semana 4", "Semana 5");
        barChart2.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xValuesMes));
        barChart2.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart2.getXAxis().setGranularity(1f);
        barChart2.getXAxis().setGranularityEnabled(true);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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