package com.example.telefutbol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.telefutbol.Entity.Posiciones;
import com.example.telefutbol.Entity.Resultados;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ResultadosAdapter extends RecyclerView.Adapter<ResultadosAdapter.ResultadosViewHolder>{
    private List<Resultados> listaResultados;
    private Context context;

    public ResultadosAdapter(List<Resultados> listaResultados) {
        this.listaResultados = listaResultados != null ? listaResultados : new ArrayList<>();
    }

    @NonNull
    @Override
    public ResultadosAdapter.ResultadosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resultados, parent, false);
        return new ResultadosAdapter.ResultadosViewHolder(inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull ResultadosAdapter.ResultadosViewHolder holder, int position) {
        Resultados resultados = listaResultados.get(position);
        holder.resultados = resultados;

        TextView nombre = holder.itemView.findViewById(R.id.nombre);
        TextView ronda = holder.itemView.findViewById(R.id.ronda);
        TextView eLocal = holder.itemView.findViewById(R.id.eLocal);
        TextView eVisitante = holder.itemView.findViewById(R.id.eVisitante);
        TextView resultado = holder.itemView.findViewById(R.id.resultado);
        TextView fecha = holder.itemView.findViewById(R.id.fecha);
        TextView espectadores = holder.itemView.findViewById(R.id.espectadores);
        ImageView logo = holder.itemView.findViewById(R.id.logo);

        nombre.setText(resultados.getNombre());
        ronda.setText(resultados.getRonda());
        eLocal.setText(resultados.geteLocal());
        eVisitante.setText(resultados.geteVisitante());
        String textolargo = resultados.getScoreLocal()+" - "+resultados.getScoreVisitante();
        resultado.setText(textolargo);
        fecha.setText(resultados.getFecha());
        if (resultados.getEspectadores() != null){
            espectadores.setText(resultados.getEspectadores());
        }
        else {
            espectadores.setText("-");
        }

        // Usar Picasso para cargar la imagen desde la URL: sugerencia COPILOT
        Picasso.get()
                .load(resultados.getLogo())
                .into(logo);
    }

    @Override
    public int getItemCount() {
        return listaResultados.size();
    }

    public class ResultadosViewHolder extends RecyclerView.ViewHolder {

        Resultados resultados;

        public ResultadosViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
