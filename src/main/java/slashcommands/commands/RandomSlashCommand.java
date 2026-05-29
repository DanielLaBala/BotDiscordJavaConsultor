package slashcommands.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.IntegrationType;
import net.dv8tion.jda.api.interactions.InteractionContextType;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import service.MessageService;
import model.SlashCommand;

public class RandomSlashCommand implements SlashCommand {
    public static final String id = "random";
    public static final String description = "Elegir una opcion aleatoria entre dos numeros incluidos.";

    MessageService ms;

    public RandomSlashCommand(MessageService messageService) {
        ms = messageService;
    }

    @Override
    public String getName() {
        return id;
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash(id, description)
                .addOption(OptionType.INTEGER, "n1", "numero 1", true)
                .addOption(OptionType.INTEGER, "n2", "numero 2", true)
                .setIntegrationTypes(IntegrationType.USER_INSTALL)
                .setContexts(
                        InteractionContextType.GUILD,
                        InteractionContextType.BOT_DM,
                        InteractionContextType.PRIVATE_CHANNEL
                );
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        OptionMapping number1OP = event.getOption("n1");
        OptionMapping number2OP = event.getOption("n2");

        if (number1OP == null || number2OP == null) {
            event.reply("Los parametros proporcionados no son correctos.").setEphemeral(true).queue();
            return;
        }

        int number1;
        int number2;

        try {
            number1 = number1OP.getAsInt();
            number2 = number2OP.getAsInt();
        } catch (ArithmeticException | NumberFormatException e) {
            event.reply("Los parametros proporcionados no son correctos.").setEphemeral(true).queue();
            return;
        }

        if (number1 > number2) {
            int aux = number2;
            number2 = number1;
            number1 = aux;
        }

        int numeroElegido = (int) (number1 + Math.random() * (number2 - number1 + 1));

        event.reply("Entre el *" + number1 + "* y el *" + number2 + "* el numero elegido ha sido el **" + numeroElegido + "**.").queue(); // .setEphemeral(true)
    }
}
