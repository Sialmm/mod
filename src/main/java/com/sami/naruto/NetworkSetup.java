package com.sami.naruto;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

@EventBusSubscriber(modid = NarutoMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class NetworkSetup {
    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        event.registrar("1").playToClient(ChakraPayload.TYPE, ChakraPayload.CODEC, ChakraPayload::handle);
    }
}
