package com.example.chatfirebase;

public class ModeloMensaje {
    private String mensajeU;
    private String nombreU;
    private String horaU;
    private String imagen;

    public ModeloMensaje() {
    }

    public ModeloMensaje(String mensajeU, String nombreU, String horaU, String imagen) {
        this.mensajeU = mensajeU;
        this.nombreU = nombreU;
        this.horaU = horaU;
        this.imagen = imagen;
    }

    public String getMensajeU() {
        return mensajeU;
    }

    public void setMensajeU(String mensajeU) {
        this.mensajeU = mensajeU;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
