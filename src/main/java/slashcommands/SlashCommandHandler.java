package slashcommands;

import model.SlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.HashMap;
import java.util.List;

public class SlashCommandHandler {
    HashMap<String, SlashCommand> commands;

    SlashCommandRegistry scr;

    public SlashCommandHandler(SlashCommandRegistry scr) {
        commands = new HashMap<>();

        this.scr = scr;

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
