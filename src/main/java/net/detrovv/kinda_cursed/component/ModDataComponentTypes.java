package net.detrovv.kinda_cursed.component;

import com.mojang.serialization.Codec;
import net.detrovv.kinda_cursed.KindaCursed;
import net.minecraft.component.ComponentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes
{
    public static final ComponentType<Boolean> CURSE_OF_HEAVY_BURDEN_ACTIVATION = register(
            "curse_of_heavy_burden_activation", builder -> builder.codec(Codec.BOOL).packetCodec(PacketCodecs.BOOL));

    private static <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator)
    {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(KindaCursed.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes()
    {
        KindaCursed.LOGGER.info("Registering Data Component Types for " + KindaCursed.MOD_ID);
    }
}
