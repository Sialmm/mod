package com.sami.naruto;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;

public class Chakra {
    public static boolean spend(ServerPlayer p, int cost) {
        int c = p.getData(ModAttachments.CHAKRA);
        if (c < cost) return false;
        set(p, c - cost);
        return true;
    }

    public static void set(ServerPlayer p, int value) {
        int v = Math.max(0, Math.min(ModAttachments.MAX_CHAKRA, value));
        p.setData(ModAttachments.CHAKRA, v);
        PacketDistributor.sendToPlayer(p, new ChakraPayload(v));
    }
}
