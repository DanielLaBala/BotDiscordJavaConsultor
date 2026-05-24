package deprecated;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.IntegrationType;
import net.dv8tion.jda.api.interactions.InteractionContextType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class Aplicacion extends ListenerAdapter {

    public static void main(String[] args) throws Exception {
        JDABuilder.createDefault("")
                .addEventListeners(new Aplicacion())
                .build()
                .awaitReady()
                .updateCommands().addCommands(
                        Commands.slash("xdmagico", "Saluda")
                                .setIntegrationTypes(IntegrationType.USER_INSTALL)
                                .setContexts(
                                        InteractionContextType.GUILD,
                                        InteractionContextType.BOT_DM,
                                        InteractionContextType.PRIVATE_CHANNEL
                                )
                ).queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("xdmagico")) {
            event.reply("hola").queue();
        }
    }
}