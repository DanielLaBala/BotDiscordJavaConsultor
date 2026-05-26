package service.VersionService;

import dao.DAODatosVersion;
import model.DatosVersion;

public class VersionService {
    DAODatosVersion daoDatosVersion;

    public VersionService(DAODatosVersion daoDatosVersion) {
        this.daoDatosVersion = daoDatosVersion;
    }

    public DatosVersion getDatos(String numVersion) {
        return daoDatosVersion.getDatos(numVersion);
    }
}