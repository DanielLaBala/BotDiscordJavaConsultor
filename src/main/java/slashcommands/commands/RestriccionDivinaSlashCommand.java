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

public class RestriccionDivinaSlashCommand implements SlashCommand {
    public static final String id = "restricciondivina";
    public static final String description = "Devuelve los dias restantes de la restriccion divina de daniel_mui.";

    @Override
    public String getName() {
        return id;
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash(id, description)
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
        LocalDate target = LocalDate.of(2026, 7, 1);

        event.replyEmbeds(new EmbedBuilder()
                .setDescription("Faltan **" + ChronoUnit.DAYS.between(now, target) +
                        "** dias para el cumplimiento de la ***RESTRICCION DIVINA*** de *daniel_mui*.")
                .setImage("https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExMDBqbGQ3a3dvMXRybHN4cTZvamhvdmRkZjc5MXg0bDA1YmV4NnFvMyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/iVvHNvjKltGvtthDz5/giphy.gif")
                .build()
        ).queue();
    }
}
