package net.detrovv.kinda_cursed.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ServerPlayerEntity.class)
public interface ServerPlayerEntityFieldAccessor
{
    @Accessor("joinInvulnerabilityTicks")
    int getJoinInvulnerabilityTicks();
}
