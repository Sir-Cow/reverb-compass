package sircow.reverbcompass.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import sircow.reverbcompass.components.ModComponents;

public class ReverbCompassRecipe extends CustomRecipe {
    public ReverbCompassRecipe(CraftingBookCategory category) {
        super(category);
    }

    public boolean matches(CraftingInput input, @NotNull Level level) {
        if (input.ingredientCount() != 2) return false;

        boolean foundCompass = false;
        boolean foundEchoShard = false;

        for (ItemStack stack : input.items()) {
            if (stack.isEmpty()) continue;

            if (stack.is(Items.ECHO_SHARD)) {
                foundEchoShard = true;
            }
            else if (stack.getItem() instanceof CompassItem && stack.has(DataComponents.LODESTONE_TRACKER) && !stack.has(ModComponents.REVERB_COMPASS.get())) {
                foundCompass = true;
            }
            else {
                return false;
            }
        }
        return foundCompass && foundEchoShard;
    }

    public @NotNull ItemStack assemble(CraftingInput craftingInput, HolderLookup.@NotNull Provider provider) {
        ItemStack lodestoneCompass = ItemStack.EMPTY;

        for (ItemStack stack : craftingInput.items()) {
            if (stack.getItem() instanceof CompassItem && stack.has(DataComponents.LODESTONE_TRACKER)) {
                lodestoneCompass = stack;
                break;
            }
        }

        if (lodestoneCompass.isEmpty()) return ItemStack.EMPTY;

        ItemStack result = lodestoneCompass.copy();
        result.setCount(1);
        result.set(ModComponents.REVERB_COMPASS.get(), true);
        return result;
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return false;
    }

    @Override
    public @NotNull RecipeSerializer<ReverbCompassRecipe> getSerializer() {
        return ModRecipes.REVERB_COMPASS_SERIALIZER.get();
    }
}
