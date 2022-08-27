package com.s0qva.easypunishment.client.event;

import net.minecraftforge.fml.common.eventhandler.Event;

public class SelectedModEvent extends Event {
    private final Object selectedMod;

    public SelectedModEvent(Object selectedMod) {
        this.selectedMod = selectedMod;
    }

    public Object getSelectedMod() {
        return selectedMod;
    }
}
