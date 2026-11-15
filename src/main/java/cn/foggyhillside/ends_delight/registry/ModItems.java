package cn.foggyhillside.ends_delight.registry;

import cn.foggyhillside.ends_delight.FoodList;
import cn.foggyhillside.ends_delight.item.*;
import cn.foggyhillside.ends_delight.utility.Utils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import net.minecraft.core.component.DataComponents;
import vectorwing.farmersdelight.common.item.KnifeItem;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModItems {
    private static net.minecraft.resources.ResourceKey<Item> key(String name) {
        return net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ITEM,
                net.minecraft.resources.Identifier.fromNamespaceAndPath(cn.foggyhillside.ends_delight.EndsDelight.MOD_ID, name));
    }

    // MC 26.1 requires Item.Properties#setId before the item instance is created
    private static Item.Properties newProps(String name) {
        return new Item.Properties().setId(key(name));
    }

    // MC 26.1: BlockItem must use the "block." translation prefix
    private static Item.Properties newBlockProps(String name) {
        return newProps(name).useBlockDescriptionPrefix();
    }
    public static <B extends Item> Supplier<B> regItem(String name, Function<String, B> factory) {
        return Utils.register(name, () -> factory.apply(name), BuiltInRegistries.ITEM);
    }

    //BlockItem
    public static final Supplier<Item> CHORUS_FRUIT_CRATE = regItem("chorus_fruit_crate",
            (n) -> new BlockItem(ModBlocks.CHORUS_FRUIT_CRATE.get(), newBlockProps(n)));
    public static final Supplier<Item> END_STOVE = regItem("end_stove",
            (n) -> new BlockItem(ModBlocks.END_STOVE.get(), newBlockProps(n)));
    //knife
    public static Item.Properties knifeItem(String name, net.minecraft.world.item.ToolMaterial material) {
        return newProps(name)
                .durability(material.durability())
                .repairable(material.repairItems())
                .enchantable(material.enchantmentValue())
                .attributes(KnifeItem.createAttributes(material, 0.5F, -2.0F));
    }

    public static final Supplier<Item> DRAGON_EGG_SHELL_KNIFE = regItem("dragon_egg_shell_knife",
            (n) -> new KnifeItem(knifeItem(n, ModMaterials.DRAGON_EGG_SHELL)));
    public static final Supplier<Item> PURPUR_KNIFE = regItem("purpur_knife",
            (n) -> new KnifeItem(knifeItem(n, ModMaterials.PURPUR)));
    public static final Supplier<Item> END_STONE_KNIFE = regItem("end_stone_knife",
            (n) -> new KnifeItem(knifeItem(n, ModMaterials.END_STONE)));
    public static final Supplier<Item> DRAGON_TOOTH_KNIFE = regItem("dragon_tooth_knife",
            (n) -> new KnifeItem(knifeItem(n, ModMaterials.DRAGON_TOOTH)));
    //Ingredients And Other Foods
    public static final Supplier<Item> ENDER_PEARL_GRAIN = regItem("ender_pearl_grain",
            (n) -> new Item(newProps(n)));
    public static final Supplier<Item> CHORUS_FRUIT_GRAIN = regItem("chorus_fruit_grain",
            (n) -> new Item(newProps(n).food(FoodList.CHORUS_FRUIT_GRAIN.food(), FoodList.CHORUS_FRUIT_GRAIN.consumable())));
    public static final Supplier<Item> CHORUS_SUCCULENT = regItem("chorus_succulent",
            (n) -> new BlockItem(ModBlocks.CHORUS_SUCCULENT.get(), newBlockProps(n).food(FoodList.CHORUS_SUCCULENT.food(), FoodList.CHORUS_SUCCULENT.consumable())));
    public static final Supplier<Item> DRIED_CHORUS_FLOWER = regItem("dried_chorus_flower",
            (n) -> new Item(newProps(n)));
    public static final Supplier<Item> DRAGON_TOOTH = regItem("dragon_tooth",
            (n) -> new Item(newProps(n)));
    public static final Supplier<Item> NON_HATCHABLE_DRAGON_EGG = regItem("non_hatchable_dragon_egg",
            (n) -> new Item(newProps(n).rarity(Rarity.RARE)));
    public static final Supplier<Item> HALF_DRAGON_EGG_SHELL = regItem("half_dragon_egg_shell",
            (n) -> new Item(newProps(n).rarity(Rarity.UNCOMMON)));
    public static final Supplier<Item> LIQUID_DRAGON_EGG = regItem("liquid_dragon_egg",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.LIQUID_DRAGON_EGG.food(), FoodList.LIQUID_DRAGON_EGG.consumable()), true));
    public static final Supplier<Item> FRIED_DRAGON_EGG = regItem("fried_dragon_egg",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.FRIED_DRAGON_EGG.food(), FoodList.FRIED_DRAGON_EGG.consumable()), true));
    public static final Supplier<Item> SHULKER_MEAT = regItem("shulker_meat",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.SHULKER_MEAT.food(), FoodList.SHULKER_MEAT.consumable()), true));
    public static final Supplier<Item> SHULKER_MEAT_SLICE = regItem("shulker_meat_slice",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.SHULKER_MEAT_SLICE.food(), FoodList.SHULKER_MEAT_SLICE.consumable()), true));
    public static final Supplier<Item> ROASTED_SHULKER_MEAT = regItem("roasted_shulker_meat",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.ROASTED_SHULKER_MEAT.food(), FoodList.ROASTED_SHULKER_MEAT.consumable()), true));
    public static final Supplier<Item> ROASTED_SHULKER_MEAT_SLICE = regItem("roasted_shulker_meat_slice",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.ROASTED_SHULKER_MEAT_SLICE.food(), FoodList.ROASTED_SHULKER_MEAT_SLICE.consumable()), true));
    public static final Supplier<Item> DRAGON_LEG = regItem("dragon_leg",
            (n) -> new Item(newProps(n).food(FoodList.DRAGON_LEG.food(), FoodList.DRAGON_LEG.consumable())));
    public static final Supplier<Item> SMOKED_DRAGON_LEG = regItem("smoked_dragon_leg",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.SMOKED_DRAGON_LEG.food(), FoodList.SMOKED_DRAGON_LEG.consumable()), true));
    public static final Supplier<Item> RAW_DRAGON_MEAT = regItem("raw_dragon_meat",
            (n) -> new Item(newProps(n).food(FoodList.RAW_DRAGON_MEAT.food(), FoodList.RAW_DRAGON_MEAT.consumable())));
    public static final Supplier<Item> ROASTED_DRAGON_MEAT = regItem("roasted_dragon_meat",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.ROASTED_DRAGON_MEAT.food(), FoodList.ROASTED_DRAGON_MEAT.consumable()), true));
    public static final Supplier<Item> RAW_DRAGON_MEAT_CUTS = regItem("raw_dragon_meat_cuts",
            (n) -> new Item(newProps(n).food(FoodList.RAW_DRAGON_MEAT_CUTS.food(), FoodList.RAW_DRAGON_MEAT_CUTS.consumable())));
    public static final Supplier<Item> ROASTED_DRAGON_MEAT_CUTS = regItem("roasted_dragon_meat_cuts",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.ROASTED_DRAGON_MEAT_CUTS.food(), FoodList.ROASTED_DRAGON_MEAT_CUTS.consumable()), true));
    public static final Supplier<Item> RAW_ENDERMITE_MEAT = regItem("raw_ender_mite_meat",
            (n) -> new Item(newProps(n).food(FoodList.RAW_ENDERMITE_MEAT.food(), FoodList.RAW_ENDERMITE_MEAT.consumable())));
    public static final Supplier<Item> DRIED_ENDERMITE_MEAT = regItem("dried_endermite_meat",
            (n) -> new Item(newProps(n).food(FoodList.DRIED_ENDERMITE_MEAT.food(), FoodList.DRIED_ENDERMITE_MEAT.consumable())));
    public static final Supplier<Item> ENDERMAN_GRISTLE = regItem("enderman_gristle",
            (n) -> new EndermanGristleItem(newProps(n).food(FoodList.ENDERMAN_GRISTLE.food(), FoodList.ENDERMAN_GRISTLE.consumable()), 0.3F, false));
    public static final Supplier<Item> CHORUS_SAUCE = regItem("chorus_sauce",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.CHORUS_SAUCE.food(), FoodList.CHORUS_SAUCE.consumable()).craftRemainder(Items.BOWL).stacksTo(64)));
    public static final Supplier<Item> SHULKER_OMELETTE_MIXTURE = regItem("shulker_omelette_mixture",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.SHULKER_OMELETTE_MIXTURE.food(), FoodList.SHULKER_OMELETTE_MIXTURE.consumable()), true));
    public static final Supplier<Item> SHULKER_OMELETTE = regItem("shulker_omelette",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.SHULKER_OMELETTE.food(), FoodList.SHULKER_OMELETTE.consumable()), true));
    public static final Supplier<Item> RAW_ENDER_SAUSAGE = regItem("raw_ender_sausage",
            (n) -> new EndermanGristleItem(newProps(n).food(FoodList.RAW_ENDER_SAUSAGE.food(), FoodList.RAW_ENDER_SAUSAGE.consumable()), 0.3F, false));
    public static final Supplier<Item> ENDER_SAUSAGE = regItem("ender_sausage",
            (n) -> new EndermanGristleItem(newProps(n).food(FoodList.ENDER_SAUSAGE.food(), FoodList.ENDER_SAUSAGE.consumable()), 0.2F, true, true));
    public static final Supplier<Item> ENDER_BAMBOO_RICE = regItem("ender_bamboo_rice",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.ENDER_BAMBOO_RICE.food(), FoodList.ENDER_BAMBOO_RICE.consumable()), true));
    public static final Supplier<Item> STUFFED_RICE_CAKE = regItem("stuffed_rice_cake",
            (n) -> new ConsumableChorusItem(newProps(n).food(FoodList.STUFFED_RICE_CAKE.food(), FoodList.STUFFED_RICE_CAKE.consumable()), true));
    public static final Supplier<Item> CHORUS_FLOWER_PIE = regItem("chorus_flower_pie",
            (n) -> new ConsumableChorusItem(newProps(n).food(FoodList.CHORUS_FLOWER_PIE.food(), FoodList.CHORUS_FLOWER_PIE.consumable()), true, true));
    public static final Supplier<Item> CHORUS_COOKIE = regItem("chorus_cookie",
            (n) -> new ConsumableChorusItem(newProps(n).food(FoodList.CHORUS_COOKIE.food(), FoodList.CHORUS_COOKIE.consumable()), true));
    public static final Supplier<Item> CHORUS_FRUIT_POPSICLE = regItem("chorus_fruit_popsicle",
            (n) -> new ChorusFruitPopsicleItem(newProps(n).food(FoodList.CHORUS_FRUIT_POPSICLE.food(), FoodList.CHORUS_FRUIT_POPSICLE.consumable()).stacksTo(16)));
    //Drink
    public static final Supplier<Item> CHORUS_FRUIT_WINE = regItem("chorus_fruit_wine",
            (n) -> new ChorusFruitWineItem(newProps(n).food(FoodList.CHORUS_FRUIT_WINE.food(), FoodList.CHORUS_FRUIT_WINE.consumable()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));
    public static final Supplier<Item> CHORUS_FRUIT_MILK_TEA = regItem("chorus_fruit_milk_tea",
            (n) -> new BubbleTeaItem(newProps(n).food(FoodList.CHORUS_FRUIT_MILK_TEA.food(), FoodList.CHORUS_FRUIT_MILK_TEA.consumable()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
    public static final Supplier<Item> BUBBLE_TEA = regItem("bubble_tea",
            (n) -> new BubbleTeaItem(newProps(n).food(FoodList.BUBBLE_TEA.food(), FoodList.BUBBLE_TEA.consumable()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
    public static final Supplier<Item> DRAGON_BREATH_SODA = regItem("dragon_breath_soda",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.DRAGON_BREATH_SODA.food(), FoodList.DRAGON_BREATH_SODA.consumable()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE), true));
    public static final Supplier<Item> CHORUS_FLOWER_TEA = regItem("chorus_flower_tea",
            (n) -> new ChorusFlowerTeaItem(newProps(n).food(FoodList.CHORUS_FLOWER_TEA.food(), FoodList.CHORUS_FLOWER_TEA.consumable()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE), true, true));
    //Pie
    public static final Supplier<Item> CHORUS_FRUIT_PIE = regItem("chorus_fruit_pie",
            (n) -> new BlockItem(ModBlocks.CHORUS_FRUIT_PIE.get(), newBlockProps(n)));
    public static final Supplier<Item> CHORUS_FRUIT_PIE_SLICE = regItem("chorus_fruit_pie_slice",
            (n) -> new ConsumableChorusItem(newProps(n).food(FoodValues.PIE_SLICE), true, true));
    //Congee
    public static final Supplier<Item> ENDER_CONGEE = regItem("ender_congee",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.ENDER_CONGEE.food(), FoodList.ENDER_CONGEE.consumable()).craftRemainder(Items.BOWL).stacksTo(16), true));
    //Soup
    public static final Supplier<Item> DRAGON_BREATH_AND_CHORUS_SOUP = regItem("dragon_breath_and_chorus_soup",
            (n) -> new DrinkableChorusItem(newProps(n).food(FoodList.DRAGON_BREATH_AND_CHORUS_SOUP.food(), FoodList.DRAGON_BREATH_AND_CHORUS_SOUP.consumable()).craftRemainder(Items.BOWL).stacksTo(16), true, true));
    public static final Supplier<Item> SHULKER_SOUP = regItem("shulker_soup",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.SHULKER_SOUP.food(), FoodList.SHULKER_SOUP.consumable()).craftRemainder(Items.BOWL).stacksTo(16), true));
    //ConsumableItem
    public static final Supplier<Item> ENDER_NOODLE = regItem("ender_noodle",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.ENDER_NOODLE.food(), FoodList.ENDER_NOODLE.consumable()).craftRemainder(Items.BOWL).stacksTo(16), true));
    public static final Supplier<Item> ENDERMAN_GRISTLE_STEW = regItem("enderman_gristle_stew",
            (n) -> new EndermanGristleItem(newProps(n).food(FoodList.ENDERMAN_GRISTLE_STEW.food(), FoodList.ENDERMAN_GRISTLE_STEW.consumable()).craftRemainder(Items.BOWL).stacksTo(16), 0.2F, true, true));
    public static final Supplier<Item> STIR_FRIED_SHULKER_MEAT = regItem("stir_fried_shulker_meat",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.STIR_FRIED_SHULKER_MEAT.food(), FoodList.STIR_FRIED_SHULKER_MEAT.consumable()).stacksTo(16).craftRemainder(Items.BOWL), true));
    public static final Supplier<Item> ROASTED_DRAGON_STEAK = regItem("roasted_dragon_steak",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.ROASTED_DRAGON_STEAK.food(), FoodList.ROASTED_DRAGON_STEAK.consumable()).stacksTo(16).craftRemainder(Items.BOWL), true));
    public static final Supplier<Item> END_MIXED_SALAD = regItem("end_mixed_salad",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.END_MIXED_SALAD.food(), FoodList.END_MIXED_SALAD.consumable()).craftRemainder(Items.BOWL).stacksTo(16), true));
    public static final Supplier<Item> ASSORTED_SALAD = regItem("assorted_salad",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.ASSORTED_SALAD.food(), FoodList.ASSORTED_SALAD.consumable()).craftRemainder(Items.SHULKER_SHELL).stacksTo(16), true));
    //BarbecueStick
    public static final Supplier<Item> END_BARBECUE_STICK = regItem("end_barbecue_stick",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.END_BARBECUE_STICK.food(), FoodList.END_BARBECUE_STICK.consumable()), true));
    //Feast
    public static final Supplier<Item> DRAGON_LEG_BLOCK = regItem("dragon_leg_with_sauce_block",
            (n) -> new BlockItem(ModBlocks.DRAGON_LEG_BLOCK.get(), newBlockProps(n).stacksTo(1)));
    public static final Supplier<Item> DRAGON_LEG_WITH_SAUCE = regItem("dragon_leg_with_sauce",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.DRAGON_LEG_WITH_SAUCE.food(), FoodList.DRAGON_LEG_WITH_SAUCE.consumable()).stacksTo(16).craftRemainder(Items.BOWL), true));
    public static final Supplier<Item> STEAMED_DRAGON_EGG_BLOCK = regItem("steamed_dragon_egg_block",
            (n) -> new BlockItem(ModBlocks.STEAMED_DRAGON_EGG_BLOCK.get(), newBlockProps(n).stacksTo(1)));
    public static final Supplier<Item> STEAMED_DRAGON_EGG = regItem("steamed_dragon_egg",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.STEAMED_DRAGON_EGG.food(), FoodList.STEAMED_DRAGON_EGG.consumable()).craftRemainder(Items.BOWL).stacksTo(16), true));
    public static final Supplier<Item> DRAGON_MEAT_STEW_BLOCK = regItem("dragon_meat_stew_block",
            (n) -> new BlockItem(ModBlocks.DRAGON_MEAT_STEW_BLOCK.get(), newBlockProps(n).stacksTo(1)));
    public static final Supplier<Item> DRAGON_MEAT_STEW = regItem("dragon_meat_stew",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.DRAGON_MEAT_STEW.food(), FoodList.DRAGON_MEAT_STEW.consumable()).craftRemainder(Items.BOWL).stacksTo(16), true));
    public static final Supplier<Item> GRILLED_SHULKER_BLOCK = regItem("grilled_shulker_block",
            (n) -> new BlockItem(ModBlocks.GRILLED_SHULKER_BLOCK.get(), newBlockProps(n).stacksTo(1)));
    public static final Supplier<Item> GRILLED_SHULKER = regItem("grilled_shulker",
            (n) -> new ConsumableItem(newProps(n).food(FoodList.GRILLED_SHULKER.food(), FoodList.GRILLED_SHULKER.consumable()).craftRemainder(Items.BOWL).stacksTo(16), true));

    public static void touch() {

    }

}
