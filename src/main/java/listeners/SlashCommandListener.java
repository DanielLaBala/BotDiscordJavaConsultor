package listeners;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import service.VersionService;

import java.util.Objects;

public class SlashCommandListener extends ListenerAdapter {
    VersionService vs;

    public SlashCommandListener() {
        vs = new VersionService();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("version")) {
            String version = Objects.requireNonNull(event.getOption("n_version")).getAsString();

            String respuesta = vs.buildVersionMessage(version);

            if (respuesta != null) {
                event.reply(respuesta).queue(); // .setEphemeral(true)
            } else {
                event.reply("No hay informacion sobre esa version.").setEphemeral(true).queue();
            }
        }
    }
}
