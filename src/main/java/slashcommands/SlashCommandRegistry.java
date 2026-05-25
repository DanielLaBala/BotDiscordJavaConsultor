package slashcommands;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import slashcommands.commands.*;

import java.util.ArrayList;
import java.util.List;

public class SlashCommandRegistry {
    private final List<SlashCommand> dataList;

    public SlashCommandRegistry() {
        dataList = List.of(
            new VersionSlashCommand(),
            new FranBanSlashCommand(),
            new RestriccionDivinaSlashCommand(),
            new RandomSlashCommand(),
            new OperacionSlashCommand()
        );
    }

    public List<CommandData> getCommandData() {
        return dataList.stream().map(SlashCommand::getCommandData).toList(); // s -> s.getCommandData()
    }

    public List<SlashCommand> getCommands() {
        return dataList;
    }
}
