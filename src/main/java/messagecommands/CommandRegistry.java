package messagecommands;

import messagecommands.commands.TestMessageCommand;
import messagecommands.commands.VersionMessageCommand;
import slashcommands.SlashCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandRegistry {
    private final List<Command> dataList;

    CommandRegistry() {
        dataList = new ArrayList<>();

        dataList.add(new TestMessageCommand());
        dataList.add(new VersionMessageCommand());
    }

    List<Command> getCommands() {
        return dataList;
    }
}
