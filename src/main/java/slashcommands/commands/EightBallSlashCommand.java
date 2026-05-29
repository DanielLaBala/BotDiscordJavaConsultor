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

public class EightBallSlashCommand implements SlashCommand {
    public static final String ID = "8ball";
    public static final String DESCRIPTION = "Hazle una pregunta y te respondera con todo su gran conocimiento.";

    private static final String[] respuestas = {
            "Sí",
            "No",
            "Tal vez",
            "Ni de coña",
            "Claro que sí",
            "Ni en tus sueños",
            "No lo se",
            "Probablemente",
            "Imposible",
            "Definitivamente",
            "Definitivamente no",
            "Puede ser",
            "No lo tengo claro",
            "Eso pinta mal",
            "Sin duda",
            "No cuentes con ello",
            "Me la pela",
            "Ahora mismo no",
            "Todo apunta a que sí",
            "Todo apunta a que no",
            "Lo veo bastante probable",
            "Muy dudoso",
            "Apuesta por ello",
            "No apostaría por eso",
            "El destino dice que sí",
            "El destino dice que no",
            "Obviamente (en tus sueños)",
            "Mas quisieras",
            "Error 404: respuesta no encontrada",
            "Si ocurre un milagro, si",
            "Prefieres no saberlo",
            "Confia en el corazon de las cartas",
            "Se lo pregunte a tu madre y me dijo simon"
    };

    MessageService ms;

    public EightBallSlashCommand(MessageService messageService) {
        ms = messageService;
    }

    @Override
    public String getName() {
        return ID;
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash(ID, DESCRIPTION)
                .addOption(OptionType.STRING, "pregunta", "pregunta", true)
                .setIntegrationTypes(IntegrationType.USER_INSTALL)
                .setContexts(
                        InteractionContextType.GUILD,
                        InteractionContextType.BOT_DM,
                        InteractionContextType.PRIVATE_CHANNEL
                );
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        OptionMapping pregunta = event.getOption("pregunta");

        if (pregunta == null) {
            event.reply("No se ha especificado la pregunta.").setEphemeral(true).queue();
            return;
        }

        int indice = (int) (Math.random() * respuestas.length);
        String respuestaAleatoria = respuestas[indice];

        event.reply("*🥸 PREGUNTA:* **" + pregunta.getAsString() + "** \n*🎱 RESPUESTA:* **" + respuestaAleatoria + "**").queue();
    }
}
