package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DatosVersion {
    public String version;
    public Fecha fechaLanzamiento;
    public Fecha fechaDirecto;

    public DatosVersion(String version, Fecha fechaLanzamiento, Fecha fechaDirecto) {
        this.version = version;
        this.fechaLanzamiento = fechaLanzamiento;
        this.fechaDirecto = fechaDirecto;
    }

    public String getFechaLanzamientoHumana() {
        return new Fecha(fechaLanzamiento.day, fechaLanzamiento.month, fechaLanzamiento.year).toStringHuman();
    }

    public String getFechaDirectoHumana() {
        return new Fecha(fechaDirecto.day, fechaDirecto.month, fechaDirecto.year).toStringHuman();
    }

    public long getDiasFaltantesLanzamiento() {
        LocalDate now = LocalDate.now();
        LocalDate target = LocalDate.of(fechaLanzamiento.year, fechaLanzamiento.month, fechaLanzamiento.day);

        return ChronoUnit.DAYS.between(now, target);
    }

    public long getDiasFaltantesDirecto() {
        LocalDate now = LocalDate.now();
        LocalDate target = LocalDate.of(fechaDirecto.year, fechaDirecto.month, fechaDirecto.day);

        return ChronoUnit.DAYS.between(now, target);
    }
}