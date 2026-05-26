package service;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.time.temporal.ChronoUnit;

public class MessageService {
    // Quizas usar mejor una clase de mensajes y luego ir construyendo dinamicamente el mensaje real

    void sendChannelMessage(SlashCommandInteractionEvent event, String mensaje) {
        event.getChannel().sendMessage(mensaje).queue();
    }

    void reply(SlashCommandInteractionEvent event, String mensaje) {
        event.reply(mensaje).queue();
    }

    void replyEphemeral(SlashCommandInteractionEvent event, String mensaje) {
        event.reply(mensaje).setEphemeral(true).queue();
    }

    void replyEmbed(SlashCommandInteractionEvent event, String mensaje, String imageUrl) {
        event.replyEmbeds(new EmbedBuilder()
                .setDescription(mensaje)
                .setImage(imageUrl)
                .build()
        ).queue();
    }
}
