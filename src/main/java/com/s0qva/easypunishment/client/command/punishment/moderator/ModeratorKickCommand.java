package com.s0qva.easypunishment.client.command.punishment.moderator;

public class ModeratorKickCommand extends ModeratorPunishmentCommand {

    public ModeratorKickCommand(String command, String playerNickname, String reason) {
        super(COMMAND_PREFIX + command, playerNickname, reason);
    }
}
