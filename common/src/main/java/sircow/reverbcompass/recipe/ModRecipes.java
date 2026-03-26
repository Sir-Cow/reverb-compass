package sircow.reverbcompass.recipe;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.function.Supplier;

public class ModRecipes {
    public static final RecipeSerializer<ReverbCompassRecipe> REVERB_COMPASS = new RecipeSerializer<>(MapCodec.unit(new ReverbCompassRecipe()), StreamCodec.unit(new ReverbCompassRecipe()));

    private ModRecipes() {}

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
