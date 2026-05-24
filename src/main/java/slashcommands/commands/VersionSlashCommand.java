package slashcommands.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.IntegrationType;
import net.dv8tion.jda.api.interactions.InteractionContextType;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import service.VersionService;
import slashcommands.SlashCommand;

import java.util.Objects;

public class VersionSlashCommand implements SlashCommand {
    VersionService vs;

    public VersionSlashCommand() {
        vs = new VersionService();
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash("version", "Consultar informacion sobre una version de Genshin Impact.")
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
