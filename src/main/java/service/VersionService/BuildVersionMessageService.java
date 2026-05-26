package service.VersionService;

import model.DatosVersion;

public class BuildVersionMessageService {
    VersionService sd;

    public BuildVersionMessageService(VersionService versionService) {
        sd = versionService;
    }

    public String buildVersionMessage(String version) {

        DatosVersion dv = sd.getDatos(version);

        if (dv != null) {
            String mensaje = "# VERSION " + version + "\n" + "- *Fecha de salida:* **" + dv.getFechaLanzamientoHumana() + "**";

            long diasRestantesLanzamiento = dv.getDiasFaltantesLanzamiento();
            long diasRestantesDirecto = dv.getDiasFaltantesDirecto();

            if (diasRestantesLanzamiento == 0) {
                mensaje += " por lo que sale **hoy**.";
            } else if (diasRestantesLanzamiento > 0) {
                mensaje += " por lo que faltan **" + diasRestantesLanzamiento + "** dias.";
            } else {
                mensaje += " por lo salio hace **" + Math.abs(diasRestantesLanzamiento) + "** dias.";
            }

            mensaje += "\n" + "- *Fecha del directo*: **" + dv.getFechaDirectoHumana() + "**";

            if (diasRestantesDirecto == 0) {
                mensaje += " por lo que sale **hoy**.";
            } else if (diasRestantesDirecto > 0) {
                mensaje += " por lo que faltan **" + diasRestantesDirecto + "** dias.";
            } else {
                mensaje += " por lo salio hace **" + Math.abs(diasRestantesDirecto) + "** dias.";
            }

            return mensaje; // .setEphemeral(true)
        } else {
            return null;
        }
    }
}
