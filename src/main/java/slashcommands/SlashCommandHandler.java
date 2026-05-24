package slashcommands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import slashcommands.commands.FranBanSlashCommand;
import slashcommands.commands.VersionSlashCommand;

import java.util.HashMap;
import java.util.List;

public class SlashCommandHandler {
    HashMap<String, SlashCommand> commands;

    SlashCommandRegistry scr = new SlashCommandRegistry();

    public SlashCommandHandler() {
        commands = new HashMap<>();
        // Cambiar carga por reflexion mejor
//        commands.put(VersionSlashCommand.id, new VersionSlashCommand());
//        commands.put(FranBanSlashCommand.id, new FranBanSlashCommand());

        List<SlashCommand> commandList = scr.getCommands();

        for (SlashCommand command : commandList) {
            commands.put(command.getName(), command);
        }
    }

    public void executeCommand(SlashCommandInteractionEvent event) {
        SlashCommand trueCommand = commands.get(event.getName().toLowerCase());

        if (trueCommand != null) {
            trueCommand.execute(event);
        }
    }
}
