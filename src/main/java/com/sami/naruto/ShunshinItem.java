package com.sami.naruto;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ShunshinItem extends Item {
    private static final int COST = 20;

    public ShunshinItem(Properties props) {
        super(props);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide) return InteractionResultHolder.sidedSuccess(stack, true);
        ServerPlayer sp = (ServerPlayer) player;
        if (!Chakra.spend(sp, COST)) {
            sp.displayClientMessage(Component.translatable("message.naruto.no_chakra"), true);
            return InteractionResultHolder.fail(stack);
        }
        Vec3 look = player.getLookAngle();
        sp.setDeltaMovement(look.scale(2.5).add(0, 0.2, 0));
        sp.hurtMarked = true;
        sp.fallDistance = 0;
        level.playSound(null, player.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1f, 1.4f);
        player.getCooldowns().addCooldown(this, 30);
        return InteractionResultHolder.sidedSuccess(stack, false);
    }
}
