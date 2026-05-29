package slashcommands.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.IntegrationType;
import net.dv8tion.jda.api.interactions.InteractionContextType;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import service.MessageService;
import service.VersionService.BuildVersionMessageService;
import model.SlashCommand;

import java.util.Objects;

public class VersionSlashCommand implements SlashCommand {
    public static final String id = "version";
    public static final String description = "Consultar informacion sobre una version de Genshin Impact.";

    BuildVersionMessageService vs;
    MessageService ms;

    public VersionSlashCommand(MessageService messageService, BuildVersionMessageService vs) {
        this.vs = vs;
        ms = messageService;
    }

    @Override
    public String getName() {
        return id;
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash(id, description)
                .addOption(OptionType.STRING, "n_version", "numero de la version", true)
                .setIntegrationTypes(IntegrationType.USER_INSTALL)
                .setContexts(
                        InteractionContextType.GUILD,
                        InteractionContextType.BOT_DM,
                        InteractionContextType.PRIVATE_CHANNEL
                );
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String version = Objects.requireNonNull(event.getOption("n_version")).getAsString();

        String respuesta = vs.buildVersionMessage(version);

        if (respuesta != null) {
            event.reply(respuesta).queue(); // .setEphemeral(true)
        } else {
            event.reply("No hay informacion sobre esa version.").setEphemeral(true).queue();
        }
    }
}
