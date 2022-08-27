package com.s0qva.easypunishment.client.util.minecraft.command;

import com.s0qva.easypunishment.client.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public final class CommandUtil {
    private static final Logger LOGGER = LogManager.getLogger(CommandUtil.class);

    private CommandUtil() {
    }

    public static void execute(Command command) {
        if (Objects.isNull(command)) {
            LOGGER.error("[CommandUtil.execute] Received command is null");
            throw new RuntimeException();
        }
        command.execute();
    }
}
