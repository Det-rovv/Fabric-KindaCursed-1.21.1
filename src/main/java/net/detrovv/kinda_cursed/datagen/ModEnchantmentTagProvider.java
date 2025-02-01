package net.detrovv.kinda_cursed.datagen;

import net.detrovv.active_enchantments_lib.util.ModTags;
import net.detrovv.kinda_cursed.enchantment.ModEnchantments;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModEnchantmentTagProvider extends FabricTagProvider.EnchantmentTagProvider
{
    public ModEnchantmentTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture)
    {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
        getOrCreateTagBuilder(ModTags.Enchantments.INVENTORY_TICKING_ENCHANTMENTS)
                .add(ModEnchantments.CURSE_OF_HEAVY_BURDEN);
    }
}