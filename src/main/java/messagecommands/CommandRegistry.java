package messagecommands;

import messagecommands.commands.TestMessageCommand;
import messagecommands.commands.VersionMessageCommand;
import model.Command;
import service.VersionService.BuildVersionMessageService;

import java.util.List;

public class CommandRegistry {
    private final List<Command> dataList;

    public CommandRegistry(BuildVersionMessageService buildVersionMessageService) {
        dataList = List.of(
            new TestMessageCommand(),
            new VersionMessageCommand(buildVersionMessageService)
        );
    }

    List<Command> getCommands() {
        return dataList;
    }
}
