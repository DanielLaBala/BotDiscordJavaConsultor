package slashcommands.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.IntegrationType;
import net.dv8tion.jda.api.interactions.InteractionContextType;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import service.VersionService.BuildVersionMessageService;
import slashcommands.SlashCommand;

import java.util.Objects;

public class OperacionSlashCommand implements SlashCommand {
    public static final String id = "operacion";
    public static final String description = "Realiza operaciones matematicas.";

    @Override
    public String getName() {
        return id;
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash(id, description)
                .addOption(OptionType.STRING, "expresion", "expresión matematica", true)
                .setIntegrationTypes(IntegrationType.USER_INSTALL)
                .setContexts(
                        InteractionContextType.GUILD,
                        InteractionContextType.BOT_DM,
                        InteractionContextType.PRIVATE_CHANNEL
                );
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        OptionMapping expresionOM = event.getOption("expresion");

        if (expresionOM == null) { // Early return, recomendados
            event.reply("La expresión matematica ha sido nula o incorrecta.").setEphemeral(true).queue();
            return;
        }

        try {
            String expresion = expresionOM.getAsString();
            Expression e = new ExpressionBuilder(expresion).build();

            String resultado = String.valueOf(e.evaluate());

            event.reply("*(" + expresion + ")*" + " = **" + resultado + "**").queue(); // .setEphemeral(true)
        } catch (IllegalArgumentException e) {
            event.reply("La expresión matematica ha sido incorrecta.").setEphemeral(true).queue();
        } catch (ArithmeticException e) {
            event.reply("La expresión matematica ha sido incorrecta (posible division entre 0).").setEphemeral(true).queue();
        }
    }
}
