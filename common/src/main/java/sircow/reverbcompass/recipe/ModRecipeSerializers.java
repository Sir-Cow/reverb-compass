package sircow.reverbcompass.recipe;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;

public final class ModRecipeSerializers {
    public static final RecipeSerializer<ReverbCompassRecipe> REVERB_COMPASS = new RecipeSerializer<>(MapCodec.unit(new ReverbCompassRecipe()), StreamCodec.unit(new ReverbCompassRecipe()));

    private ModRecipeSerializers() {}
}
