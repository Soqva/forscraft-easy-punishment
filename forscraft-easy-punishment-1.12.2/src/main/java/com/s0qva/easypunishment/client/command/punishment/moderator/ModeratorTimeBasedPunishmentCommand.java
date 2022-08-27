package com.s0qva.easypunishment.client.command.punishment.moderator;

import com.s0qva.easypunishment.client.command.punishment.TimeBasedPunishmentCommand;
import com.s0qva.easypunishment.client.util.minecraft.MinecraftUtil;
import com.s0qva.easypunishment.client.util.basic.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ModeratorTimeBasedPunishmentCommand extends TimeBasedPunishmentCommand {
    private static final Logger LOGGER = LogManager.getLogger(ModeratorPunishmentCommand.class);
    protected static final String COMMAND_TEMPLATE = "%s %s %s %s";
    protected final String command;

    public ModeratorTimeBasedPunishmentCommand(String command, String playerNickname, String punishmentTime, String reason) {
        super(playerNickname, punishmentTime, reason);
        this.command = command;
    }

    @Override
    public void execute() {
        if (StringUtil.isBlank(command)) {
            LOGGER.info("Command is empty");
            MinecraftUtil.sendMessageToPlayer("команда не может быть пустым.");
            return;
        }
        if (StringUtil.isBlank(playerNickname)) {
            LOGGER.info("PlayerNickname is empty");
            MinecraftUtil.sendMessageToPlayer("Никнейм игрока не может быть пустым.");
            return;
        }
        String executionCommand = String.format(COMMAND_TEMPLATE, command, playerNickname, punishmentTime, reason);

        LOGGER.info("Built command: {}", executionCommand);
        MinecraftUtil.sendPlayerMessageToChat(executionCommand);
    }
}
