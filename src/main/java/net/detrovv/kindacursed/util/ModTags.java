package net.detrovv.kindacursed.util;

import net.detrovv.kindacursed.KindaCursed;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags
{
    public static class Enchantments
    {
        public static final TagKey<Enchantment> INVENTORY_TICKING_ENCHANTMENTS =
                createTag("inventory_ticking_enchantments");

        private static TagKey<Enchantment> createTag(String name)
        {
            return TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(KindaCursed.MOD_ID, name));
        }
    }
}
