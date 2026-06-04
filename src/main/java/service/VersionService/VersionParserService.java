package service.VersionService;

import model.DatosVersion;
import model.Fecha;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class VersionParserService {
    public void cargarDatos(HashMap<String, DatosVersion> datos) {

        BufferedReader br = null;

        if (Files.exists(Paths.get("datos.csv"))) {
            try {
                br = new BufferedReader(new FileReader("datos.csv"));
            } catch (FileNotFoundException ignored) {}
        } else { // Si no existe el archivo de versiones en el directorio del ejecutable se usa el de resources, asi le damos autonomia y en el peor de los casos tenemos ese por si acaso
            InputStream contenido = VersionParserService.class.getResourceAsStream("/datos.csv");

            if (contenido == null) throw new RuntimeException("No existe datos.csv en los resources.");

            br = new BufferedReader(new InputStreamReader(contenido));
        }

        try {
            while (br.ready()) {
                String linea = br.readLine();
                String[] campos = linea.split(",");

                String version = campos[0];

                int dayL = Integer.parseInt(campos[1]);
                int monthL = Integer.parseInt(campos[2]);
                int yearL = Integer.parseInt(campos[3]);

                Fecha fechaLanzamiento = new Fecha(dayL, monthL, yearL);

                int dayD = Integer.parseInt(campos[4]);
                int monthD = Integer.parseInt(campos[5]);
                int yearD = Integer.parseInt(campos[6]);

                Fecha fechaDirecto = new Fecha(dayD, monthD, yearD);

                datos.put(version, new DatosVersion(version, fechaLanzamiento, fechaDirecto));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
