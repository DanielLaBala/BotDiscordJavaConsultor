package slashcommands;

import ui.Bot;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesService {
    Properties p = new Properties();

    public PropertiesService() {
        try {
            InputStream input = Bot.class.getResourceAsStream("/bot.properties");

            if (input != null) {
                p.load(input);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDiscordToken() {
        return p.getProperty("discord.token");
    }
}
