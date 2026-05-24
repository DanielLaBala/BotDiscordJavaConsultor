package slashcommands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import slashcommands.commands.FranBanSlashCommand;
import slashcommands.commands.VersionSlashCommand;

import java.util.HashMap;

public class SlashCommandHandler {
    HashMap<String, SlashCommand> commands;

    public SlashCommandHandler() {
        commands = new HashMap<>();
        // Cambiar carga por reflexion mejor
        commands.put("version", new VersionSlashCommand());
        commands.put("franban", new FranBanSlashCommand());
    }

    public void executeCommand(SlashCommandInteractionEvent event) {
        SlashCommand trueCommand = commands.get(event.getName().toLowerCase());

        if (trueCommand != null) {
            trueCommand.execute(event);
        }
    }
}
