package virtuoel.kanostweaks.init;

import net.minecraft.fluid.BaseFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import virtuoel.kanostweaks.KanosTweaks;
import virtuoel.kanostweaks.fluid.ExtendedLavaFluid;

public class FluidRegistrar
{
	public static final BaseFluid MOLTEN_OBSIDIAN = registerFluid(
		KanosTweaks.id("molten_obsidian"),
		new ExtendedLavaFluid.Still(
			f -> Registry.FLUID.get(KanosTweaks.id("flowing_molten_obsidian")),
			f -> BlockRegistrar.MOLTEN_OBSIDIAN.getDefaultState(),
			f -> ItemRegistrar.MOLTEN_OBSIDIAN_BUCKET
		)
	);
	
	public static final BaseFluid FLOWING_MOLTEN_OBSIDIAN = registerFluid(
		KanosTweaks.id("flowing_molten_obsidian"),
		new ExtendedLavaFluid.Flowing(
			f -> MOLTEN_OBSIDIAN,
			f -> BlockRegistrar.MOLTEN_OBSIDIAN.getDefaultState(),
			f -> ItemRegistrar.MOLTEN_OBSIDIAN_BUCKET
		)
	);
	
	private static <T extends Fluid> T registerFluid(Identifier name, T value)
	{
		return Registry.FLUID.register(name, value);
	}
}
