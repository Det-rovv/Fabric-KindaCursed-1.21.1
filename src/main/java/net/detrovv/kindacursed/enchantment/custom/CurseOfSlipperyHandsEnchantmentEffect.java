package net.detrovv.kindacursed.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

public record CurseOfSlipperyHandsEnchantmentEffect() implements EnchantmentEntityEffect
{
    public static final MapCodec<CurseOfSlipperyHandsEnchantmentEffect> CODEC =
            MapCodec.unit(CurseOfSlipperyHandsEnchantmentEffect::new);
    private static final float ITEM_DROP_PROBABILITY = 0.02f;
    private static final Random RANDOM = new Random();

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos)
    {
        if (user instanceof ServerPlayerEntity serverPlayer)
        {
            if (RANDOM.nextFloat() <= ITEM_DROP_PROBABILITY)
            {
                dropItemInPlayerHand(serverPlayer);
                world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_PARROT_AMBIENT, SoundCategory.PLAYERS);
            }
        }
    }

    private static void dropItemInPlayerHand(ServerPlayerEntity serverPlayer)
    {
        ItemStack playerHandStack = serverPlayer.getMainHandStack();
        serverPlayer.dropItem(playerHandStack, false);
        serverPlayer.setStackInHand(serverPlayer.getActiveHand(), ItemStack.EMPTY);
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec()
    {
        return CODEC;
    }
}