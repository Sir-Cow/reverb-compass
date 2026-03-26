package sircow.reverbcompass.recipe;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import sircow.reverbcompass.Constants;

public class FabricModRecipes {
    private static <T extends RecipeSerializer<?>> void register(String id, T serializer) {
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Constants.id(id), serializer);
    }

    public static void registerFabricModRecipes() {
        register("reverb_compass", ModRecipes.REVERB_COMPASS);
    }
}
