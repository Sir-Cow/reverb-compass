package sircow.reverbcompass.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

public class ReverbCompassRecipeSerializer implements RecipeSerializer<ReverbCompassRecipe> {
    public static final MapCodec<ReverbCompassRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(ReverbCompassRecipe::category)
    ).apply(instance, ReverbCompassRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, ReverbCompassRecipe> STREAM_CODEC = StreamCodec.composite(
            CraftingBookCategory.STREAM_CODEC,
            ReverbCompassRecipe::category,
            ReverbCompassRecipe::new
    );

    @Override
    public @NotNull MapCodec<ReverbCompassRecipe> codec() {
        return CODEC;
    }

    @Override
    public @NotNull StreamCodec<RegistryFriendlyByteBuf, ReverbCompassRecipe> streamCodec() {
        return STREAM_CODEC;
    }
}
