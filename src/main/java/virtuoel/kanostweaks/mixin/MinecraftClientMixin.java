package virtuoel.kanostweaks.mixin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.HitResult;
import net.minecraft.util.math.BlockPos;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin
{
	@Shadow private static final Logger LOGGER = LogManager.getLogger();
	@Shadow public ClientPlayerInteractionManager interactionManager;
	@Shadow public ClientWorld world;
	@Shadow public ClientPlayerEntity player;
	@Shadow public WorldRenderer worldRenderer;
	@Shadow public HitResult hitResult;
	@Shadow private int field_1752;
	
	public void minecraftClient$doItemUse()
	{
		doItemUse();
	}
	
	@Overwrite
	public void doItemUse()
	{
		if(!this.interactionManager.isBreakingBlock())
		{
			this.field_1752 = 4;
			if(!this.player.method_3144()) // rowing boat
			{
				if(this.hitResult == null)
				{
					LOGGER.warn("Null returned as 'hitResult', this shouldn't happen!");
				}
				
				for(Hand hand : Hand.values())
				{
					ItemStack held = this.player.getStackInHand(hand);
					if(this.hitResult != null)
					{
						switch(this.hitResult.type)
						{
							case ENTITY:
								if(this.interactionManager.interactEntityAtLocation(this.player, this.hitResult.entity, this.hitResult, hand) == ActionResult.SUCCESS)
								{
									return;
								}
								
								if(this.interactionManager.interactEntity(this.player, this.hitResult.entity, hand) == ActionResult.SUCCESS)
								{
									return;
								}
								break;
							case BLOCK:
								BlockPos targetpos = this.hitResult.getBlockPos();
								if(!this.world.getBlockState(targetpos).isAir())
								{
									int i = held.getAmount();
									ActionResult result = this.interactionManager.interactBlock(this.player, this.world, targetpos, this.hitResult.side, this.hitResult.pos, hand);
									if(result == ActionResult.SUCCESS)
									{
										this.player.swingHand(hand);
										if(!held.isEmpty() && (held.getAmount() != i || this.interactionManager.hasCreativeInventory()))
										{
											this.worldRenderer.firstPersonRenderer.resetEquipProgress(hand);
										}
										
										return;
									}
									/*
									if(result == ActionResult.FAILURE)
									{
										return;
									}
									*/
								}
							default:
								break;
						}
					}
					
					if(!held.isEmpty() && this.interactionManager.interactItem(this.player, this.world, hand) == ActionResult.SUCCESS)
					{
						this.worldRenderer.firstPersonRenderer.resetEquipProgress(hand);
						return;
					}
				}
			}
		}
	}
}
