package com.sami.naruto;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = NarutoMod.MODID)
public class ChakraEvents {
    @SubscribeEvent
    public static void onTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer sp)) return;
        if (sp.tickCount % 20 == 0) {
            int c = sp.getData(ModAttachments.CHAKRA);
            if (c < ModAttachments.MAX_CHAKRA) Chakra.set(sp, c + 3);
        }
    }

    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer sp) Chakra.set(sp, sp.getData(ModAttachments.CHAKRA));
    }

    @SubscribeEvent
    public static void onRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer sp) Chakra.set(sp, ModAttachments.MAX_CHAKRA);
    }
}
