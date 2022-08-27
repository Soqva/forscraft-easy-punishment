package com.s0qva.easypunishment.client.timer;

import java.util.Timer;
import java.util.TimerTask;

public class ModGuiConfigOpenFromModListEventTimer {
    public static final long DEFAULT_DELAY = 5000L;
    private static final Timer TIMER = new Timer();
    private boolean canPerform = true;

    public void restrictGuiConfigOpening(long delay) {
        restrictPerforming();
        TIMER.schedule(new TimerTask() {
            @Override
            public void run() {
                allowPerforming();
            }
        }, DEFAULT_DELAY);
    }

    public void restrictGuiConfigOpening() {
        restrictGuiConfigOpening(DEFAULT_DELAY);
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
