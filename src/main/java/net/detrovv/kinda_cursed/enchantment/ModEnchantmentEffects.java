package net.detrovv.kinda_cursed.enchantment;

import com.mojang.serialization.MapCodec;
import net.detrovv.kinda_cursed.KindaCursed;
import net.detrovv.kinda_cursed.enchantment.custom.CurseOfDiscordEnchantmentEffect;
import net.detrovv.kinda_cursed.enchantment.custom.CurseOfDoomEnchantmentEffect;
import net.detrovv.kinda_cursed.enchantment.custom.CurseOfHeavyBurdenEnchantmentEffect;
import net.detrovv.kinda_cursed.enchantment.custom.CurseOfSlipperyHandsEnchantmentEffect;
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

    public static final MapCodec<? extends EnchantmentEntityEffect> CURSE_OF_DOOM =
            registerEntityEffect("curse_of_doom", CurseOfDoomEnchantmentEffect.CODEC);

    public static final MapCodec<? extends EnchantmentEntityEffect> CURSE_OF_DISCORD =
            registerEntityEffect("curse_of_discord", CurseOfDiscordEnchantmentEffect.CODEC);

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
