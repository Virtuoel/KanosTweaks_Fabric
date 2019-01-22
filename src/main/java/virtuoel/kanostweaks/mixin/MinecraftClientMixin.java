package virtuoel.kanostweaks.mixin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockHitResult;
import net.minecraft.util.EntityHitResult;
import net.minecraft.util.Hand;
import net.minecraft.util.HitResult;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin
{
	@Shadow private static final Logger LOGGER = LogManager.getLogger();
	@Shadow public ClientPlayerInteractionManager interactionManager;
	@Shadow public ClientWorld world;
	@Shadow public ClientPlayerEntity player;
	@Shadow public GameRenderer gameRenderer;
	@Shadow public HitResult hitResult;
	@Shadow private int itemUseCooldown;
	
	@Overwrite
	public void doItemUse()
	{
		if(!this.interactionManager.isBreakingBlock())
		{
			this.itemUseCooldown = 4;
			if(!this.player.method_3144()) // rowing boat
			{
				if(this.hitResult == null)
				{
					LOGGER.warn("Null returned as 'hitResult', this shouldn't happen!");
				}
				
				for(Hand hand_1 : Hand.values())
				{
					ItemStack itemStack_1 = this.player.getStackInHand(hand_1);
					if(this.hitResult != null)
					{
						switch(this.hitResult.getType())
						{
							case ENTITY:
								EntityHitResult entityHitResult_1 = (EntityHitResult) this.hitResult;
								Entity entity_1 = entityHitResult_1.getEntity();
								if(this.interactionManager.interactEntityAtLocation(this.player, entity_1, entityHitResult_1, hand_1) == ActionResult.SUCCESS)
								{
									return;
								}
								
								if(this.interactionManager.interactEntity(this.player, entity_1, hand_1) == ActionResult.SUCCESS)
								{
									return;
								}
								break;
							case BLOCK:
								BlockHitResult blockHitResult_1 = (BlockHitResult) this.hitResult;
								int int_1 = itemStack_1.getAmount();
								ActionResult actionResult_1 = this.interactionManager.interactBlock(this.player, this.world, hand_1, blockHitResult_1);
								if(actionResult_1 == ActionResult.SUCCESS)
								{
									this.player.swingHand(hand_1);
									if(!itemStack_1.isEmpty() && (itemStack_1.getAmount() != int_1 || this.interactionManager.hasCreativeInventory()))
									{
										this.gameRenderer.firstPersonRenderer.resetEquipProgress(hand_1);
									}
									
									return;
								}
								/*
								if(actionResult_1 == ActionResult.FAILURE)
								{
									return;
								}*/
							default:
								break;
						}
					}
					
					if(!itemStack_1.isEmpty() && this.interactionManager.interactItem(this.player, this.world, hand_1) == ActionResult.SUCCESS)
					{
						this.gameRenderer.firstPersonRenderer.resetEquipProgress(hand_1);
						return;
					}
				}
				
			}
		}
	}
}
