package com.example.el_cairo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BusquedaAdapter extends RecyclerView.Adapter<BusquedaAdapter.ClienteViewHolder> {

    private List<String> clientes;
    private LayoutInflater inflater;

    public BusquedaAdapter(Context context, List<String> clientes) {
        this.clientes = clientes;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_buscar, parent, false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        String cliente = clientes.get(position);
        holder.tvCliente.setText("Cliente: " + cliente);
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }

    // actualizando la lista de clentes:
    public void actualizarLista(List<String> nuevaLista) {
        this.clientes = nuevaLista;
        notifyDataSetChanged();
    }

    static class ClienteViewHolder extends RecyclerView.ViewHolder {
        TextView tvCliente;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCliente = itemView.findViewById(R.id.tvCliente);
        }
    }
}
