package com.s0qva.easypunishment.client.command.pardon;

public class UnbanCommand extends PardonCommand {
    public static final String COMMAND = COMMAND_PREFIX + "unban";

    public UnbanCommand(String playerNickname) {
        super(COMMAND, playerNickname);
    }
}
