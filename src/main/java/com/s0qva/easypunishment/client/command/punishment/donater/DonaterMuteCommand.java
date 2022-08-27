package com.s0qva.easypunishment.client.command.punishment.donater;

public class DonaterMuteCommand extends DonaterPunishmentCommand {
    public static final String COMMAND = COMMAND_PREFIX + "mute";

    public DonaterMuteCommand(String playerNickname, String rulePoint, String reason) {
        super(COMMAND, playerNickname, rulePoint, reason);
    }
}
