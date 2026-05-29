package slashcommands.commands;

import model.SlashCommand;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.IntegrationType;
import net.dv8tion.jda.api.interactions.InteractionContextType;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import service.CreacionUsoMensajesService;
import service.MessageService;
import service.PropertiesService;

public class CrearMensajeSlashCommand implements SlashCommand {
    public static final String ID = "crear";
    public static final String DESCRIPTION = "Crea un mensaje personalizado en la sesion actual que podras usar despues con el comando 'usar'.";

    MessageService messageService;
    CreacionUsoMensajesService creacionUsoMensajesService;
    PropertiesService propertiesService;

    public CrearMensajeSlashCommand(MessageService messageService, CreacionUsoMensajesService creacionUsoMensajesService, PropertiesService propertiesService) {
        this.messageService = messageService;
        this.creacionUsoMensajesService = creacionUsoMensajesService;
        this.propertiesService = propertiesService;
    }

    @Override
    public String getName() {
        return ID;
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash(ID, DESCRIPTION)
                .addOption(OptionType.STRING, "id", "id", true)
                .addOption(OptionType.STRING, "mensaje", "mensaje", true)
                .addOption(OptionType.STRING, "enlace", "enlace a un archivo multimedia que pueda ser visualizado por Discord", false)
                .addOption(OptionType.ATTACHMENT, "archivo", "añade una imagen adjunta al mensaje", false)
                .setIntegrationTypes(IntegrationType.USER_INSTALL)
                .setContexts(
                        InteractionContextType.GUILD,
                        InteractionContextType.BOT_DM,
                        InteractionContextType.PRIVATE_CHANNEL
                );
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        OptionMapping idOP = event.getOption("id");
        OptionMapping mensajeOP = event.getOption("mensaje");
        OptionMapping enlaceOP = event.getOption("enlace");
        OptionMapping archivoOP = event.getOption("archivo");

        if (idOP == null || mensajeOP == null) {
            messageService.replyEphemeral(event,"No se han especificado todos los parametros.");
            return;
        }

        String id = idOP.getAsString();
        String mensaje = mensajeOP.getAsString();
        String enlace;
        String archivo;

        if (enlaceOP != null && archivoOP != null) {
            messageService.replyEphemeral(event,"Solo puedes enviar una imagen adjunta o un enlace, no puedes mandar ambas.");
            return;
        }

        if (enlaceOP == null) {
            enlace = null;
        } else {
            enlace = enlaceOP.getAsString();
        }

        if (archivoOP == null) {
            archivo = null;
        } else {
            Message.Attachment archivoAux = archivoOP.getAsAttachment();

            if (!archivoAux.isImage()) {
                messageService.replyEphemeral(event, "El archivo asociado al comando no es una imagen");
                return;
            }

            if (archivoAux.getSize() > propertiesService.getMaxFileMB() * 1024 * 1024) { // 20MB
                messageService.replyEphemeral(event, "La imagen asociada supera el limite maximo de 20MB.");
                return;
            }

            archivo = archivoAux.getUrl();
        }

        String enlaceDefinitivo = null;

        if (enlace != null) {
            enlaceDefinitivo = enlace;
        }

        if (archivo != null) {
            enlaceDefinitivo = archivo;
        }

        boolean respuesta = creacionUsoMensajesService.add(event, id, mensaje, enlaceDefinitivo);

        if (respuesta) {
            messageService.replyEphemeral(event, "Se ha añadido el mensaje indicado a la sesion actual.");
        } else {
            messageService.replyEphemeral(event, "Ya existe un mensaje con ese identificador.");
        }
    }
}
