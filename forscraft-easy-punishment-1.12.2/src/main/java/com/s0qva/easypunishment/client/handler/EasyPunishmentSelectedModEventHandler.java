package com.s0qva.easypunishment.client.handler;

import com.s0qva.easypunishment.EasyPunishment;
import com.s0qva.easypunishment.client.event.ModGuiConfigOpenFromModListEvent;
import com.s0qva.easypunishment.client.event.SelectedModEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLModContainer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = EasyPunishment.MOD_ID)
public class EasyPunishmentSelectedModEventHandler {

    @SubscribeEvent
    public static void handleSelectedModEvent(SelectedModEvent event) {
        Object selectedMod = event.getSelectedMod();

        if (!(selectedMod instanceof FMLModContainer)) {
            return;
        }
        ModContainer selectedModContainer = (FMLModContainer) selectedMod;

        if (!selectedModContainer.getModId().equals(EasyPunishment.MOD_ID)) {
            return;
        }
        MinecraftForge.EVENT_BUS.post(new ModGuiConfigOpenFromModListEvent());
    }
}
