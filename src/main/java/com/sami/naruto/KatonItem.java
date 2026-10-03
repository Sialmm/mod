package com.sami.naruto;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class KatonItem extends Item {
    private static final int COST = 30;

    public KatonItem(Properties props) {
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
        for (int i = -1; i <= 1; i++) {
            Vec3 dir = look.yRot((float) Math.toRadians(i * 8));
            SmallFireball fb = new SmallFireball(level, player, dir.scale(1.2));
            fb.setPos(player.getX() + dir.x, player.getEyeY() - 0.1 + dir.y, player.getZ() + dir.z);
            level.addFreshEntity(fb);
        }
        level.playSound(null, player.blockPosition(), SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS, 1f, 0.8f);
        player.getCooldowns().addCooldown(this, 40);
        return InteractionResultHolder.sidedSuccess(stack, false);
    }
}
