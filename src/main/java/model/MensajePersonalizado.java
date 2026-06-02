package model;

import java.time.LocalDate;

public class MensajePersonalizado {
    String id;
    String mensaje;
    String creador;
    String mediaUrl;
    LocalDate fecha;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getCreador() {
        return creador;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public MensajePersonalizado(String id, String mensaje, String creador, String mediaUrl) {
        this.id = id;
        this.mensaje = mensaje;
        this.creador = creador;
        this.fecha = LocalDate.now();
        this.mediaUrl = mediaUrl;
    }
}
