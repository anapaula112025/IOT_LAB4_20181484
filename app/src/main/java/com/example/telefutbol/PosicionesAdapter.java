package com.example.telefutbol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.telefutbol.Entity.Liga;
import com.example.telefutbol.Entity.Posiciones;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PosicionesAdapter extends RecyclerView.Adapter<PosicionesAdapter.PosicionesViewHolder>{
    private List<Posiciones> listaPosiciones;
    private Context context;

    public PosicionesAdapter(List<Posiciones> listaPosiciones) {
        this.listaPosiciones = listaPosiciones != null ? listaPosiciones : new ArrayList<>();
    }

    @NonNull
    @Override
    public PosicionesAdapter.PosicionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_posiciones, parent, false);
        return new PosicionesAdapter.PosicionesViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PosicionesAdapter.PosicionesViewHolder holder, int position) {
        Posiciones posiciones = listaPosiciones.get(position);
        holder.posiciones = posiciones;

        TextView nombreEquipo = holder.itemView.findViewById(R.id.nombreEquipo);
        TextView ranking = holder.itemView.findViewById(R.id.ranking);
        TextView nombreVED = holder.itemView.findViewById(R.id.nombreVED);
        TextView nombreGoles = holder.itemView.findViewById(R.id.nombreGoles);
        ImageView imagenBadge = holder.itemView.findViewById(R.id.imagenBadge);

        nombreEquipo.setText(posiciones.getNombre());
        ranking.setText(posiciones.getRanking());
        String textolargo1 = posiciones.getGanados()+"/"+posiciones.getEmpatados()+"/"+posiciones.getPerdidos();
        nombreVED.setText(textolargo1);
        String textolargo2 = posiciones.getAnotados()+"/"+posiciones.getConcedidos()+"/"+posiciones.getDiferencias();
        nombreGoles.setText(textolargo2);
        // Usar Picasso para cargar la imagen desde la URL: sugerencia COPILOT
        Picasso.get()
                .load(posiciones.getBadge())
                .into(imagenBadge);
    }

    @Override
    public int getItemCount() {
        return listaPosiciones.size();
    }

    public class PosicionesViewHolder extends RecyclerView.ViewHolder {

        Posiciones posiciones;

        public PosicionesViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
