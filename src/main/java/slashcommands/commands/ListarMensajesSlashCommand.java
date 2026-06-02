package slashcommands.commands;

import model.SlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.IntegrationType;
import net.dv8tion.jda.api.interactions.InteractionContextType;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import service.CreacionUsoMensajesService;
import service.MessageService;

public class ListarMensajesSlashCommand implements SlashCommand {
    public static final String ID = "listar";
    public static final String DESCRIPTION = "Listas los mensajes personalizados creados anteriormente en la sesion actual con el comando 'crear'.";

    MessageService messageService;
    CreacionUsoMensajesService creacionUsoMensajesService;

    public ListarMensajesSlashCommand(MessageService messageService, CreacionUsoMensajesService creacionUsoMensajesService) {
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
                .setIntegrationTypes(IntegrationType.USER_INSTALL)
                .setContexts(
                        InteractionContextType.GUILD,
                        InteractionContextType.BOT_DM,
                        InteractionContextType.PRIVATE_CHANNEL
                );
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String res = creacionUsoMensajesService.list();

        if (res.isEmpty()) {
            messageService.replyEphemeral(event, "Actualmente no hay ningun mensaje personalizado creado.");
        } else {
            messageService.reply(event, res);
        }
    }
}
