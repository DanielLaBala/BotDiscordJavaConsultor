package slashcommands;

import model.SlashCommand;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import service.CreacionUsoMensajesService;
import service.MessageService;
import service.PropertiesService;
import service.VersionService.BuildVersionMessageService;
import slashcommands.commands.*;

import java.util.List;

public class SlashCommandRegistry {
    private final List<SlashCommand> dataList;

    public SlashCommandRegistry(MessageService messageService, BuildVersionMessageService versionMessageService, CreacionUsoMensajesService creacionUsoMensajesService, PropertiesService propertiesService) {
        dataList = List.of(
            new VersionSlashCommand(messageService, versionMessageService),
            new FranBanSlashCommand(messageService),
            new RestriccionDivinaSlashCommand(messageService),
            new RandomSlashCommand(messageService),
            new OperacionSlashCommand(messageService),
            new EightBallSlashCommand(messageService),
            new CrearMensajeSlashCommand(messageService, creacionUsoMensajesService, propertiesService),
            new UsarMensajeSlashCommand(messageService, creacionUsoMensajesService)
        );
    }

    public List<CommandData> getCommandData() {
        return dataList.stream().map(SlashCommand::getCommandData).toList(); // s -> s.getCommandData()
    }

    public List<SlashCommand> getCommands() {
        return dataList;
    }
}
