package com.s0qva.easypunishment;

import com.s0qva.easypunishment.config.EasyPunishmentGuiConfig;
import gg.essential.elementa.effects.StencilEffect;
import gg.essential.vigilance.Vigilance;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = EasyPunishment.MOD_ID,
        useMetadata = true,
        clientSideOnly = true
)
public class EasyPunishment {
    public static final String MOD_ID = "easy-punishment";

    @Mod.Instance(MOD_ID)
    public static EasyPunishment INSTANCE;


    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Vigilance.initialize();
        EasyPunishmentGuiConfig.INSTANCE.preload();
        StencilEffect.enableStencil();
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
    }
}
