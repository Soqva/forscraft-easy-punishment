package com.s0qva.easypunishment.client.util.minecraft;

import com.s0qva.easypunishment.client.util.minecraft.chat.ChatMessageUtil;
import com.s0qva.easypunishment.client.util.basic.StringUtil;
import com.s0qva.easypunishment.config.EasyPunishmentGuiConfig;
import gg.essential.universal.UScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.Objects;

public final class MinecraftUtil {
    public static final Minecraft MINECRAFT = Minecraft.getMinecraft();
    private static final Logger LOGGER = LogManager.getLogger(MinecraftUtil.class);

    private MinecraftUtil() {
    }

    public static FontRenderer obtainFontRender() {
        return MINECRAFT.fontRenderer;
    }

    public static GuiNewChat obtainGuiNewChat() {
        return MINECRAFT.ingameGUI
                .getChatGUI();
    }

    public static Path obtainGameDirectoryPath() {
        return MINECRAFT.gameDir
                .toPath();
    }

    public static String obtainGameDirectoryPathAsString() {
        return MINECRAFT.gameDir
                .getPath();
    }

    public static EntityPlayerSP obtainCurrentPlayer() {
        return MINECRAFT.player;
    }

    public static void sendMessageToPlayer(ITextComponent message) {
        if (ChatMessageUtil.isNull(message) || StringUtil.isBlank(message.getUnformattedText())) {
            return;
        }
        EntityPlayerSP currentPlayer = obtainCurrentPlayer();

        if (Objects.isNull(currentPlayer)) {
            LOGGER.warn("[MinecraftUtil.sendMessage] player is null");
            return;
        }
        currentPlayer.sendMessage(message);
    }

    public static void sendMessageToPlayer(String message) {
        if (StringUtil.isBlank(message)) {
            return;
        }
        sendMessageToPlayer(new TextComponentString(message));
    }

    public static void sendPlayerMessageToChat(String messageText) {
        if (StringUtil.isBlank(messageText)) {
            return;
        }
        EntityPlayerSP currentPlayer = obtainCurrentPlayer();

        if (Objects.isNull(currentPlayer)) {
            LOGGER.warn("[MinecraftUtil.sendMessage] player is null");
            return;
        }
        currentPlayer.sendChatMessage(messageText);
    }

    public static void sendPlayerMessageToChat(ITextComponent message) {
        if (ChatMessageUtil.isNull(message)) {
            return;
        }
        sendPlayerMessageToChat(message.getUnformattedText());
    }

    public static void displayModGuiConfigScreen() {
        UScreen.displayScreen(EasyPunishmentGuiConfig.INSTANCE.gui());
    }
}
