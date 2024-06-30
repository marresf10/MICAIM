package com.example.micaim;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.example.micaim.adapters.SensoresAdapter;
import com.example.micaim.data.Sensores;
import com.example.micaim.models.Sensor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseReference sensoresRef;
    RecyclerView recyclerView;
    SensoresAdapter sensoresAdapter;
    ArrayList<Sensor> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.sensores);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        sensoresAdapter = new SensoresAdapter(this, list);
        recyclerView.setAdapter(sensoresAdapter);

        datosSensores();

        // Suscripción al tópico "alertas"
        FirebaseMessaging.getInstance().subscribeToTopic("alertas")
                .addOnCompleteListener(task -> {
                    String msg = "Suscripción exitosa";
                    if (!task.isSuccessful()) {
                        msg = "Fallo en la suscripción";
                    }
                    Log.d("FCM", msg);
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.graficas) {
            Toast.makeText(this, "Graficas", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, module_graphics.class);
            startActivity(intent);
            return true;
        }
        return true;
    }

    private void datosSensores() {
        sensoresRef = FirebaseDatabase.getInstance().getReference().child("sensores");
        sensoresRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                if (snapshot.exists()) {
                    float sensor1 = snapshot.child("sensor1").getValue(Float.class);
                    float sensor2 = snapshot.child("sensor2").getValue(Float.class);
                    float sensor3 = snapshot.child("sensor3").getValue(Float.class);

                    list.add(new Sensor("Sensor 1", sensor1));
                    list.add(new Sensor("Sensor 2", sensor2));
                    list.add(new Sensor("Sensor 3", sensor3));

                    sensoresAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("FirebaseError", "Error en Firebase: " + error.getMessage());
            }
        });
    }
}
