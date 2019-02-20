package com.example.barlinegraph;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<BarEntry> BarEntry = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<>();
    FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private Data user;
    private Long val;
    BarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user= new Data();
        chart = (BarChart) findViewById(R.id.bar_chart);
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference("Price");
        databaseReference.child("2016").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.e("value",snapshot.getValue().toString());
                val= (Long) snapshot.getValue();
                BarEntry.add(new BarEntry(val, 0));
                BarEntry.add(new BarEntry(val+10, 1));
                BarEntry.add(new BarEntry(val+20, 2));
                BarDataSet dataSet = new BarDataSet(BarEntry, "Indian Petrol Price");
                labels.add("2016");
                labels.add("2017");
                labels.add("2018");
                BarData data = new BarData(labels, dataSet);
                dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                chart.setData(data);
                chart.setDescription("Assignment Demo");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
