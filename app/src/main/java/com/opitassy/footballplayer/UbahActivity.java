package com.opitassy.footballplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UbahActivity extends AppCompatActivity {
    private EditText etnama, etnomor, etklub;
    private Button btn_ubah;
    MyDatabaseHelper myDB = new MyDatabaseHelper(UbahActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);
        etnama = findViewById(R.id.et_nama);
        etnomor = findViewById(R.id.et_nomor);
        etklub = findViewById(R.id.et_club);
        btn_ubah = findViewById(R.id.btn_ubah);

        Intent varIntent = getIntent();
        String id = varIntent.getStringExtra("varID");
        String nama = varIntent.getStringExtra("varnama");
        String nomor = varIntent.getStringExtra("varnomor");
        String klub = varIntent.getStringExtra("varklub");

        etnama.setText(nama);
        etnomor.setText(nomor);
        etklub.setText(klub);

        btn_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getNama, getNomor, getKlub;
                getNama = etnama.getText().toString();
                getNomor = etnomor.getText().toString();
                getKlub = etklub.getText().toString();

                if (getNama.trim().equals("")) {
                    etnama.setError("Nama Harus Diisi");
                }
                if (getNomor.trim().equals("")) {
                    etnomor.setError("Nomor Punggung Harus Diisi");
                }
                if (getKlub.trim().equals("")) {
                    etklub.setError("Nama Club Harus Diisi");
                } else {
                    long eks = myDB.ubahPlayer(id, getNama, getNomor, getKlub);
                    if (eks == -1) {
                        Toast.makeText(UbahActivity.this, "Gagal Mengubah Data", Toast.LENGTH_LONG).show();
                        etnama.requestFocus();
                    } else {
                        Toast.makeText(UbahActivity.this, "Berhasil Mengubah Data", Toast.LENGTH_LONG).show();
                        finish();
                    }

                }
            }
        });
    }
}