package com.s0qva.easypunishment.client.command.punishment.donater;

public class DonaterBanCommand extends DonaterPunishmentCommand {
    public static final String COMMAND = COMMAND_PREFIX + "ban";

    public DonaterBanCommand(String playerNickname, String rulePoint, String reason) {
        super(COMMAND, playerNickname, rulePoint, reason);
    }
}
