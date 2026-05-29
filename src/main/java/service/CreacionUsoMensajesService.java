package service;

import model.MensajePersonalizado;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.HashMap;

public class CreacionUsoMensajesService {
    HashMap<String, MensajePersonalizado> mensajesGuardados;
    PropertiesService propertiesService;

    public CreacionUsoMensajesService(PropertiesService propertiesService) {
        mensajesGuardados = new HashMap<>();
        this.propertiesService = propertiesService;
    }

    public boolean add(SlashCommandInteractionEvent event, String id, String mensaje, String enlace) {
        if (mensajesGuardados.size() < propertiesService.getHashMapLimit()) {
            if (mensajesGuardados.containsKey(id)) {
                return false;
            }

            String creador = event.getUser().getName();

            MensajePersonalizado mensajePersonalizado = new MensajePersonalizado(mensaje, creador, enlace);

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
