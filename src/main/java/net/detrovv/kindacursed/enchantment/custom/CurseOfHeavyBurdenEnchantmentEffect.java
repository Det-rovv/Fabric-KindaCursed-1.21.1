package net.detrovv.kindacursed.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.detrovv.kindacursed.KindaCursed;
import net.detrovv.kindacursed.component.ModDataComponentTypes;
import net.detrovv.kindacursed.mixin.ServerPlayerEntityFieldAccessor;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

public record CurseOfHeavyBurdenEnchantmentEffect() implements EnchantmentEntityEffect
{
    public static final MapCodec<CurseOfHeavyBurdenEnchantmentEffect> CODEC =
            MapCodec.unit(CurseOfHeavyBurdenEnchantmentEffect::new);
    public static final double DOWNWARD_PULL_VELOCITY_PER_TICK = 0.08;
    public static final double CURSE_ACTIVATION_PROBABILITY = 0.0025;
    private static final int JOIN_INVULNERABILITY_TICKS_DEFAULT_VALUE = 60;
    private static final Random RANDOM = new Random();

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos)
    {
        if (!world.isClient())
        {
            ItemStack itemStack = context.stack();
            boolean curseActive = getCurseActivation(itemStack);

            if (curseActive && notFirstTwoTicksAfterJoin(user))
            {
                if (user.isInFluid())
                {
                    pullEntityDown(user);
                }
                else setCurseActivation(itemStack, false);
            }
            else tryToActivateCurse(user, itemStack);
        }
    }

    private static boolean notFirstTwoTicksAfterJoin(Entity user)
    {
        return user instanceof ServerPlayerEntityFieldAccessor accessor &&
                accessor.getJoinInvulnerabilityTicks() < (JOIN_INVULNERABILITY_TICKS_DEFAULT_VALUE - 1);
    }

    private static boolean getCurseActivation(ItemStack stack)
    {
        return stack.getOrDefault(ModDataComponentTypes.CURSE_OF_HEAVY_BURDEN_ACTIVATION, false);
    }

    private static void setCurseActivation(ItemStack stack, boolean activation)
    {
        stack.set(ModDataComponentTypes.CURSE_OF_HEAVY_BURDEN_ACTIVATION, activation);
    }

    private static void pullEntityDown(Entity entity)
    {
        entity.addVelocity(0, -DOWNWARD_PULL_VELOCITY_PER_TICK, 0);
        if (entity instanceof ServerPlayerEntity serverPlayer)
        {
            serverPlayer.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(entity));
        }
    }

    private static void tryToActivateCurse(Entity entity, ItemStack stack)
    {
        if (entity.isInFluid() && RANDOM.nextFloat() <= CURSE_ACTIVATION_PROBABILITY)
        {
            setCurseActivation(stack, true);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec()
    {
        return CODEC;
    }
}
