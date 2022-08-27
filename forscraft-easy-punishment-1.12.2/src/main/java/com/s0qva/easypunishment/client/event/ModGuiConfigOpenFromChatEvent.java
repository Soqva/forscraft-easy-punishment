package com.s0qva.easypunishment.client.event;

import net.minecraftforge.fml.common.eventhandler.Event;

public class ModGuiConfigOpenFromChatEvent extends Event {
    private final String playerNickname;

    public ModGuiConfigOpenFromChatEvent(String playerNickname) {
        this.playerNickname = playerNickname;
    }

    public String getPlayerNickname() {
        return playerNickname;
    }
}
