package sircow.reverbcompass.recipe;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.CustomRecipe;
import sircow.reverbcompass.Constants;

public class FabricModRecipes {
    private static <T extends CustomRecipe> void register(ModRecipes.RecipeEntry<T> entry) {
        var registered = Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Constants.id(entry.id), entry.factory.get());
        entry.bind(() -> registered);
    }

    public static void registerFabricModRecipes() {
        register(ModRecipes.REVERB_COMPASS);
    }
}
