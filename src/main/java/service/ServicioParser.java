package service;

import model.DatosVersion;
import model.Fecha;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class ServicioParser {
    public void cargarDatos(HashMap<String, DatosVersion> datos) {
        try (BufferedReader br = new BufferedReader(new FileReader("datos.csv"))) {
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
        } catch (Exception ignored) {}
    }
}
