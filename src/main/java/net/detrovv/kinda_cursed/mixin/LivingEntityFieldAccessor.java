package net.detrovv.kinda_cursed.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LivingEntity.class)
public interface LivingEntityFieldAccessor
{
    @Accessor("lastDamageSource")
    DamageSource getLastDamageSource();
}
