package listeners;

import messagecommands.CommandHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
    CommandHandler ch;

    public MessageListener(CommandHandler commandHandler) {
        ch = commandHandler;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        String mensaje = event.getMessage().getContentRaw().toLowerCase();

        if (mensaje.startsWith("!")) {
            ch.executeCommand(event, mensaje.replace('!', ' ').strip());
        }
    }
}
