package com.s0qva.easypunishment.client.command.punishment.moderator;

public class ModeratorMuteCommand extends ModeratorTimeBasedPunishmentCommand {

    public ModeratorMuteCommand(String command, String playerNickname, String punishmentTime, String reason) {
        super(COMMAND_PREFIX + command, playerNickname, punishmentTime, reason);
    }
}
