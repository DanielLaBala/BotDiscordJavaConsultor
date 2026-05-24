package service;

import dao.DAODatosVersion;
import model.DatosVersion;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ServicioDatos {
    DAODatosVersion daoDatosVersion;

    public ServicioDatos() {
        daoDatosVersion = new DAODatosVersion();
    }

    public DatosVersion getDatos(String numVersion) {
        return daoDatosVersion.getDatos(numVersion);
    }
}