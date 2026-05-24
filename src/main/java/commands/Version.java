package commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import service.VersionService;

public class Version implements Command {
    VersionService vs;

    Version() {
        vs = new VersionService();
    }

    public void execute(MessageReceivedEvent event, String[] args) {
        if (args.length > 0) {
            String version = args[0];

            VersionService vs = new VersionService();
            String respuesta = vs.buildVersionMessage(version);

            if (respuesta != null) {
                event.getChannel().sendMessage(respuesta).queue();
            } else {
                event.getChannel().sendMessage("No hay información sobre esa version.").queue();
            }
        }
    }
}
