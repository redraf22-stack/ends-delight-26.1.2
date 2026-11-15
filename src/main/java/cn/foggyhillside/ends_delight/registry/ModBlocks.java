package cn.foggyhillside.ends_delight.registry;

import cn.foggyhillside.ends_delight.EndsDelight;
import cn.foggyhillside.ends_delight.block.*;
import cn.foggyhillside.ends_delight.utility.Utils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class ModBlocks {

    private static ResourceKey<Block> key(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(EndsDelight.MOD_ID, name));
    }

    /**
     * MC 26.1 requires BlockBehaviour.Properties#setId to be called before the
     * block instance is created ("Block id not set" otherwise).
     */
    public static <B extends Block> Supplier<B> regBlock(String name, Properties properties, Function<Properties, B> factory) {
        properties.setId(key(name));
        return Utils.register(name, () -> factory.apply(properties), BuiltInRegistries.BLOCK);
    }

    public static final Supplier<Block> CHORUS_FRUIT_PIE = regBlock("chorus_fruit_pie",
            Properties.ofFullCopy(Blocks.CAKE),
            (properties) -> new ChorusFruitPieBlock(properties, () -> ModItems.CHORUS_FRUIT_PIE_SLICE.get()));
    public static final Supplier<Block> CHORUS_SUCCULENT = regBlock("chorus_succulent",
            Properties.of().strength(0.0F, 0.0F).mapColor(MapColor.COLOR_PURPLE).lightLevel((state) -> {
                return 1 + 2 * state.getValue(ChorusSucculentBlock.SUCCULENT);
            }).sound(SoundType.FUNGUS).noOcclusion().pushReaction(PushReaction.DESTROY),
            (properties) -> new ChorusSucculentBlock(properties));
    public static final Supplier<Block> STEAMED_DRAGON_EGG_BLOCK = regBlock("steamed_dragon_egg_block",
            Properties.ofFullCopy(Blocks.DRAGON_EGG),
            (properties) -> new DragonEggFeastBlock(properties, () -> ModItems.STEAMED_DRAGON_EGG.get(), true));
    public static final Supplier<Block> DRAGON_MEAT_STEW_BLOCK = regBlock("dragon_meat_stew_block",
            Properties.ofFullCopy(Blocks.DRAGON_EGG),
            (properties) -> new DragonEggFeastBlock(properties, () -> ModItems.DRAGON_MEAT_STEW.get(), true));
    public static final Supplier<Block> GRILLED_SHULKER_BLOCK = regBlock("grilled_shulker_block",
            Properties.ofFullCopy(Blocks.SHULKER_BOX),
            (properties) -> new GrilledShulkerBlock(properties, () -> ModItems.GRILLED_SHULKER.get(), true));
    public static final Supplier<Block> END_STOVE = regBlock("end_stove",
            Properties.ofFullCopy(Blocks.BRICKS).lightLevel(litBlockEmission(13)),
            (properties) -> new EndStoveBlock(properties));
    public static final Supplier<Block> DRAGON_LEG_BLOCK = regBlock("dragon_leg_with_sauce_block",
            Properties.ofFullCopy(Blocks.CAKE).pushReaction(PushReaction.DESTROY),
            (properties) -> new DragonLegBlock(properties));
    public static final Supplier<Block> CHORUS_FRUIT_CRATE = regBlock("chorus_fruit_crate",
            Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD),
            (properties) -> new Block(properties));

    private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return (state) -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }

    public static void touch() {

    }

}
