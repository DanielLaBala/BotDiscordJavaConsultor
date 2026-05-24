package slashcommands.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.IntegrationType;
import net.dv8tion.jda.api.interactions.InteractionContextType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import slashcommands.SlashCommand;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FranBanSlashCommand implements SlashCommand {
    @Override
    public CommandData getCommandData() {
        return Commands.slash("franban", "Devuelve los dias restantes del baneo de insano_tilin123.")
                .setIntegrationTypes(IntegrationType.USER_INSTALL)
                .setContexts(
                        InteractionContextType.GUILD,
                        InteractionContextType.BOT_DM,
                        InteractionContextType.PRIVATE_CHANNEL
                );
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        LocalDate now = LocalDate.now();
        LocalDate target = LocalDate.of(2027, 3, 24);

        event.replyEmbeds(new EmbedBuilder()
                .setDescription("Faltan **" + ChronoUnit.DAYS.between(now, target) +
                        "** dias para la vuelta de *insano_tilin123*.")
                .setImage("https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExN2d1b2pqcHYwZ3ZuYTZhbWxidDFveTZubTh5NTVud284d3FzcXh3MSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/B4jfJqiIxvU08/giphy.gif")
                .build()
        ).queue();
    }
}
