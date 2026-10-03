package com.sami.naruto;

import com.mojang.serialization.Codec;
import java.util.function.Supplier;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, NarutoMod.MODID);

    public static final int MAX_CHAKRA = 100;

    public static final Supplier<AttachmentType<Integer>> CHAKRA = ATTACHMENTS.register("chakra",
            () -> AttachmentType.builder(() -> MAX_CHAKRA).serialize(Codec.INT).copyOnDeath().build());
}
