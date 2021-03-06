package virtuoel.kanostweaks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import virtuoel.kanostweaks.init.BlockRegistrar;
import virtuoel.kanostweaks.init.FluidRegistrar;
import virtuoel.kanostweaks.init.ItemRegistrar;

public class KanosTweaks implements ModInitializer
{
	public static final String MOD_ID = "kanostweaks";
	
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	
	@Override
	public void onInitialize()
	{
		new FluidRegistrar();
		new BlockRegistrar();
		new ItemRegistrar();
	}
	
	public static Identifier id(String name)
	{
		return new Identifier(MOD_ID, name);
	}
}
