package com.s0qva.easypunishment.client.command.punishment.moderator;

public class ModeratorBanCommand extends ModeratorTimeBasedPunishmentCommand {

    public ModeratorBanCommand(String command, String playerNickname, String punishmentTime, String reason) {
        super(COMMAND_PREFIX + command, playerNickname, punishmentTime, reason);
    }
}
