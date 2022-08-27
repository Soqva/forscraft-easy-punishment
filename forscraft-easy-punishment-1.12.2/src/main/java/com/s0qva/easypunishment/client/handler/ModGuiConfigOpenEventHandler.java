package com.s0qva.easypunishment.client.handler;

import com.s0qva.easypunishment.EasyPunishment;
import com.s0qva.easypunishment.client.event.ModGuiConfigOpenFromChatEvent;
import com.s0qva.easypunishment.client.event.ModGuiConfigOpenFromModListEvent;
import com.s0qva.easypunishment.client.timer.ModGuiConfigOpenFromModListEventTimer;
import com.s0qva.easypunishment.client.util.minecraft.MinecraftUtil;
import com.s0qva.easypunishment.client.util.basic.StringUtil;
import com.s0qva.easypunishment.config.EasyPunishmentGuiConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = EasyPunishment.MOD_ID)
public class ModGuiConfigOpenEventHandler {
    private static final Logger LOGGER = LogManager.getLogger(ModGuiConfigOpenEventHandler.class);
    private static final ModGuiConfigOpenFromModListEventTimer TIMER = new ModGuiConfigOpenFromModListEventTimer();

    @SubscribeEvent
    public static void handleModGuiConfigOpenFromChatEvent(ModGuiConfigOpenFromChatEvent event) {
        String playerNickname = event.getPlayerNickname();

        if (StringUtil.isBlank(playerNickname)) {
            return;
        }
        EasyPunishmentGuiConfig.INSTANCE.passPlayerNickname(playerNickname);
        MinecraftUtil.displayModGuiConfigScreen();
    }

    @SubscribeEvent
    public static void handleModGuiConfigOpenFromModListEvent(ModGuiConfigOpenFromModListEvent event) {
        if (!TIMER.canPerform()) {
            return;
        }
        MinecraftUtil.displayModGuiConfigScreen();
        TIMER.restrictGuiConfigOpening();
    }
}
