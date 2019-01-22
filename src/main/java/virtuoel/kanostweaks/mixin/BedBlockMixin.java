package virtuoel.kanostweaks.mixin;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockEntityProvider;

@Mixin(BedBlock.class)
@Implements(@Interface(iface = BlockEntityProvider.class, prefix = "blockEntityProvider$"))
public abstract class BedBlockMixin// extends BlockMixin
{/*
	@Inject(at = @At("HEAD"), method = "hasBlockEntity", cancellable = true)
	private void onHasBlockEntity(CallbackInfoReturnable<Boolean> info)
	{
		info.setReturnValue(false);
	}
	
	@Inject(at = @At("HEAD"), method = "hasBlockEntityBreakingRender", cancellable = true)
	private void onHasBlockEntityBreakingRender(CallbackInfoReturnable<Boolean> info)
	{
		info.setReturnValue(false);
	}
	
	@Inject(at = @At("HEAD"), method = "blockEntityProvider$createBlockEntity", cancellable = true)
	private void onBlockEntityProvider$createBlockEntity(CallbackInfoReturnable<Boolean> info)
	{
		info.setReturnValue(null);
	}*/
	/*
	@Overwrite
	public boolean hasBlockEntity()
	{
		return false;
	}
	
	@Overwrite
	public boolean hasBlockEntityBreakingRender(final BlockState state)
	{
		return false;
	}
	
	@Overwrite
	public BlockEntity blockEntityProvider$createBlockEntity(BlockView blockView_1)
	{
		return null;
	}*/
}
