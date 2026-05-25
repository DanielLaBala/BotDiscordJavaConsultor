package messagecommands.commands;

import messagecommands.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import service.VersionService.BuildVersionMessageService;

public class VersionMessageCommand implements Command {
    static final String ID = "version";

    BuildVersionMessageService vs;

    public VersionMessageCommand() {
        vs = new BuildVersionMessageService();
    }

    @Override
    public String getName() {
        return ID;
    }

    public void execute(MessageReceivedEvent event, String[] args) {
        if (args.length > 0) {
            String version = args[0];

            BuildVersionMessageService vs = new BuildVersionMessageService();
            String respuesta = vs.buildVersionMessage(version);

            if (respuesta != null) {
                event.getChannel().sendMessage(respuesta).queue();
            } else {
                event.getChannel().sendMessage("No hay información sobre esa version.").queue();
            }
        }
    }
}
