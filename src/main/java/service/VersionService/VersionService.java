package service.VersionService;

import dao.DAODatosVersion;
import model.DatosVersion;

public class VersionService {
    DAODatosVersion daoDatosVersion;

    public VersionService() {
        daoDatosVersion = new DAODatosVersion();
    }

    public DatosVersion getDatos(String numVersion) {
        return daoDatosVersion.getDatos(numVersion);
    }
}