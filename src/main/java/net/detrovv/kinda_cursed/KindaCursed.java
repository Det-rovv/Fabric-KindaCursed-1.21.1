package net.detrovv.kinda_cursed;

import net.detrovv.kinda_cursed.component.ModDataComponentTypes;
import net.detrovv.kinda_cursed.enchantment.ModEnchantmentEffects;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KindaCursed implements ModInitializer
{
	public static final String MOD_ID = "kinda_cursed";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize()
	{
		ModEnchantmentEffects.registerEnchantmentEffects();
		ModDataComponentTypes.registerDataComponentTypes();
	}
}