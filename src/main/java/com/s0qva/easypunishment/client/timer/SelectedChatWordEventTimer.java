package com.s0qva.easypunishment.client.timer;

import java.util.Timer;
import java.util.TimerTask;

public class SelectedChatWordEventTimer {
    public static final long DEFAULT_DELAY = 100L;
    private static final Timer TIMER = new Timer();
    private boolean canPerform = true;

    public void restrictRepeats(long delay) {
        restrictPerforming();
        TIMER.schedule(new TimerTask() {
            @Override
            public void run() {
                allowPerforming();
            }
        }, DEFAULT_DELAY);
    }

    public void restrictRepeats() {
        restrictRepeats(DEFAULT_DELAY);
    }

    private void restrictPerforming() {
        canPerform = false;
    }

    private void allowPerforming() {
        canPerform = true;
    }

    public boolean canPerform() {
        return canPerform;
    }
}
