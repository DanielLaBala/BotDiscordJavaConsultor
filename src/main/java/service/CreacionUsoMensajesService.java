package service;

import model.MensajePersonalizado;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.HashMap;
import java.util.Set;

public class CreacionUsoMensajesService {
    HashMap<String, MensajePersonalizado> mensajesGuardados;
    PropertiesService propertiesService;

    public CreacionUsoMensajesService(PropertiesService propertiesService) {
        mensajesGuardados = new HashMap<>();
        this.propertiesService = propertiesService;
    }

    public boolean delete(SlashCommandInteractionEvent event, String id) {
        if (mensajesGuardados.containsKey(id)) {
            if (event.getUser().getName().equals(mensajesGuardados.get(id).getCreador())) {
                mensajesGuardados.remove(id);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String list() { // todo mejorar bastante, de hecho si hay muchos comandos saldra un mensaje larguisimo, ahora mismo esta de debug practicamente
        String listado = "";

        for (MensajePersonalizado mensaje : mensajesGuardados.values()) {
            listado += "**ID:** " + mensaje.getId() + ", **CREADO POR:** " + mensaje.getCreador() + "\n";
        }

        if (!listado.equals("")) {
            listado = "**MENSAJES:** \n" + listado;
        }

        return listado;
    }

    public boolean add(SlashCommandInteractionEvent event, String id, String mensaje, String enlace) {
        if (mensajesGuardados.size() < propertiesService.getHashMapLimit()) {
            if (mensajesGuardados.containsKey(id)) {
                return false;
            }

            String creador = event.getUser().getName();

            MensajePersonalizado mensajePersonalizado = new MensajePersonalizado(id, mensaje, creador, enlace);

            mensajesGuardados.put(id, mensajePersonalizado);

            return true;
        } else {
            return false;
        }
    }

    public MensajePersonalizado read(String id) {
        if (!mensajesGuardados.containsKey(id)) {
            return null;
        }

        return mensajesGuardados.get(id);
    }
}
