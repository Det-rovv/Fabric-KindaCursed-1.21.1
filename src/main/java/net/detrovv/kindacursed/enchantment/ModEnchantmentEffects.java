package net.detrovv.kindacursed.enchantment;

import com.mojang.serialization.MapCodec;
import net.detrovv.kindacursed.KindaCursed;
import net.detrovv.kindacursed.enchantment.custom.CurseOfHeavyBurdenEnchantmentEffect;
import net.detrovv.kindacursed.enchantment.custom.CurseOfSlipperyHandsEnchantmentEffect;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantmentEffects
{
    public static final MapCodec<? extends EnchantmentEntityEffect> CURSE_OF_SLIPPERY_HANDS =
            registerEntityEffect("curse_of_slippery_hands", CurseOfSlipperyHandsEnchantmentEffect.CODEC);

    public static final MapCodec<? extends EnchantmentEntityEffect> CURSE_OF_HEAVY_BURDEN =
            registerEntityEffect("curse_of_heavy_burden", CurseOfHeavyBurdenEnchantmentEffect.CODEC);




    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name,
                                                                                    MapCodec<? extends EnchantmentEntityEffect> codec)
    {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(KindaCursed.MOD_ID, name), codec);
    }

    public static void registerEnchantmentEffects()
    {
        KindaCursed.LOGGER.info("Registering Mod Enchantment Effects for " + KindaCursed.MOD_ID);
    }
}
