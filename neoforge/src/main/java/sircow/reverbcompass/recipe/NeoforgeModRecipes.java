package sircow.reverbcompass.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import sircow.reverbcompass.Constants;

public class NeoforgeModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Constants.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ReverbCompassRecipe>> REVERB_COMPASS = RECIPE_SERIALIZERS.register("reverb_compass", ReverbCompassRecipeSerializer::new);

    public static void registerNeoForgeModRecipes(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
        ModRecipes.REVERB_COMPASS_SERIALIZER = REVERB_COMPASS;
    }
}
