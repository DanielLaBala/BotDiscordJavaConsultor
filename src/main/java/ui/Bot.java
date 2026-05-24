package ui;

import listeners.MessageListener;
import listeners.SlashCommandListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.IntegrationType;
import net.dv8tion.jda.api.interactions.InteractionContextType;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Bot extends ListenerAdapter {
    static Properties p = new Properties();

    static {
        try {
            InputStream input = Bot.class.getResourceAsStream("/bot.properties");

            if (input != null) {
                p.load(input);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        JDABuilder.createDefault(p.getProperty("discord.token"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new SlashCommandListener(), new MessageListener())
                .build()
                .awaitReady()
                .updateCommands().addCommands(
                        Commands.slash("version", "Consultar informacion sobre una version de Genshin Impact.")
                                .addOption(OptionType.STRING, "n_version", "numero de la version", true)
                                .setIntegrationTypes(IntegrationType.USER_INSTALL)
                                .setContexts(
                                        InteractionContextType.GUILD,
                                        InteractionContextType.BOT_DM,
                                        InteractionContextType.PRIVATE_CHANNEL
                                ),
                        Commands.slash("franban", "Devuelve los dias restantes del baneo de insano_tilin123.")
                                .setIntegrationTypes(IntegrationType.USER_INSTALL)
                                .setContexts(
                                        InteractionContextType.GUILD,
                                        InteractionContextType.BOT_DM,
                                        InteractionContextType.PRIVATE_CHANNEL
                                )

                ).queue();
    }
}