package sircow.reverbcompass.recipe;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import sircow.reverbcompass.Constants;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModRecipes {
    private static final Map<Identifier, RecipeSerializer<?>> RECIPES = new LinkedHashMap<>();

    public static final RecipeSerializer<ReverbCompassRecipe> REVERB_COMPASS_SERIALIZER = register("reverb_compass", new CustomRecipe.Serializer<>(ReverbCompassRecipe::new));

    private static <T extends RecipeSerializer<?>> T register(String name, T serializer) {
        RECIPES.put(Constants.id(name), serializer);
        return serializer;
    }

    public static Map<Identifier, RecipeSerializer<?>> getRecipes() {
        return RECIPES;
    }
}
