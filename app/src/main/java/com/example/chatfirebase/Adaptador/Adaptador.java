package com.example.chatfirebase.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chatfirebase.Holder.HolderMensajes;
import com.example.chatfirebase.Modelo.ModeloMensaje;
import com.example.chatfirebase.R;


import java.util.ArrayList;
import java.util.List;

public class Adaptador extends RecyclerView.Adapter<HolderMensajes> {

    //generar lista desde el adaptador
    private List<ModeloMensaje> listMensaje = new ArrayList<>();
    private Context contexto;
        //constructor para traer el contexto
    public Adaptador(Context contexto) {
        this.contexto = contexto;
    }
    //metodo para agregar mensajes al adpter enviando un objeto de modeloMensaje
    public void adaptarMensajes(ModeloMensaje m){
        //agregar el objeto a la lista
        listMensaje.add(m);
        //metodo para avisar cuando se crea un nuevo mensaje
        notifyItemInserted(listMensaje.size());
    }
    @Override
    public HolderMensajes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(contexto).inflate(R.layout.activity_diseno_mensajes,parent,false);
        //retorna la vista
        return new HolderMensajes(v);
    }
    @Override
    public void onBindViewHolder(@NonNull HolderMensajes holder, int position) {
        //setear el mensaje desde el holder mensaje
        holder.getNombreUsuario().setText(listMensaje.get(position).getNombreU());
        holder.getTextoMensajes().setText(listMensaje.get(position).getMensajeU());
        holder.getHora().setText(listMensaje.get(position).getHoraU());
        Glide.with(contexto).load(listMensaje.get(position).getUrlImagenMensaje()).into(holder.getMensajeDEimagen());
        if (listMensaje.get(position).getTipoMensaje()=="2"){
            holder.getTextoMensajes().setVisibility(View.GONE);
            holder.getMensajeDEimagen().setVisibility(View.VISIBLE);


      }else if (listMensaje.get(position).getTipoMensaje()=="1"){
            holder.getTextoMensajes().setVisibility(View.VISIBLE);
            holder.getMensajeDEimagen().setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return listMensaje.size();
    }
}
