package net.detrovv.kindacursed;

import net.detrovv.kindacursed.component.ModDataComponentTypes;
import net.detrovv.kindacursed.enchantment.ModEnchantmentEffects;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KindaCursed implements ModInitializer
{
	public static final String MOD_ID = "kindacursed";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize()
	{
		ModEnchantmentEffects.registerEnchantmentEffects();
		ModDataComponentTypes.registerDataComponentTypes();
	}
}