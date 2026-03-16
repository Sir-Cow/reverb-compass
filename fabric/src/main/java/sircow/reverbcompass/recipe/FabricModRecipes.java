package sircow.reverbcompass.recipe;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import sircow.reverbcompass.Constants;

public class FabricModRecipes {
    public static void registerFabricModRecipes() {
        RecipeSerializer<ReverbCompassRecipe> serializer = Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Constants.id("reverb_compass"), new ReverbCompassRecipeSerializer());

        ModRecipes.REVERB_COMPASS_SERIALIZER = () -> serializer;

        Registry.register(BuiltInRegistries.RECIPE_TYPE, Constants.id("reverb_compass"), ModRecipes.REVERB_COMPASS_TYPE);
    }
}
