package com.s0qva.easypunishment.client.command.punishment;

import com.s0qva.easypunishment.client.command.MinecraftCommand;

public abstract class PunishmentCommand extends MinecraftCommand {
    protected final String playerNickname;
    protected final String reason;

    public PunishmentCommand(String playerNickname, String reason) {
        this.playerNickname = playerNickname;
        this.reason = reason;
    }
}
