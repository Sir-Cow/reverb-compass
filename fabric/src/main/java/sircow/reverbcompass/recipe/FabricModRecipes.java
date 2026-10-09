package sircow.reverbcompass.recipe;

import net.fabricmc.fabric.api.recipe.v1.sync.RecipeSynchronization;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class FabricModRecipes {
    public static void registerFabricModRecipes() {
        ModRecipes.getRecipes().forEach((id, serializer) ->
                Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, id, serializer)
        );
        ModRecipes.getRecipes().values().forEach(RecipeSynchronization::synchronizeRecipeSerializer);
    }
}
