package com.s0qva.easypunishment.client.event;

import net.minecraftforge.fml.common.eventhandler.Event;

public class SelectedChatWordEvent extends Event {
    private final int xSelectedCord;
    private final int ySelectedCord;

    public SelectedChatWordEvent(int xSelectedCord, int ySelectedCord) {
        this.xSelectedCord = xSelectedCord;
        this.ySelectedCord = ySelectedCord;
    }

    public int getXSelectedCord() {
        return xSelectedCord;
    }

    public int getYSelectedCord() {
        return ySelectedCord;
    }
}
