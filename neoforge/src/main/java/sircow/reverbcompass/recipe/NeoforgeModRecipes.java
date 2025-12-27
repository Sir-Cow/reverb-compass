package sircow.reverbcompass.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import sircow.reverbcompass.Constants;

public class NeoforgeModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Constants.MOD_ID);

    private static <T extends CustomRecipe> void register(ModRecipes.RecipeEntry<T> entry) {
        entry.bind(RECIPE_SERIALIZERS.register(entry.id, entry.factory));
    }

    public static void registerNeoForgeModRecipes(IEventBus eventBus) {
        register(ModRecipes.REVERB_COMPASS);
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
