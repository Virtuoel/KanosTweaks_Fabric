package virtuoel.kanostweaks.block;

import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.BaseFluid;

public class PublicFluidBlock extends FluidBlock
{
	// because ofc FluidBlock is protected
	public PublicFluidBlock(BaseFluid fluid, Settings settings)
	{
		super(fluid, settings);
	}
}
