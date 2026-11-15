package cn.foggyhillside.ends_delight.registry;

import cn.foggyhillside.ends_delight.EndsDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModMaterials {

    private static TagKey<Item> repair(String path) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(EndsDelight.MOD_ID, path));
    }

    public static final ToolMaterial DRAGON_EGG_SHELL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL, 1250, 6.0F, 2.5F, 14, repair("tools/dragon_egg_shell_repair"));

    public static final ToolMaterial END_STONE = new ToolMaterial(
            BlockTags.INCORRECT_FOR_STONE_TOOL, 200, 4.0F, 1.0F, 5, repair("tools/end_stone_repair"));

    public static final ToolMaterial PURPUR = new ToolMaterial(
            BlockTags.INCORRECT_FOR_STONE_TOOL, 200, 4.0F, 1.0F, 5, repair("tools/purpur_repair"));

    public static final ToolMaterial DRAGON_TOOTH = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1561, 8.0F, 3.5F, 10, repair("tools/dragon_tooth_repair"));
}
