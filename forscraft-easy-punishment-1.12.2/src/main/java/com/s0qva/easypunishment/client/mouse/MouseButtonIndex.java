package com.s0qva.easypunishment.client.mouse;

public enum MouseButtonIndex {
    LEFT_MOUSE_BUTTON(0);

    private final int index;

    MouseButtonIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
