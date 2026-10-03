package com.sami.naruto;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;

@EventBusSubscriber(modid = NarutoMod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void layers(RegisterGuiLayersEvent event) {
        event.registerAboveAll(ResourceLocation.fromNamespaceAndPath(NarutoMod.MODID, "chakra"), (gg, delta) -> {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player == null || mc.options.hideGui) return;
            int c = mc.player.getData(ModAttachments.CHAKRA);
            int x = 10, y = gg.guiHeight() - 20;
            gg.fill(x - 1, y - 1, x + ModAttachments.MAX_CHAKRA + 1, y + 7, 0xFF000000);
            gg.fill(x, y, x + ModAttachments.MAX_CHAKRA, y + 6, 0xFF1A1A33);
            gg.fill(x, y, x + c, y + 6, 0xFF3399FF);
        });
    }
}
