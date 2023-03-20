package com.opitassy.footballplayer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterFootballPlayer extends RecyclerView.Adapter<AdapterFootballPlayer.ViewHolderPlayer>{
    private Context ctx;
    private ArrayList arrid, arrNama, arrNomor, arrKlub;

    public AdapterFootballPlayer(@NonNull Context ctx,ArrayList arrid, ArrayList arrNama, ArrayList arrNomor, ArrayList arrKlub) {
        this.ctx = ctx;
        this.arrNama = arrNama;
        this.arrNomor = arrNomor;
        this.arrKlub = arrKlub;
        this.arrid = arrid;
    }

    @NonNull
    @Override
    public ViewHolderPlayer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(ctx).inflate(R.layout.listitemview, parent, false);
        return new ViewHolderPlayer(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPlayer holder, int position) {
        holder.tvid.setText(arrid.get(position).toString());
        holder.tvnama.setText(arrNama.get(position).toString());
        holder.tvnomor.setText(arrNomor.get(position).toString());
        holder.tvklub.setText(arrKlub.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return arrNama.size();
    }

    public class ViewHolderPlayer extends RecyclerView.ViewHolder
    {
        private TextView tvid, tvnama, tvnomor, tvklub;
        public ViewHolderPlayer(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.tv_id);
            tvnama = itemView.findViewById(R.id.tv_nama);
            tvnomor = itemView.findViewById(R.id.tv_nomor);
            tvklub = itemView.findViewById(R.id.tv_club);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("Anda memasuki daerah terlarang");
                    pesan.setMessage("Anda Memilih "+ tvnama.getText().toString() + " Pilih perintah yang anda inginkan");
                    pesan.setCancelable(true);
                    pesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent varIntent = new Intent(ctx, UbahActivity.class);
                            varIntent.putExtra("varID", tvid.getText().toString());
                            varIntent.putExtra("varnama", tvnama.getText().toString());
                            varIntent.putExtra("varnomor", tvnomor.getText().toString());
                            varIntent.putExtra("varklub", tvklub.getText().toString());

                            ctx.startActivity(varIntent);
                        }
                    });
                    pesan.setNegativeButton("Hapus ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyDatabaseHelper myDB = new MyDatabaseHelper(ctx);
                            long eksekusi = myDB.hapusPlayer(tvid.getText().toString());
                            if (eksekusi == -1)
                            {
                                Toast.makeText(ctx, "Gagal menghapus data!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(ctx, "Berhasil Menghapus data", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                ((MainActivity) ctx).onResume();
                            }
                        }
                    });
                    pesan.show();
                    return false;
                }
            });


        }
    }
}
