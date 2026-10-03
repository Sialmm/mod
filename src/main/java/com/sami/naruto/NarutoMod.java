package com.sami.naruto;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NarutoMod.MODID)
public class NarutoMod {
    public static final String MODID = "naruto";

    public NarutoMod(IEventBus modBus) {
        ModAttachments.ATTACHMENTS.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModItems.TABS.register(modBus);
    }
}
