package com.example.el_cairo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RutaAdapter extends RecyclerView.Adapter<RutaAdapter.RutaViewHolder> {

    private List<String> rutas;
    private LayoutInflater inflater;

    public RutaAdapter(Context context, List<String> rutas) {
        this.rutas = rutas;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RutaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_ruta, parent, false);
        return new RutaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RutaViewHolder holder, int position) {
        String ruta = rutas.get(position);
        holder.tvRuta.setText("Ruta: " + ruta);
    }

    @Override
    public int getItemCount() {
        return rutas.size();
    }

    static class RutaViewHolder extends RecyclerView.ViewHolder {
        TextView tvRuta;

        RutaViewHolder(View itemView) {
            super(itemView);
            tvRuta = itemView.findViewById(R.id.tvRuta);
        }
    }
}
