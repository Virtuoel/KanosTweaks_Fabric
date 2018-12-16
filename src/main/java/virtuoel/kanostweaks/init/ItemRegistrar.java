package virtuoel.kanostweaks.init;

import java.util.function.Function;

import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;
import virtuoel.kanostweaks.KanosTweaks;

public class ItemRegistrar
{
	public static final Item MOLTEN_OBSIDIAN_BUCKET = registerItem(
		"molten_obsidian_bucket",
		new Item.Settings().recipeRemainder(Items.BUCKET).stackSize(1).itemGroup(ItemGroup.MISC),
		s -> new BucketItem(FluidRegistrar.MOLTEN_OBSIDIAN, s)
	);
	
	public static Item registerItem(String name, Item.Settings itemSettings)
	{
		return registerItem(name, itemSettings, Item::new);
	}
	
	public static Item registerItem(String name, Item.Settings itemSettings, Function<Item.Settings, Item> itemFunc)
	{
		final Item item = itemFunc.apply(itemSettings);
		return Registry.register(Registry.ITEM, KanosTweaks.id(name), item);
	}
}
