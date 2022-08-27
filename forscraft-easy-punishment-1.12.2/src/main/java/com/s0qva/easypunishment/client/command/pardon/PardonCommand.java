package com.s0qva.easypunishment.client.command.pardon;

import com.s0qva.easypunishment.client.command.MinecraftCommand;
import com.s0qva.easypunishment.client.util.basic.StringUtil;
import com.s0qva.easypunishment.client.util.minecraft.MinecraftUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class PardonCommand extends MinecraftCommand {
    private static final Logger LOGGER = LogManager.getLogger(PardonCommand.class);
    protected static final String COMMAND_TEMPLATE = "%s %s";
    protected final String command;
    protected final String playerNickname;

    public PardonCommand(String command, String playerNickname) {
        this.command = command;
        this.playerNickname = playerNickname;
    }

    @Override
    public void execute() {
        if (StringUtil.isBlank(playerNickname)) {
            MinecraftUtil.sendMessageToPlayer("Никнейм игрока не может быть пустым.");
            return;
        }
        String executionCommand = String.format(COMMAND_TEMPLATE, command, playerNickname);

        LOGGER.info("Built command: {}", executionCommand);
        MinecraftUtil.sendPlayerMessageToChat(executionCommand);
    }
}
