package net.detrovv.kindacursed.mixin;

import com.mojang.authlib.GameProfile;
import net.detrovv.kindacursed.enchantment.ModEnchantments;
import net.detrovv.kindacursed.enchantment.custom.CurseOfDoomEnchantmentEffect;
import net.detrovv.kindacursed.util.EnchantmentUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public abstract class LivingEntityDamageMixin extends PlayerEntity
{
    public LivingEntityDamageMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile)
    {
        super(world, pos, yaw, gameProfile);
    }

    @Inject(at = @At("TAIL"), method = "damage")
    private void damage(CallbackInfoReturnable info)
    {
        if (getWorld() instanceof ServerWorld serverWorld && !serverWorld.isClient())
        {
            Iterable<ItemStack> totalInventory = EnchantmentUtil.getAllVanillaPlayerItemStacks(getInventory());

            EnchantmentUtil.iterateEnchantmentEntries(totalInventory, (itemStack, component,
                                                                        enchantmentEntry) ->
            {
                if (enchantmentEntry.matchesId(ModEnchantments.CURSE_OF_DOOM.getValue()))
                {
                    CurseOfDoomEnchantmentEffect.staticApply(this);
                }
            });
        }
    }
}
