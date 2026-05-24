package commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Test implements Command {
    public void execute(MessageReceivedEvent event, String[] args) {
        event.getChannel().sendMessage("Servidor en funcionamiento.").queue();
    }
}
