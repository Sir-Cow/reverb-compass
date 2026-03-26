package sircow.reverbcompass.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import sircow.reverbcompass.Constants;

public final class NeoforgeModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Constants.MOD_ID);

    private static void register(String id, RecipeSerializer<?> serializer) {
        RECIPE_SERIALIZERS.register(id, () -> serializer);
    }

    public static void registerNeoForgeModRecipes(IEventBus eventBus) {
        register("reverb_compass", ModRecipes.REVERB_COMPASS);
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
