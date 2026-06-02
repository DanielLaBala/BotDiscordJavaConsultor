package slashcommands.commands;

import model.MensajePersonalizado;
import model.SlashCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.IntegrationType;
import net.dv8tion.jda.api.interactions.InteractionContextType;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import service.CreacionUsoMensajesService;
import service.MessageService;

public class EliminarMensajeSlashCommand implements SlashCommand {
    public static final String ID = "borrar";
    public static final String DESCRIPTION = "Borras un mensaje personalizado creado anteriormente en la sesion actual con el comando 'crear'.";

    MessageService messageService;
    CreacionUsoMensajesService creacionUsoMensajesService;

    public EliminarMensajeSlashCommand(MessageService messageService, CreacionUsoMensajesService creacionUsoMensajesService) {
        this.messageService = messageService;
        this.creacionUsoMensajesService = creacionUsoMensajesService;
    }

    @Override
    public String getName() {
        return ID;
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash(ID, DESCRIPTION)
                .addOption(OptionType.STRING, "id", "id", true)
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

        if (idOP == null) {
            event.reply("No se ha especificado un identificador.").setEphemeral(true).queue();
            return;
        }

        String id = idOP.getAsString();

        boolean res = creacionUsoMensajesService.delete(event, id);

        if (!res) {
            messageService.replyEphemeral(event, "No hay ningun mensaje especificado a ese identificador en la sesion actual o no te pertenece.");
        } else {
            messageService.replyEphemeral(event, "Se ha borrado el mensaje personalizado especificado.");
        }
    }
}
