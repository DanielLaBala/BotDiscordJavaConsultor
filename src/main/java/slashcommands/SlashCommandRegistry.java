package slashcommands;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import slashcommands.commands.FranBanSlashCommand;
import slashcommands.commands.VersionSlashCommand;

import java.util.ArrayList;
import java.util.List;

public class SlashCommandRegistry {
    private List<SlashCommand> dataList;

    public SlashCommandRegistry() {
        dataList = new ArrayList<>();

        // Meter reflexion
        dataList.add(new VersionSlashCommand());
        dataList.add(new FranBanSlashCommand());
    }

    public List<CommandData> getCommandData() {
        return dataList.stream().map(SlashCommand::getCommandData).toList();
    }

    public List<SlashCommand> getCommands() {
        return dataList;
    }
}
