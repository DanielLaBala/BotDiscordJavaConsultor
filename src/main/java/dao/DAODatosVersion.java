package dao;

import model.DatosVersion;
import service.ServicioParser;

import java.util.HashMap;

public class DAODatosVersion {
    private final HashMap<String, DatosVersion> registros = new HashMap<>(); // Carga al inicio de la aplicacion, no por cada consulta, lo cual no es del todo bueno pero vaya

    public DAODatosVersion() {
        ServicioParser sp = new ServicioParser();
        sp.cargarDatos(registros);
    }

    public HashMap<String, DatosVersion> getRegistros() {
        return registros;
    }

    public DatosVersion getDatos(String numVersion) {
        return registros.get(numVersion);
    }
}
