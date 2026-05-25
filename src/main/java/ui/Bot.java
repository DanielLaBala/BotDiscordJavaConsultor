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
import slashcommands.PropertiesService;
import slashcommands.SlashCommandRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Bot extends ListenerAdapter {


    public static void main(String[] args) throws Exception {
        PropertiesService ps = new PropertiesService();

        JDABuilder.createDefault(ps.getDiscordToken())
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new SlashCommandListener(), new MessageListener())
                .build()
                .awaitReady()
                .updateCommands()
                .addCommands(new SlashCommandRegistry().getCommandData()) // Los comandos de aqui son los que haran que salgan y te habilitaran usarlos
                .queue();
    }
}