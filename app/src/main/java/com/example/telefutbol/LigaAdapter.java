package com.example.telefutbol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.telefutbol.Entity.Liga;

import java.util.ArrayList;
import java.util.List;

public class LigaAdapter extends RecyclerView.Adapter<LigaAdapter.LigaViewHolder>{
    private List<Liga> listaLigas;
    private Context context;

    public LigaAdapter(List<Liga> listaLigas) {
        this.listaLigas = listaLigas != null ? listaLigas : new ArrayList<>();
    }

    @NonNull
    @Override
    public LigaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_liga, parent, false);
        return new LigaViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull LigaViewHolder holder, int position) {
        Liga liga = listaLigas.get(position);
        holder.liga = liga;

        TextView nombreDeporte = holder.itemView.findViewById(R.id.nombreDeporte);
        TextView idLiga = holder.itemView.findViewById(R.id.idLiga);
        TextView nombreLiga = holder.itemView.findViewById(R.id.nombreLiga);
        TextView nombreLigaAlt = holder.itemView.findViewById(R.id.nombreLigaAlt);

        nombreDeporte.setText(liga.getNombreDeporte());
        idLiga.setText(liga.getId());
        nombreLiga.setText(liga.getNombreLiga());
        if (!liga.getNombreLigaAlt().isEmpty()){
            nombreLigaAlt.setText(liga.getNombreLigaAlt());
        }
        else {
            nombreLigaAlt.setText("-");
        }

    }

    @Override
    public int getItemCount() {
        return listaLigas.size();
    }


    public List<Liga> getListaLigas() {
        return listaLigas;
    }

    public void setListaLigas(List<Liga> listaLigas) {
        this.listaLigas = listaLigas;

    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public class LigaViewHolder extends RecyclerView.ViewHolder {

        Liga liga;

        public LigaViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
