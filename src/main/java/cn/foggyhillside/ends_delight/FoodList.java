package cn.foggyhillside.ends_delight;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import vectorwing.farmersdelight.common.registry.ModEffects;

import java.util.ArrayList;
import java.util.List;

/**
 * Food properties (nutrition/saturation) plus the matching Consumable component
 * (eating animation, speed and status effects) for MC 26.1.
 */
public class FoodList {

    public record Entry(FoodProperties food, Consumable consumable) {
    }

    private static Entry build(int nutrition, float saturation, boolean alwaysEdible, boolean fast, boolean drink, MobEffectInstance... effects) {
        FoodProperties.Builder food = new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation);
        if (alwaysEdible) {
            food.alwaysEdible();
        }
        Consumable.Builder consumable = drink ? Consumables.defaultDrink() : Consumables.defaultFood();
        if (fast) {
            consumable.consumeSeconds(0.8F);
        }
        List<ConsumeEffect> consumeEffects = new ArrayList<>();
        for (MobEffectInstance effect : effects) {
            consumeEffects.add(new ApplyStatusEffectsConsumeEffect(effect, 1.0F));
        }
        consumeEffects.forEach(consumable::onConsume);
        return new Entry(food.build(), consumable.build());
    }

    // plain food
    private static Entry f(int nutrition, float saturation, MobEffectInstance... effects) {
        return build(nutrition, saturation, false, false, false, effects);
    }

    // always edible food
    private static Entry fa(int nutrition, float saturation, MobEffectInstance... effects) {
        return build(nutrition, saturation, true, false, false, effects);
    }

    // fast food (quick eat animation)
    private static Entry ff(int nutrition, float saturation, MobEffectInstance... effects) {
        return build(nutrition, saturation, false, true, false, effects);
    }

    // fast + always edible food
    private static Entry ffa(int nutrition, float saturation, MobEffectInstance... effects) {
        return build(nutrition, saturation, true, true, false, effects);
    }

    // drink (always edible, drinking animation)
    private static Entry d(int nutrition, float saturation, MobEffectInstance... effects) {
        return build(nutrition, saturation, true, false, true, effects);
    }

    public static final Entry CHORUS_FRUIT_GRAIN = ff(1, 0.6F);
    public static final Entry CHORUS_FRUIT_MILK_TEA = d(6, 0.5F);
    public static final Entry BUBBLE_TEA = d(8, 0.5F);
    public static final Entry DRAGON_BREATH_SODA = d(8, 0.5F,
            new MobEffectInstance(MobEffects.STRENGTH, 60 * 20, 1),
            new MobEffectInstance(MobEffects.RESISTANCE, 60 * 20, 0));
    public static final Entry SHULKER_MEAT = fa(4, 0.5F,
            new MobEffectInstance(MobEffects.LEVITATION, 4 * 20, 1));
    public static final Entry SHULKER_MEAT_SLICE = ffa(2, 0.6F,
            new MobEffectInstance(MobEffects.LEVITATION, 2 * 20, 1));
    public static final Entry STIR_FRIED_SHULKER_MEAT = fa(10, 0.8F,
            new MobEffectInstance(MobEffects.LEVITATION, 5 * 20, 1),
            new MobEffectInstance(ModEffects.NOURISHMENT, 180 * 20, 0));
    public static final Entry FRIED_DRAGON_EGG = fa(20, 0.5F,
            new MobEffectInstance(MobEffects.REGENERATION, 240 * 20, 2),
            new MobEffectInstance(MobEffects.STRENGTH, 240 * 20, 2),
            new MobEffectInstance(MobEffects.RESISTANCE, 240 * 20, 2));
    public static final Entry DRAGON_LEG = f(8, 0.6F);
    public static final Entry SMOKED_DRAGON_LEG = f(15, 0.6F,
            new MobEffectInstance(MobEffects.REGENERATION, 15 * 20, 1));
    public static final Entry CHORUS_FRUIT_POPSICLE = fa(3, 0.2F);
    public static final Entry CHORUS_FRUIT_WINE = new Entry(
            new FoodProperties.Builder().nutrition(0).saturationModifier(0.0F).alwaysEdible().build(),
            Consumables.defaultDrink()
                    .onConsume(new vectorwing.farmersdelight.common.item.component.consumable.RemoveRandomStatusEffectsConsumeEffect(
                            vectorwing.farmersdelight.refabricated.FDRefabricatedTags.MobEffects.HOT_COCOA_IGNORED, true))
                    .build());
    public static final Entry DRAGON_BREATH_AND_CHORUS_SOUP = d(16, 0.6F,
            new MobEffectInstance(MobEffects.REGENERATION, 20 * 20, 1),
            new MobEffectInstance(MobEffects.STRENGTH, 60 * 20, 1),
            new MobEffectInstance(MobEffects.RESISTANCE, 60 * 20, 0),
            new MobEffectInstance(ModEffects.COMFORT, 180 * 20, 0));
    public static final Entry LIQUID_DRAGON_EGG = fa(14, 0.5F,
            new MobEffectInstance(MobEffects.REGENERATION, 60 * 20, 0),
            new MobEffectInstance(MobEffects.STRENGTH, 60 * 20, 0),
            new MobEffectInstance(MobEffects.RESISTANCE, 60 * 20, 0),
            new MobEffectInstance(MobEffects.BLINDNESS, 20 * 20, 0));
    public static final Entry STUFFED_RICE_CAKE = fa(6, 0.7F);
    public static final Entry RAW_ENDERMITE_MEAT = f(3, 0.6F);
    public static final Entry DRIED_ENDERMITE_MEAT = f(5, 0.6F);
    public static final Entry RAW_DRAGON_MEAT = f(8, 0.6F);
    public static final Entry END_BARBECUE_STICK = fa(10, 0.7F,
            new MobEffectInstance(MobEffects.REGENERATION, 5 * 20, 1));
    public static final Entry ROASTED_DRAGON_MEAT = f(15, 0.6F,
            new MobEffectInstance(MobEffects.REGENERATION, 15 * 20, 1));
    public static final Entry CHORUS_FLOWER_TEA = d(0, 0.0F,
            new MobEffectInstance(MobEffects.REGENERATION, 5 * 20, 1),
            new MobEffectInstance(MobEffects.RESISTANCE, 20 * 20, 0));
    public static final Entry ROASTED_SHULKER_MEAT = fa(6, 0.5F,
            new MobEffectInstance(MobEffects.LEVITATION, 4 * 20, 1));
    public static final Entry ROASTED_SHULKER_MEAT_SLICE = ffa(3, 0.8F,
            new MobEffectInstance(MobEffects.LEVITATION, 2 * 20, 1));
    public static final Entry RAW_DRAGON_MEAT_CUTS = ff(3, 0.6F);
    public static final Entry ROASTED_DRAGON_MEAT_CUTS = ff(6, 0.6F,
            new MobEffectInstance(MobEffects.REGENERATION, 5 * 20, 1));
    public static final Entry DRAGON_MEAT_STEW = f(10, 0.6F,
            new MobEffectInstance(MobEffects.ABSORPTION, 75 * 20, 2),
            new MobEffectInstance(MobEffects.REGENERATION, 15 * 20, 1),
            new MobEffectInstance(MobEffects.STRENGTH, 30 * 20, 1),
            new MobEffectInstance(ModEffects.NOURISHMENT, 300 * 20, 0));
    public static final Entry CHORUS_SUCCULENT = ff(2, 0.8F);
    public static final Entry END_MIXED_SALAD = f(10, 0.6F,
            new MobEffectInstance(MobEffects.REGENERATION, 6 * 20, 0),
            new MobEffectInstance(MobEffects.SPEED, 10 * 20, 0));
    public static final Entry ASSORTED_SALAD = f(14, 0.5F,
            new MobEffectInstance(MobEffects.REGENERATION, 10 * 20, 0),
            new MobEffectInstance(MobEffects.SPEED, 25 * 20, 0),
            new MobEffectInstance(MobEffects.STRENGTH, 25 * 20, 0));
    public static final Entry CHORUS_FLOWER_PIE = f(4, 0.6F,
            new MobEffectInstance(MobEffects.REGENERATION, 5 * 20, 0));
    public static final Entry STEAMED_DRAGON_EGG = f(12, 0.6F,
            new MobEffectInstance(MobEffects.ABSORPTION, 60 * 20, 2),
            new MobEffectInstance(ModEffects.NOURISHMENT, 300 * 20, 0),
            new MobEffectInstance(MobEffects.REGENERATION, 60 * 20, 2),
            new MobEffectInstance(MobEffects.STRENGTH, 60 * 20, 2),
            new MobEffectInstance(MobEffects.RESISTANCE, 60 * 20, 2));
    public static final Entry GRILLED_SHULKER = f(8, 0.6F,
            new MobEffectInstance(MobEffects.REGENERATION, 5 * 20, 0),
            new MobEffectInstance(MobEffects.LEVITATION, 2 * 20, 1),
            new MobEffectInstance(ModEffects.NOURISHMENT, 300 * 20, 0));
    public static final Entry ROASTED_DRAGON_STEAK = fa(18, 0.5F,
            new MobEffectInstance(MobEffects.REGENERATION, 25 * 20, 1),
            new MobEffectInstance(ModEffects.NOURISHMENT, 300 * 20, 0));
    public static final Entry CHORUS_SAUCE = f(3, 0.5F);
    public static final Entry CHORUS_COOKIE = f(2, 0.6F);
    public static final Entry ENDER_CONGEE = fa(10, 0.6F,
            new MobEffectInstance(MobEffects.REGENERATION, 15 * 20, 1),
            new MobEffectInstance(MobEffects.RESISTANCE, 45 * 20, 1),
            new MobEffectInstance(MobEffects.HEALTH_BOOST, 45 * 20, 2),
            new MobEffectInstance(ModEffects.COMFORT, 180 * 20, 0));
    public static final Entry DRAGON_LEG_WITH_SAUCE = f(10, 0.7F,
            new MobEffectInstance(MobEffects.ABSORPTION, 75 * 20, 2),
            new MobEffectInstance(MobEffects.REGENERATION, 15 * 20, 1),
            new MobEffectInstance(ModEffects.NOURISHMENT, 300 * 20, 0));
    public static final Entry ENDERMAN_GRISTLE = ffa(2, 0.8F);
    public static final Entry RAW_ENDER_SAUSAGE = fa(6, 0.6F);
    public static final Entry ENDER_SAUSAGE = fa(9, 0.6F,
            new MobEffectInstance(MobEffects.REGENERATION, 8 * 20, 1));
    public static final Entry ENDERMAN_GRISTLE_STEW = fa(7, 0.7F,
            new MobEffectInstance(ModEffects.NOURISHMENT, 180 * 20, 0));
    public static final Entry SHULKER_SOUP = fa(8, 0.6F,
            new MobEffectInstance(MobEffects.LEVITATION, 5 * 20, 1),
            new MobEffectInstance(ModEffects.COMFORT, 180 * 20, 0));
    public static final Entry ENDER_NOODLE = fa(8, 0.6F,
            new MobEffectInstance(MobEffects.LEVITATION, 50, 1),
            new MobEffectInstance(ModEffects.COMFORT, 180 * 20, 0));
    public static final Entry SHULKER_OMELETTE_MIXTURE = fa(5, 0.5F,
            new MobEffectInstance(MobEffects.LEVITATION, 5 * 20, 1));
    public static final Entry SHULKER_OMELETTE = fa(11, 0.6F,
            new MobEffectInstance(MobEffects.LEVITATION, 5 * 20, 1),
            new MobEffectInstance(MobEffects.REGENERATION, 5 * 20, 0));
    public static final Entry ENDER_BAMBOO_RICE = f(15, 0.6F,
            new MobEffectInstance(MobEffects.REGENERATION, 15 * 20, 1),
            new MobEffectInstance(ModEffects.NOURISHMENT, 180 * 20, 0));
}
