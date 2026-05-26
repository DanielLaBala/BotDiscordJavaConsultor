package listeners;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import slashcommands.SlashCommandHandler;

public class SlashCommandListener extends ListenerAdapter {
    SlashCommandHandler sch;

    public SlashCommandListener(SlashCommandHandler sch) {
        this.sch = sch;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        sch.executeCommand(event);
    }
}
