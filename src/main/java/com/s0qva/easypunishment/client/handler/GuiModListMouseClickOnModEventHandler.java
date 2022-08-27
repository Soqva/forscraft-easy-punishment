package com.s0qva.easypunishment.client.handler;

import com.s0qva.easypunishment.EasyPunishment;
import com.s0qva.easypunishment.client.event.SelectedModEvent;
import com.s0qva.easypunishment.client.util.basic.ReflectionUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.GuiModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = EasyPunishment.MOD_ID)
public class GuiModListMouseClickOnModEventHandler {

    @SubscribeEvent
    public static void handleMouseClickOnModEvent(GuiScreenEvent.MouseInputEvent event) {
        GuiScreen currentGui = event.getGui();

        if (!(currentGui instanceof GuiModList)) {
            return;
        }
        GuiModList guiModList = (GuiModList) currentGui;
        Object selectedMod = ReflectionUtil.obtainObject(guiModList, "selectedMod");

        if (Objects.isNull(selectedMod)) {
            return;
        }
        MinecraftForge.EVENT_BUS.post(new SelectedModEvent(selectedMod));
    }
}
