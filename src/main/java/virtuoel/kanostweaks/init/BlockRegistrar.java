package virtuoel.kanostweaks.init;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.block.Material;
import net.minecraft.block.WallBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.registry.Registry;
import virtuoel.kanostweaks.KanosTweaks;
import virtuoel.kanostweaks.block.FluidTankBlock;
import virtuoel.kanostweaks.block.PublicFluidBlock;
import virtuoel.kanostweaks.block.PublicStairsBlock;

public class BlockRegistrar
{
	public static final Block MOLTEN_OBSIDIAN = registerBlock(
		"molten_obsidian",
		Block.Settings.copy(Blocks.LAVA),
		s -> new PublicFluidBlock(FluidRegistrar.MOLTEN_OBSIDIAN, s)
	);
	
	public static final Block OBSIDIAN_STAIRS = registerBlock(
		"obsidian_stairs",
		Block.Settings.copy(Blocks.OBSIDIAN),
		s -> new PublicStairsBlock(Blocks.OBSIDIAN.getDefaultState(), s),
		new Item.Settings().itemGroup(ItemGroup.BUILDING_BLOCKS),
		BlockItem::new
	);
	
	public static final Block OBSIDIAN_WALL = registerBlock(
		"obsidian_wall",
		Block.Settings.copy(Blocks.OBSIDIAN),
		WallBlock::new,
		new Item.Settings().itemGroup(ItemGroup.BUILDING_BLOCKS),
		BlockItem::new
	);
	
	public static final Block OAK_WOOD_WALL = registerBlock(
		"oak_wood_wall",
		Block.Settings.copy(Blocks.OAK_WOOD),
		WallBlock::new,
		new Item.Settings().itemGroup(ItemGroup.BUILDING_BLOCKS),
		BlockItem::new
	);
	
	public static final Block FLUID_TANK = registerBlock(
		"fluid_tank",
		Block.Settings.of(Material.GLASS).strength(2.0F, 2.0F),
		FluidTankBlock::new,
		new Item.Settings().itemGroup(ItemGroup.DECORATIONS),
		BlockItem::new
	);
	
	static
	{
		if(Blocks.FIRE instanceof FireBlock)
		{
			final FireBlock FIRE = ((FireBlock) Blocks.FIRE);
			
			FIRE.registerFlammable(OAK_WOOD_WALL, 5, 5);
		}
	}
	
	public static Block registerBlock(String name, Block.Settings blockSettings, Function<Block.Settings, Block> blockFunc, Item.Settings itemSettings)
	{
		return registerBlock(name, blockSettings, blockFunc, itemSettings, BlockItem::new);
	}
	
	public static Block registerBlock(String name, Block.Settings blockSettings, Function<Block.Settings, Block> blockFunc, Item.Settings itemSettings, BiFunction<Block, Item.Settings, BlockItem> itemFunc)
	{
		final Block block = registerBlock(name, blockSettings, blockFunc);
		
		final BlockItem item = itemFunc.apply(block, itemSettings);
		item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
		Registry.register(Registry.ITEM, KanosTweaks.id(name), item);
		
		return block;
	}
	
	public static Block registerBlock(String name, Block.Settings blockSettings, Function<Block.Settings, Block> blockFunc)
	{
		return registerBlock(name, () -> blockFunc.apply(blockSettings));
	}
	
	public static Block registerBlock(String name, Supplier<Block> blockSupplier)
	{
		return Registry.register(Registry.BLOCK, KanosTweaks.id(name), blockSupplier.get());
	}
}
