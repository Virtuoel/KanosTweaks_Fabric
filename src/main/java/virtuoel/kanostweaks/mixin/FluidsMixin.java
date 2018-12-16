package virtuoel.kanostweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.fluid.Fluids;
import virtuoel.kanostweaks.init.FluidRegistrar;

@Mixin(Fluids.class)
public class FluidsMixin
{
	@Inject(at = @At("HEAD"), method = "<clinit>")
	private static void onClinit(CallbackInfo info)
	{
		new FluidRegistrar();
	}
}
