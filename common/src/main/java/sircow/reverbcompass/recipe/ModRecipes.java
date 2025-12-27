package sircow.reverbcompass.recipe;

import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.function.Supplier;

public class ModRecipes {
    public static final RecipeEntry<ReverbCompassRecipe> REVERB_COMPASS = new RecipeEntry<>("reverb_compass", () -> new CustomRecipe.Serializer<>(ReverbCompassRecipe::new));

    public static class RecipeEntry<T extends CustomRecipe> {
        public final String id;
        public final Supplier<RecipeSerializer<T>> factory;
        private Supplier<RecipeSerializer<T>> serializer;

        public RecipeEntry(String id, Supplier<RecipeSerializer<T>> factory) {
            this.id = id;
            this.factory = factory;
        }

        public void bind(Supplier<RecipeSerializer<T>> supplier) {
            this.serializer = supplier;
        }

        public RecipeSerializer<T> get() {
            return this.serializer.get();
        }
    }
}
