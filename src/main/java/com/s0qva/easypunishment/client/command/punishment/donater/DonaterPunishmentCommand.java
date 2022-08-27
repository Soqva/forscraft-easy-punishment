package com.s0qva.easypunishment.client.command.punishment.donater;

import com.s0qva.easypunishment.client.command.punishment.PunishmentCommand;
import com.s0qva.easypunishment.client.util.basic.StringUtil;
import com.s0qva.easypunishment.client.util.minecraft.MinecraftUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class DonaterPunishmentCommand extends PunishmentCommand {
    private static final Logger LOGGER = LogManager.getLogger(DonaterPunishmentCommand.class);
    protected static final String COMMAND_TEMPLATE = "%s %s %s %s";
    protected final String command;
    protected final String rulePoint;

    public DonaterPunishmentCommand(String command, String playerNickname, String rulePoint, String reason) {
        super(playerNickname, reason);
        this.command = command;
        this.rulePoint = rulePoint;
    }

    @Override
    public void execute() {
        if (StringUtil.isBlank(playerNickname)) {
            LOGGER.info("PlayerNickname is empty");
            MinecraftUtil.sendMessageToPlayer("Никнейм игрока не может быть пустым.");
            return;
        }
        if (StringUtil.isBlank(rulePoint)) {
            LOGGER.info("RulePoint is empty");
            MinecraftUtil.sendMessageToPlayer("Пункт правил не может быть пустым.");
            return;
        }
        String executionCommand = String.format(COMMAND_TEMPLATE, command, playerNickname, rulePoint, reason);

        LOGGER.info("Built command: {}", executionCommand);
        MinecraftUtil.sendPlayerMessageToChat(executionCommand);
    }
}
