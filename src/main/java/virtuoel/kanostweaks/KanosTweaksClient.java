package virtuoel.kanostweaks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.client.texture.SpriteRegistry;
import net.fabricmc.fabric.events.client.SpriteEvent;
import net.minecraft.fluid.Fluid;
import virtuoel.kanostweaks.init.FluidRegistrar;
import virtuoel.towelette.util.FluidUtils;

public class KanosTweaksClient implements ClientModInitializer
{
	@Override
	public void onInitializeClient()
	{
		SpriteEvent.PROVIDE.register(registry ->
		{
			registerFluidSprites(registry, FluidRegistrar.MOLTEN_OBSIDIAN);
			registerFluidSprites(registry, FluidRegistrar.FLOWING_MOLTEN_OBSIDIAN);
		});
	}
	
	public static void registerFluidSprites(SpriteRegistry registry, Fluid fluid)
	{
		FluidUtils.getSpriteIdForFluid(fluid, true).ifPresent(registry::register);
		FluidUtils.getSpriteIdForFluid(fluid, false).ifPresent(registry::register);
		FluidUtils.getOverlaySpriteIdForFluid(fluid).ifPresent(registry::register);
	}
}
