package net.detrovv.kindacursed.mixin;

import com.mojang.authlib.GameProfile;
import net.detrovv.kindacursed.util.ModTags;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class InventoryEnchantmentTickMixin extends PlayerEntity
{
	public InventoryEnchantmentTickMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile)
	{
		super(world, pos, yaw, gameProfile);
	}

	@Inject(at = @At("TAIL"), method = "tick")
	private void tick(CallbackInfo info)
	{
		ServerWorld serverWorld = (ServerWorld)getWorld();
		var inventory = getInventory().main;

		for (var itemStack : inventory)
		{
			var enchantmentsComponent = EnchantmentHelper.getEnchantments(itemStack);

			for (var enchantmentEntry : enchantmentsComponent.getEnchantments())
			{
				Enchantment enchantment = enchantmentEntry.value();
				if (enchantmentEntry.isIn(ModTags.Enchantments.INVENTORY_TICKING_ENCHANTMENTS))
				{
					applyEnchantmentTickEffects(serverWorld, this, itemStack, enchantment,
							enchantmentsComponent.getLevel(enchantmentEntry));
				}
			}
		}
	}

	private static void applyEnchantmentTickEffects(ServerWorld serverWorld, LivingEntity entity,
													ItemStack itemStack, Enchantment enchantment, int level)
	{
		var tickingEffects = enchantment.getEffect(EnchantmentEffectComponentTypes.TICK);
		var context = new EnchantmentEffectContext(itemStack, null, entity);

		for (var tickingEffect : tickingEffects)
		{
			tickingEffect.effect().apply(serverWorld, level, context, entity, entity.getPos());
		}
	}
}