package net.detrovv.kinda_cursed.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

public record CurseOfDiscordEnchantmentEffect() implements EnchantmentEntityEffect
{
    public static final MapCodec<CurseOfDiscordEnchantmentEffect> CODEC =
            MapCodec.unit(CurseOfDiscordEnchantmentEffect::new);
    private static final Box SEARCH_ENTITY_BOX = new Box(-16, -16, -16, 16, 16, 16);
    private static final Random RANDOM = new Random();
    private static final float CURSE_ACTIVATION_PROBABILITY = 0.00083f;

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos)
    {
        if (!world.isClient() && user instanceof LivingEntity userLivingEntity)
        {
            if (RANDOM.nextFloat() <= CURSE_ACTIVATION_PROBABILITY)
            {
                activateCurseEffect(world, userLivingEntity);
            }
        }
    }

    private static void activateCurseEffect(ServerWorld world, LivingEntity user)
    {
        Box searchEntityBoxAroundPlayer = SEARCH_ENTITY_BOX.offset(user.getPos());
        var angerableEntities = world.getEntitiesByClass(HostileEntity.class, searchEntityBoxAroundPlayer,
                (hostileEntity) -> hostileEntity instanceof Angerable);

        angerHostilesOnEntity(angerableEntities, user);
    }

    private static void angerHostilesOnEntity(Iterable<HostileEntity> hostileEntities, LivingEntity target)
    {
        hostileEntities.forEach((entity) ->
        {
            if (entity.canTarget(target) &&
                    entity.canSee(target) &&
                    entity.getTarget() == null)
            {
                entity.setTarget(target);
            }
        });
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec()
    {
        return CODEC;
    }
}
