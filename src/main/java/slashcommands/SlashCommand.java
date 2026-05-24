package slashcommands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface SlashCommand {
    void execute(MessageReceivedEvent event, String[] args);
}
