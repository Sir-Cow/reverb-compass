package sircow.reverbcompass.recipe;

import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

public final class ModRecipeSerializers {
    public static final RecipeSerializer<ReverbCompassRecipe> REVERB_COMPASS = new CustomRecipe.Serializer<>(ReverbCompassRecipe::new);

    private ModRecipeSerializers() {}
}
