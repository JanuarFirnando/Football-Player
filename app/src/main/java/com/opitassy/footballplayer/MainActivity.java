package com.opitassy.footballplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MyDatabaseHelper myDB;
    private FloatingActionButton fabTambah;
    private RecyclerView rvPlayer;
    private AdapterFootballPlayer adPlayer;
    private ArrayList<String> arrid, arrNama, arrNomor, arrKlub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new MyDatabaseHelper(MainActivity.this);
        fabTambah = findViewById(R.id.fab_tambah);
        rvPlayer = findViewById(R.id.rv_player);

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        tampilPlayer();
        //git config --global user.email "you@example.com"
        //git config --global user.name "Your Name"
    }
    private void SQLiteToArrayList()
    {
        Cursor cursor = myDB.bacaDataPlayer();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "KATEK DATA WOY", Toast.LENGTH_SHORT).show();
        }
        else {
            while(cursor.moveToNext())
            {
                arrid.add(cursor.getString(0));
                arrNama.add(cursor.getString(1));
                arrNomor.add(cursor.getString(2));
                arrKlub.add(cursor.getString(3));
            }
        }
    }
    private void tampilPlayer()
    {
        arrid = new ArrayList<>();
        arrNama = new ArrayList<>();
        arrNomor = new ArrayList<>();
        arrKlub = new ArrayList<>();

        SQLiteToArrayList();

        adPlayer = new AdapterFootballPlayer(MainActivity.this, arrid, arrNama, arrNomor, arrKlub);
        rvPlayer.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvPlayer.setAdapter(adPlayer);
    }
}