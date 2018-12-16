package virtuoel.kanostweaks.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.block.BlockRenderLayer;
import net.minecraft.state.StateFactory;
import net.minecraft.util.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import virtuoel.towelette.state.property.FluidProperty;

public class FluidTankBlock extends Block
{
	public FluidTankBlock(Block.Settings settings)
	{
		super(settings);
	}
	
	public static final VoxelShape INNER = Block.createCubeShape(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	public static final VoxelShape OUTER = VoxelShapes.combine(VoxelShapes.fullCube(), INNER, BooleanBiFunction.ONLY_FIRST);
	
	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public VoxelShape getRayTraceShape(BlockState var1, BlockView var2, BlockPos var3)
	{
		return INNER;
	}
	
	@Override
	public VoxelShape getBoundingShape(BlockState var1, BlockView var2, BlockPos var3)
	{
		return OUTER;
	}
	
	@Override
	public boolean isSimpleFullBlock(BlockState var1, BlockView var2, BlockPos var3)
	{
		return false;
	}
	
	@Override
	protected void appendProperties(StateFactory.Builder<Block, BlockState> var1)
	{
		super.appendProperties(var1);
		FluidProperty.FLUID.tryAppendPropertySafely(var1);
	}
}
