package com.opitassy.footballplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterFootballPlayer extends RecyclerView.Adapter<AdapterFootballPlayer.ViewHolderPlayer>{
    private Context ctx;
    private ArrayList arrNama, arrNomor, arrKlub;

    public AdapterFootballPlayer(@NonNull Context ctx, ArrayList arrNama, ArrayList arrNomor, ArrayList arrKlub) {
        this.ctx = ctx;
        this.arrNama = arrNama;
        this.arrNomor = arrNomor;
        this.arrKlub = arrKlub;
    }

    @NonNull
    @Override
    public ViewHolderPlayer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(ctx).inflate(R.layout.listitemview, parent, false);
        return new ViewHolderPlayer(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPlayer holder, int position) {
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
        private TextView tvnama, tvnomor, tvklub;
        public ViewHolderPlayer(@NonNull View itemView) {
            super(itemView);
            tvnama = itemView.findViewById(R.id.tv_nama);
            tvnomor = itemView.findViewById(R.id.tv_nomor);
            tvklub = itemView.findViewById(R.id.tv_club);


        }
    }
}
