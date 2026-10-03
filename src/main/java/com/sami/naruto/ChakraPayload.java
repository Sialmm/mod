package com.sami.naruto;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record ChakraPayload(int chakra) implements CustomPacketPayload {
    public static final Type<ChakraPayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(NarutoMod.MODID, "chakra"));
    public static final StreamCodec<ByteBuf, ChakraPayload> CODEC =
            ByteBufCodecs.VAR_INT.map(ChakraPayload::new, ChakraPayload::chakra);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ChakraPayload payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> ctx.player().setData(ModAttachments.CHAKRA, payload.chakra()));
    }
}
