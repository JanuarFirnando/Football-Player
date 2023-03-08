package com.opitassy.footballplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahActivity extends AppCompatActivity {
    private EditText etnama, etnomor, etklub;
    private Button btn_simpan;
    MyDatabaseHelper myDB = new MyDatabaseHelper(TambahActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        etnama = findViewById(R.id.et_nama);
        etnomor = findViewById(R.id.et_nomor);
        etklub = findViewById(R.id.et_club);
        btn_simpan = findViewById(R.id.btn_simpan);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama, nomor, klub;

                nama = etnama.getText().toString();
                nomor = etnomor.getText().toString();
                klub = etklub.getText().toString();

                if(nama.trim().equals(""))
                {
                    etnama.setError("Nama Harus Diisi");
                }
                if(nomor.trim().equals(""))
                {
                    etnomor.setError("Nomor Punggung Harus Diisi");
                }
                if(klub.trim().equals(""))
                {
                    etklub.setError("Nama Club Harus Diisi");
                }
                else {
                    long eks = myDB.TambahPlayer(nama, nomor, klub);
                    if (eks == -1){
                        Toast.makeText(TambahActivity.this, "Gagal Menambah Data",Toast.LENGTH_LONG).show();
                        etnama.requestFocus();
                    }
                    else
                    {
                        Toast.makeText(TambahActivity.this, "Berhasil Menambah Data", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }
        });

    }
}