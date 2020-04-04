package com.example.chatfirebase.Modelo;

public class ModeloMensaje {
    private String mensajeU;
    private String urlImagenMensaje;
    private String nombreU;
    private String fotoPerfil;
    private String horaU;
    private String tipoMensaje;

    public ModeloMensaje() {
    }

    public ModeloMensaje(String mensajeU, String nombreU, String horaU, String imagen) {
        this.mensajeU = mensajeU;
        this.nombreU = nombreU;
        this.horaU = horaU;
        this.urlImagenMensaje = imagen;
    }

    public ModeloMensaje(String mensajeU, String urlImagenMensaje, String nombreU, String fotoPerfil, String horaU, String tipoMensaje) {
        this.mensajeU = mensajeU;
        this.urlImagenMensaje = urlImagenMensaje;
        this.nombreU = nombreU;
        this.fotoPerfil = fotoPerfil;
        this.horaU = horaU;
        this.tipoMensaje = tipoMensaje;
    }

    public String getMensajeU() {
        return mensajeU;
    }

    public void setMensajeU(String mensajeU) {
        this.mensajeU = mensajeU;
    }

    public String getUrlImagenMensaje() {
        return urlImagenMensaje;
    }

    public void setUrlImagenMensaje(String urlImagenMensaje) {
        this.urlImagenMensaje = urlImagenMensaje;
    }

    public String getNombreU() {
        return nombreU;
    }

    public void setNombreU(String nombreU) {
        this.nombreU = nombreU;
    }

    public String getHoraU() {
        return horaU;
    }

    public void setHoraU(String horaU) {
        this.horaU = horaU;
    }

    public String getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}
