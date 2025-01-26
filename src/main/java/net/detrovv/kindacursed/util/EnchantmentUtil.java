package net.detrovv.kindacursed.util;

import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.collection.DefaultedList;

public class EnchantmentUtil
{
    @FunctionalInterface
    public interface EnchantmentsComponentIteration
    {
        void operate(ItemStack itemStack, ItemEnchantmentsComponent component);
    }

    @FunctionalInterface
    public interface EnchantmentEntryIteration
    {
        void operate(ItemStack itemStack, ItemEnchantmentsComponent component,
                     RegistryEntry<Enchantment> enchantmentEntry);
    }

    @FunctionalInterface
    public interface EnchantmentIteration
    {
        void operate(ItemStack itemStack, ItemEnchantmentsComponent component,
                     RegistryEntry<Enchantment> enchantmentEntry, Enchantment enchantment);
    }


    public static void iterateItemEnchantmentsComponents(Iterable<ItemStack> inventory,
                                                         EnchantmentsComponentIteration operation)
    {
        for (ItemStack itemStack : inventory)
        {
            var enchantmentsComponent = EnchantmentHelper.getEnchantments(itemStack);

            operation.operate(itemStack, enchantmentsComponent);
        }
    }

    public static void iterateEnchantmentEntries(Iterable<ItemStack> inventory,
                                                 EnchantmentEntryIteration operation)
    {
        iterateItemEnchantmentsComponents(inventory, (itemStack, component) ->
        {
            for (var enchantmentEntry : component.getEnchantments())
            {
                operation.operate(itemStack, component, enchantmentEntry);
            }
        });
    }

    public static void iterateEnchantments(Iterable<ItemStack> inventory, EnchantmentIteration operation)
    {
        iterateEnchantmentEntries(inventory, (itemStack, component, enchantmentEntry) ->
        {
            operation.operate(itemStack, component, enchantmentEntry, enchantmentEntry.value());
        });
    }

    public static Iterable<ItemStack> getAllVanillaPlayerItemStacks(PlayerInventory inventory)
    {
        DefaultedList<ItemStack> totalInventory = DefaultedList.of();

        totalInventory.addAll(inventory.main);
        totalInventory.addAll(inventory.armor);
        totalInventory.addAll(inventory.offHand);

        return totalInventory;
    }
}
