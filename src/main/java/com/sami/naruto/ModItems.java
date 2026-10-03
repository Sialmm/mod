package com.sami.naruto;

import java.util.function.Supplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NarutoMod.MODID);
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NarutoMod.MODID);

    public static final DeferredItem<Item> KATON =
            ITEMS.register("katon_scroll", () -> new KatonItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> SHUNSHIN =
            ITEMS.register("shunshin_scroll", () -> new ShunshinItem(new Item.Properties().stacksTo(1)));

    public static final Supplier<CreativeModeTab> TAB = TABS.register("main", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.naruto"))
                    .icon(() -> new ItemStack(KATON.get()))
                    .displayItems((params, out) -> {
                        out.accept(KATON.get());
                        out.accept(SHUNSHIN.get());
                    }).build());
}
