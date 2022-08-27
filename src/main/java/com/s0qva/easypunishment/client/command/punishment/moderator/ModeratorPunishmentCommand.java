package com.s0qva.easypunishment.client.command.punishment.moderator;

import com.s0qva.easypunishment.client.command.punishment.PunishmentCommand;
import com.s0qva.easypunishment.client.util.basic.StringUtil;
import com.s0qva.easypunishment.client.util.minecraft.MinecraftUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ModeratorPunishmentCommand extends PunishmentCommand {
    private static final Logger LOGGER = LogManager.getLogger(ModeratorPunishmentCommand.class);
    protected static final String COMMAND_TEMPLATE = "%s %s %s";
    protected final String command;

    public ModeratorPunishmentCommand(String command, String playerNickname, String reason) {
        super(playerNickname, reason);
        this.command = command;
    }

    @Override
    public void execute() {
        if (StringUtil.isBlank(playerNickname)) {
            LOGGER.info("PlayerNickname is empty");
            MinecraftUtil.sendMessageToPlayer("Никнейм игрока не может быть пустым.");
            return;
        }
        String executionCommand = String.format(COMMAND_TEMPLATE, command, playerNickname, reason);

        LOGGER.info("Built command: {}", executionCommand);
        MinecraftUtil.sendPlayerMessageToChat(executionCommand);
    }
}
