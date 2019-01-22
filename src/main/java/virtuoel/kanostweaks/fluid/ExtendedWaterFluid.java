package virtuoel.kanostweaks.fluid;

import java.util.function.Function;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.Item;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.Properties;

public abstract class ExtendedWaterFluid extends WaterFluid
{
	BlockState blockState;
	Function<Fluid, BlockState> blockStateSupplier;
	
	Fluid still;
	Function<Fluid, Fluid> stillSupplier;
	
	Fluid flowing;
	Function<Fluid, Fluid> flowingSupplier;
	
	Item bucket;
	Function<Fluid, Item> bucketSupplier;
	
	public ExtendedWaterFluid(Function<Fluid, Fluid> still, Function<Fluid, Fluid> flowing, Function<Fluid, BlockState> blockState, Function<Fluid, Item> bucket)
	{
		stillSupplier = still;
		flowingSupplier = flowing;
		blockStateSupplier = blockState;
		bucketSupplier = bucket;
	}
	
	@Override
	public Fluid getStill()
	{
		return still == null ? (still = stillSupplier.apply(this)) : still;
	}
	
	@Override
	public Fluid getFlowing()
	{
		return flowing == null ? (flowing = flowingSupplier.apply(this)) : flowing;
	}
	
	@Override
	public Item getBucketItem()
	{
		return bucket == null ? (bucket = bucketSupplier.apply(this)) : bucket;
	}
	
	@Override
	public boolean matchesType(Fluid fluid_1)
	{
		return fluid_1 == getStill() || fluid_1 == getFlowing();
	}
	
	@Override
	public BlockState toBlockState(FluidState fluidState_1)
	{
		return (blockState == null ? (blockState = blockStateSupplier.apply(this)) : blockState).with(Properties.FLUID_BLOCK_LEVEL, method_15741(fluidState_1));
	}
	
	public static class Flowing extends ExtendedWaterFluid
	{
		public Flowing(Function<Fluid, Fluid> still, Function<Fluid, BlockState> blockState, Function<Fluid, Item> bucket)
		{
			super(still, f -> f, blockState, bucket);
		}
		
		@Override
		protected void appendProperties(StateFactory.Builder<Fluid, FluidState> stateFactory$Builder_1)
		{
			super.appendProperties(stateFactory$Builder_1);
			stateFactory$Builder_1.with(LEVEL);
		}
		
		@Override
		public int method_15779(FluidState fluidState_1)
		{
			return fluidState_1.get(LEVEL);
		}
		
		@Override
		public boolean isStill(FluidState fluidState_1)
		{
			return false;
		}
	}
	
	public static class Still extends ExtendedWaterFluid
	{
		public Still(Function<Fluid, Fluid> flowing, Function<Fluid, BlockState> blockState, Function<Fluid, Item> bucket)
		{
			super(f -> f, flowing, blockState, bucket);
		}
		
		@Override
		public int method_15779(FluidState fluidState_1)
		{
			return 8;
		}
		
		@Override
		public boolean isStill(FluidState fluidState_1)
		{
			return true;
		}
	}
}
