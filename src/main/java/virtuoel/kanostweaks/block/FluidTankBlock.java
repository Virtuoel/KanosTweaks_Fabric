package virtuoel.kanostweaks.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.util.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import virtuoel.towelette.api.Fluidloggable;

public class FluidTankBlock extends Block implements Fluidloggable
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
	public VoxelShape getRayTraceShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1)
	{
		return INNER;
	}
	
	@Override
	public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, VerticalEntityPosition verticalEntityPosition_1)
	{
		return OUTER;
	}
	
	@Override
	public boolean isSimpleFullBlock(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1)
	{
		return false;
	}
}
