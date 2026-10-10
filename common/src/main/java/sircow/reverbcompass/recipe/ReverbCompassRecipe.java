package sircow.reverbcompass.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.LodestoneTracker;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapelessCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import sircow.reverbcompass.component.ModComponents;

import java.util.List;
import java.util.Optional;

public final class ReverbCompassRecipe extends CustomRecipe {
    public static final ReverbCompassRecipe INSTANCE = new ReverbCompassRecipe(CraftingBookCategory.MISC);

    public ReverbCompassRecipe(CraftingBookCategory category) {
        super(category);
    }

    public static boolean isLodestoneCompassInput(ItemStack stack) {
        return stack.getItem() instanceof CompassItem && stack.has(DataComponents.LODESTONE_TRACKER) && !stack.has(ModComponents.REVERB_COMPASS);
    }

    @Override
    public boolean matches(CraftingInput input, @NonNull Level level) {
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
    public @NonNull ItemStack assemble(CraftingInput craftingInput, HolderLookup.Provider provider) {
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
    public @NonNull CraftingBookCategory category() {
        return CraftingBookCategory.MISC;
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
    public @NonNull PlacementInfo placementInfo() {
        return PlacementInfo.create(List.of(Ingredient.of(Items.COMPASS), Ingredient.of(Items.ECHO_SHARD)));
    }

    @Override
    public @NonNull List<RecipeDisplay> display() {
        ItemStack lodestoneCompass = new ItemStack(Items.COMPASS);
        lodestoneCompass.set(DataComponents.LODESTONE_TRACKER, new LodestoneTracker(Optional.empty(), true));

        ItemStack result = new ItemStack(Items.COMPASS);
        result.set(ModComponents.REVERB_COMPASS, true);

        return List.of(
            new ShapelessCraftingRecipeDisplay(
                List.of(
                    new SlotDisplay.ItemStackSlotDisplay(lodestoneCompass),
                    new SlotDisplay.ItemSlotDisplay(Items.ECHO_SHARD)
                ),
                new SlotDisplay.ItemStackSlotDisplay(result),
                new SlotDisplay.ItemSlotDisplay(Items.CRAFTING_TABLE)
            )
        );
    }

    @Override
    public @NonNull RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return ModRecipes.REVERB_COMPASS_SERIALIZER;
    }
}
