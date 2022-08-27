package com.s0qva.easypunishment.client.command.punishment.donater;

public class DonaterKickCommand extends DonaterPunishmentCommand {
    public static final String COMMAND = COMMAND_PREFIX + "kick";

    public DonaterKickCommand(String playerNickname, String rulePoint, String reason) {
        super(COMMAND, playerNickname, rulePoint, reason);
    }
}
