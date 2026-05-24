package messagecommands;

import messagecommands.commands.TestMessageCommand;
import messagecommands.commands.VersionMessageCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.HashMap;

public class CommandHandler {
    HashMap<String, Command> commands;

    public CommandHandler() {
        commands = new HashMap<>();
        // Cambiar carga por reflexion mejor
        commands.put("test", new TestMessageCommand());
        commands.put("version", new VersionMessageCommand());
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
