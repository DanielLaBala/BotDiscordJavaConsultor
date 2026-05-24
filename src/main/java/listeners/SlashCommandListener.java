package listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import service.VersionService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
        } else if (event.getName().equals("franban")) {
            LocalDate now = LocalDate.now();
            LocalDate target = LocalDate.of(2027, 3, 24);

            event.replyEmbeds(new EmbedBuilder()
                    .setDescription("Faltan **" + ChronoUnit.DAYS.between(now, target) +
                            "** dias para la vuelta de *insano_tilin123*.")
                    .setImage("https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExN2d1b2pqcHYwZ3ZuYTZhbWxidDFveTZubTh5NTVud284d3FzcXh3MSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/B4jfJqiIxvU08/giphy.gif")
                    .build()
            ).queue();
        }
    }
}
