package com.s0qva.easypunishment.client.handler;

import com.s0qva.easypunishment.EasyPunishment;
import com.s0qva.easypunishment.client.event.SelectedChatWordEvent;
import com.s0qva.easypunishment.client.mouse.MouseButtonIndex;
import com.s0qva.easypunishment.client.util.minecraft.MinecraftUtil;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Mouse;

@Mod.EventBusSubscriber(modid = EasyPunishment.MOD_ID)
public class GuiChatMouseLeftButtonClickEventHandler {

    @SubscribeEvent
    public static void handleGuiChatMouseLeftButtonClickEvent(GuiScreenEvent.MouseInputEvent event) {
        if (!(event.getGui() instanceof GuiChat)) {
            return;
        }
        GuiNewChat chatGUI = MinecraftUtil.obtainGuiNewChat();
        boolean isLeftMouseButton = Mouse.isButtonDown(MouseButtonIndex.LEFT_MOUSE_BUTTON.getIndex());

        if (!isLeftMouseButton || !chatGUI.getChatOpen()) {
            return;
        }
        int xSelectedCord = Mouse.getX();
        int ySelectedCord = Mouse.getY();

        MinecraftForge.EVENT_BUS.post(new SelectedChatWordEvent(xSelectedCord, ySelectedCord));
    }
}
