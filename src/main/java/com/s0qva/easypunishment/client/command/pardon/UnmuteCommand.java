package com.s0qva.easypunishment.client.command.pardon;

public class UnmuteCommand extends PardonCommand {
    public static final String COMMAND = COMMAND_PREFIX + "unmute";

    public UnmuteCommand(String playerNickname) {
        super(COMMAND, playerNickname);
    }
}
