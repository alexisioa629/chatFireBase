package com.example.chatfirebase.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatfirebase.R;

public class HolderMensajes extends RecyclerView.ViewHolder {

    private TextView nombreUsuario;
    private TextView textoMensajes;
    private TextView hora;
    private ImageView mensajeDEimagen;
    private ImageView fotoPerfil;


    public HolderMensajes(@NonNull View itemView) {
        super(itemView);
        nombreUsuario = (TextView) itemView.findViewById(R.id.textViewNombreDiseno);
        textoMensajes = (TextView) itemView.findViewById(R.id.textViewMensajeDiseno);
        hora = (TextView) itemView.findViewById(R.id.textViewHoraDiseno);
        mensajeDEimagen = (ImageView) itemView.findViewById(R.id.imagenMensajeDiseno);
        fotoPerfil = (ImageView) itemView.findViewById(R.id.imageViewPerfilDiseno);
    }

    public TextView getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(TextView nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public TextView getTextoMensajes() {
        return textoMensajes;
    }

    public void setTextoMensajes(TextView textoMensajes) {
        this.textoMensajes = textoMensajes;
    }

    public TextView getHora() {
        return hora;
    }

    public void setHora(TextView hora) {
        this.hora = hora;
    }

    public ImageView getMensajeDEimagen() {
        return mensajeDEimagen;
    }

    public void setMensajeDEimagen(ImageView mensajeDEimagen) {
        this.mensajeDEimagen = mensajeDEimagen;
    }

    public ImageView getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(ImageView fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}