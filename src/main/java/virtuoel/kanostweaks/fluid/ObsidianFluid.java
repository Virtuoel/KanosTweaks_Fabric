package virtuoel.kanostweaks.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.item.Item;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.Properties;
import virtuoel.kanostweaks.init.BlockRegistrar;
import virtuoel.kanostweaks.init.FluidRegistrar;
import virtuoel.kanostweaks.init.ItemRegistrar;

public abstract class ObsidianFluid extends LavaFluid
{
	@Override
	public Fluid getStill()
	{
		return FluidRegistrar.MOLTEN_OBSIDIAN;
	}
	
	@Override
	public Fluid getFlowing()
	{
		return FluidRegistrar.FLOWING_MOLTEN_OBSIDIAN;
	}
	
	@Override
	public Item getBucketItem()
	{
		return ItemRegistrar.MOLTEN_OBSIDIAN_BUCKET;
	}
	
	@Override
	public boolean matchesType(Fluid var1)
	{
		return var1 == getStill() || var1 == getFlowing();
	}
	
	@Override
	public BlockState toBlockState(FluidState var1)
	{
		return BlockRegistrar.MOLTEN_OBSIDIAN.getDefaultState().with(Properties.FLUID_BLOCK_LEVEL, method_15741(var1));
	}
	
	public static class Flowing extends ObsidianFluid
	{
		@Override
		protected void appendProperties(StateFactory.Builder<Fluid, FluidState> var1)
		{
			super.appendProperties(var1);
			var1.with(LEVEL);
		}
		
		@Override
		public int method_15779(FluidState var1)
		{
			return var1.get(LEVEL);
		}
		
		@Override
		public boolean isStill(FluidState var1)
		{
			return false;
		}
	}
	
	public static class Still extends ObsidianFluid
	{
		@Override
		public int method_15779(FluidState var1)
		{
			return 8;
		}
		
		@Override
		public boolean isStill(FluidState var1)
		{
			return true;
		}
	}
}
