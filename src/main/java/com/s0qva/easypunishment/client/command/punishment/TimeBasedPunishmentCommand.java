package com.s0qva.easypunishment.client.command.punishment;

import com.s0qva.easypunishment.client.command.Command;

public abstract class TimeBasedPunishmentCommand extends PunishmentCommand implements Command {
    protected final String punishmentTime;

    public TimeBasedPunishmentCommand(String playerNickname, String punishmentTime, String reason) {
        super(playerNickname, reason);
        this.punishmentTime = punishmentTime;
    }
}
