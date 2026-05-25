package messagecommands.commands;

import messagecommands.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class TestMessageCommand implements Command {
    static final String ID = "test";

    public String getName() {
        return ID;
    }

    public void execute(MessageReceivedEvent event, String[] args) {
        event.getChannel().sendMessage("Servidor en funcionamiento.").queue();
    }
}
