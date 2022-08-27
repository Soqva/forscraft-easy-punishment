package com.s0qva.easypunishment.client.util.minecraft.config;

import com.s0qva.easypunishment.client.util.minecraft.MinecraftUtil;
import net.minecraft.client.gui.GuiChat;

public final class GuiConfigUtil {

    private GuiConfigUtil() {
    }

    public static void restoreChatScreen() {
        MinecraftUtil.MINECRAFT.displayGuiScreen(new GuiChat());
    }
}
