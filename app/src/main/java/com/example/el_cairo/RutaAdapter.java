package com.example.el_cairo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RutaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_AGREGAR = 0;
    private static final int TYPE_RUTA = 1;

    private List<String> rutas;
    private Context context;

    public RutaAdapter(Context context, List<String> rutas) {
        this.context = context;
        this.rutas = rutas;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_AGREGAR : TYPE_RUTA;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_AGREGAR) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_agregar, parent, false);
            return new AgregarViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_ruta, parent, false);
            return new RutaViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RutaViewHolder) {
            String ruta = rutas.get(position - 1); // -1 porque el primer item es el botón +
            ((RutaViewHolder) holder).tvRuta.setText("Ruta: " + ruta);
        } else {
            holder.itemView.setOnClickListener(v -> {
                Toast.makeText(context, "Agregar nueva ruta", Toast.LENGTH_SHORT).show();
                // Aquí puedes abrir un formulario o dialog
            });
        }
    }

    @Override
    public int getItemCount() {
        return rutas.size() + 1; // +1 por el botón de agregar
    }

    static class RutaViewHolder extends RecyclerView.ViewHolder {
        TextView tvRuta;

        RutaViewHolder(View itemView) {
            super(itemView);
            tvRuta = itemView.findViewById(R.id.tvRuta);
        }
    }

    static class AgregarViewHolder extends RecyclerView.ViewHolder {
        AgregarViewHolder(View itemView) {
            super(itemView);
        }
    }
}
