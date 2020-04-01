package com.example.chatfirebase;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HolderMensajes extends RecyclerView.ViewHolder {

    private TextView textViewNombreUsuariJ;
    private TextView textViewMensajeJ;
    private TextView textViewHoraJ;
    private ImageView imagenSmsJ;

    public TextView getTextViewNombreUsuariJ() {
        return textViewNombreUsuariJ;
    }

    public void setTextViewNombreUsuariJ(TextView textViewNombreUsuariJ) {
        this.textViewNombreUsuariJ = textViewNombreUsuariJ;
    }

    public TextView getTextViewMensajeJ() {
        return textViewMensajeJ;
    }

    public void setTextViewMensajeJ(TextView textViewMensajeJ) {
        this.textViewMensajeJ = textViewMensajeJ;
    }

    public TextView getTextViewHoraJ() {
        return textViewHoraJ;
    }

    public void setTextViewHoraJ(TextView textViewHoraJ) {
        this.textViewHoraJ = textViewHoraJ;
    }

    public ImageView getImagenSmsJ() {
        return imagenSmsJ;
    }

    public void setImagenSmsJ(ImageView imagenSmsJ) {
        this.imagenSmsJ = imagenSmsJ;
    }

    public HolderMensajes(@NonNull View itemView) {
        super(itemView);
        textViewNombreUsuariJ= (TextView) itemView.findViewById(R.id.textViewNombreDiseno);
        textViewMensajeJ= (TextView) itemView.findViewById(R.id.textViewMensajeDiseno);
        textViewHoraJ=(TextView) itemView.findViewById(R.id.textViewHoraDiseno);
        imagenSmsJ=(ImageView)itemView.findViewById(R.id.imageViewPerfilDiseno);
    }
}
