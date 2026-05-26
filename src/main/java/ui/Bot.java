package ui;

import dao.DAODatosVersion;
import listeners.MessageListener;
import listeners.SlashCommandListener;
import messagecommands.CommandHandler;
import messagecommands.CommandRegistry;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import service.MessageService;
import service.PropertiesService;
import service.VersionService.BuildVersionMessageService;
import service.VersionService.VersionService;
import slashcommands.SlashCommandHandler;
import slashcommands.SlashCommandRegistry;

public class Bot extends ListenerAdapter {
    public static void main(String[] args) throws Exception {
        PropertiesService propertiesService = new PropertiesService();
        MessageService messageService = new MessageService();
        DAODatosVersion daoDatosVersion = new DAODatosVersion();
        VersionService versionService = new VersionService(daoDatosVersion);
        BuildVersionMessageService buildVersionMessageService = new BuildVersionMessageService(versionService);
        SlashCommandRegistry slashCommandRegistry = new SlashCommandRegistry(messageService, buildVersionMessageService);
        SlashCommandHandler slashCommandHandler = new SlashCommandHandler(slashCommandRegistry);
        CommandRegistry commandRegistry = new CommandRegistry(buildVersionMessageService);
        CommandHandler commandHandler = new CommandHandler(commandRegistry);

        JDABuilder.createDefault(propertiesService.getDiscordToken())
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new SlashCommandListener(slashCommandHandler), new MessageListener(commandHandler))
                .build()
                .awaitReady()
                .updateCommands()
                .addCommands(slashCommandRegistry.getCommandData()) // Los comandos de aqui son los que haran que salgan y te habilitaran usarlos
                .queue();
    }
}