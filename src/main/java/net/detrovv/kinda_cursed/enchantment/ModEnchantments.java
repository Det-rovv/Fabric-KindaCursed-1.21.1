package net.detrovv.kinda_cursed.enchantment;

import net.detrovv.kinda_cursed.KindaCursed;
import net.detrovv.kinda_cursed.enchantment.custom.CurseOfDiscordEnchantmentEffect;
import net.detrovv.kinda_cursed.enchantment.custom.CurseOfHeavyBurdenEnchantmentEffect;
import net.detrovv.kinda_cursed.enchantment.custom.CurseOfSlipperyHandsEnchantmentEffect;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

public class ModEnchantments
{
    public static final RegistryKey<Enchantment> CURSE_OF_SLIPPERY_HANDS =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(KindaCursed.MOD_ID, "curse_of_slippery_hands"));
    public static final RegistryKey<Enchantment> CURSE_OF_HEAVY_BURDEN =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(KindaCursed.MOD_ID, "curse_of_heavy_burden"));
    public static final RegistryKey<Enchantment> CURSE_OF_DOOM =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(KindaCursed.MOD_ID, "curse_of_doom"));
    public static final RegistryKey<Enchantment> CURSE_OF_DISCORD =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(KindaCursed.MOD_ID, "curse_of_discord"));

    public static void bootstrap(Registerable<Enchantment> registerable)
    {
        var enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);

        var curseOfSlipperyHandsEnchantmentEffect = new CurseOfSlipperyHandsEnchantmentEffect();
        register(registerable, CURSE_OF_SLIPPERY_HANDS, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ItemTags.DURABILITY_ENCHANTABLE),
                5,
                1,
                Enchantment.constantCost(25),
                Enchantment.constantCost(50),
                8,
                AttributeModifierSlot.ANY))
                .addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.ATTACKER,
                        EnchantmentEffectTarget.ATTACKER,
                        curseOfSlipperyHandsEnchantmentEffect)
                .addEffect(EnchantmentEffectComponentTypes.HIT_BLOCK,
                        curseOfSlipperyHandsEnchantmentEffect));

        register(registerable, CURSE_OF_HEAVY_BURDEN, Enchantment.builder(Enchantment.definition(
                        items.getOrThrow(ItemTags.DURABILITY_ENCHANTABLE),
                        5,
                        1,
                        Enchantment.constantCost(25),
                        Enchantment.constantCost(50),
                        8,
                        AttributeModifierSlot.ANY,
                        AttributeModifierSlot.OFFHAND))
                .addEffect(EnchantmentEffectComponentTypes.TICK,
                new CurseOfHeavyBurdenEnchantmentEffect()));

        register(registerable, CURSE_OF_DISCORD, Enchantment.builder(Enchantment.definition(
                        items.getOrThrow(ItemTags.DURABILITY_ENCHANTABLE),
                        5,
                        1,
                        Enchantment.constantCost(25),
                        Enchantment.constantCost(50),
                        8,
                        AttributeModifierSlot.ANY,
                        AttributeModifierSlot.OFFHAND))
                .addEffect(EnchantmentEffectComponentTypes.TICK,
                        new CurseOfDiscordEnchantmentEffect()));

        register(registerable, CURSE_OF_DOOM, Enchantment.builder(Enchantment.definition(
                        items.getOrThrow(ItemTags.DURABILITY_ENCHANTABLE),
                        5,
                        1,
                        Enchantment.constantCost(25),
                        Enchantment.constantCost(50),
                        8,
                        AttributeModifierSlot.ANY)));
    }

    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder)
    {
        registry.register(key, builder.build(key.getValue()));
    }
}
