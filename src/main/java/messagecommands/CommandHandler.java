package messagecommands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CommandHandler {
    HashMap<String, Command> commands;

    CommandRegistry cr = new CommandRegistry();

    public CommandHandler() {
        commands = new HashMap<>();

        List<Command> commandList = cr.getCommands();

        for (Command command : commandList) {
            commands.put(command.getName(), command);
        }
    }

    public void executeCommand(MessageReceivedEvent event, String mensaje) {
        String[] argsAux = mensaje.split("\\s+");

        Command trueCommand = commands.get(argsAux[0]);

        String[] args = Arrays.copyOfRange(argsAux,1, argsAux.length);

        if (trueCommand != null) {
            trueCommand.execute(event, args);
        }
    }
}
