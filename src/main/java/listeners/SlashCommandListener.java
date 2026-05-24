package listeners;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import slashcommands.SlashCommandHandler;

public class SlashCommandListener extends ListenerAdapter {
    SlashCommandHandler ch = new SlashCommandHandler();

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        ch.executeCommand(event);
    }
}
