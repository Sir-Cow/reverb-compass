package sircow.reverbcompass.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Supplier;

public class ModRecipes {
    public static Supplier<RecipeSerializer<ReverbCompassRecipe>> REVERB_COMPASS_SERIALIZER;

    public static final RecipeType<ReverbCompassRecipe> REVERB_COMPASS_TYPE = new RecipeType<>() {
        @Override
        public String toString() {
            return "reverb_compass";
        }
    };
}
