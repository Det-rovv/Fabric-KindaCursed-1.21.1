package net.detrovv.kindacursed.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public record CurseOfDoomEnchantmentEffect() implements EnchantmentEntityEffect
{
    public static final MapCodec<CurseOfDoomEnchantmentEffect> CODEC =
            MapCodec.unit(CurseOfDoomEnchantmentEffect::new);
    public static final double DEATH_HEALTH_LEVEL = 1;

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos)
    {
        staticApply(user);
    }

    public static void staticApply(Entity user)
    {
        if (user instanceof LivingEntity livingEntity)
        {
            float health = livingEntity.getHealth();
            if (health > 0 && health <= DEATH_HEALTH_LEVEL)
            {
                livingEntity.kill();
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec()
    {
        return CODEC;
    }
}
