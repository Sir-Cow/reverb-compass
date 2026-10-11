package sircow.reverbcompass.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.LodestoneTracker;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import sircow.reverbcompass.component.ModComponents;

import java.util.Optional;

public class ReverbCompassRecipe extends CustomRecipe {
    public ReverbCompassRecipe(CraftingBookCategory category) {
        super(category);
    }

    public static boolean isLodestoneCompassInput(ItemStack stack) {
        return stack.getItem() instanceof CompassItem && stack.has(DataComponents.LODESTONE_TRACKER) && !stack.has(ModComponents.REVERB_COMPASS);
    }

    @Override
    public boolean matches(CraftingInput input, @NotNull Level level) {
        if (input.ingredientCount() != 2) return false;

        boolean foundCompass = false;
        boolean foundEchoShard = false;

        for (ItemStack stack : input.items()) {
            if (stack.isEmpty()) continue;

            if (stack.is(Items.ECHO_SHARD)) foundEchoShard = true;
            else if (isLodestoneCompassInput(stack)) foundCompass = true;
            else return false;
        }
        return foundCompass && foundEchoShard;
    }

    @Override
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
        result.set(ModComponents.REVERB_COMPASS, true);

        return result;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.Provider provider) {
        ItemStack result = new ItemStack(Items.COMPASS);
        result.set(ModComponents.REVERB_COMPASS, true);
        return result;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        ItemStack lodestoneCompass = new ItemStack(Items.COMPASS);
        lodestoneCompass.set(DataComponents.LODESTONE_TRACKER, new LodestoneTracker(Optional.empty(), true));
        return NonNullList.of(Ingredient.EMPTY, Ingredient.of(lodestoneCompass), Ingredient.of(Items.ECHO_SHARD));
    }

    @Override
    public @NotNull RecipeSerializer<ReverbCompassRecipe> getSerializer() {
        return ModRecipes.REVERB_COMPASS_SERIALIZER;
    }
}
