package com.s0qva.easypunishment.client.handler;

import com.s0qva.easypunishment.EasyPunishment;
import com.s0qva.easypunishment.client.event.ModGuiConfigOpenFromChatEvent;
import com.s0qva.easypunishment.client.event.SelectedChatWordEvent;
import com.s0qva.easypunishment.client.timer.SelectedChatWordEventTimer;
import com.s0qva.easypunishment.client.util.minecraft.chat.ChatMessageUtil;
import com.s0qva.easypunishment.client.util.minecraft.MinecraftUtil;
import com.s0qva.easypunishment.client.util.basic.StringUtil;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = EasyPunishment.MOD_ID)
public class SelectedChatWordEventHandler {
    private static final Logger LOGGER = LogManager.getLogger(SelectedChatWordEventHandler.class);
    private static final SelectedChatWordEventTimer TIMER = new SelectedChatWordEventTimer();

    @SubscribeEvent
    public static void handleSelectedChatWordEvent(SelectedChatWordEvent event) {
        if (!TIMER.canPerform()) {
            return;
        }

        GuiNewChat chatGUI = MinecraftUtil.obtainGuiNewChat();
        int xSelectedCord = event.getXSelectedCord();
        int ySelectedCord = event.getYSelectedCord();
        String selectedWord = ChatMessageUtil.obtainSelectedChatWord(chatGUI, xSelectedCord, ySelectedCord);

        if (StringUtil.isBlank(selectedWord)) {
            LOGGER.warn("Selected word is blank");
            return;
        }
        TIMER.restrictRepeats();
        MinecraftForge.EVENT_BUS.post(new ModGuiConfigOpenFromChatEvent(selectedWord));
    }
}
