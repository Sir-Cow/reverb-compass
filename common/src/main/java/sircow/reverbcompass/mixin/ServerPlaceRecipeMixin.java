package sircow.reverbcompass.mixin;

import net.minecraft.recipebook.ServerPlaceRecipe;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sircow.reverbcompass.recipe.ReverbCompassRecipe;

import java.util.List;
import java.util.function.Predicate;

@Mixin(ServerPlaceRecipe.class)
public abstract class ServerPlaceRecipeMixin {
    @Shadow @Final private Inventory inventory;
    @Shadow @Final private boolean useMaxItems;
    @Shadow @Final private List<Slot> inputGridSlots;

    @Shadow private void clearGrid() {}

    @Inject(method = "tryPlaceRecipe", at = @At("HEAD"), cancellable = true)
    private void reverbCompass$placeLodestoneCompassOnly(RecipeHolder<?> recipe, StackedItemContents availableItems, CallbackInfoReturnable<RecipeBookMenu.PostPlaceAction> cir) {
        if (!(recipe.value() instanceof ReverbCompassRecipe)) return;

        if (this.inputGridSlots.size() < 2) {
            cir.setReturnValue(RecipeBookMenu.PostPlaceAction.NOTHING);
            return;
        }

        this.clearGrid();

        int compassCount = 0;
        int shardCount = 0;
        ItemStack compass = ItemStack.EMPTY;
        ItemStack shard = ItemStack.EMPTY;

        for (ItemStack stack : this.inventory.getNonEquipmentItems()) {
            if (stack.isEmpty() || !Inventory.isUsableForCrafting(stack)) continue;

            if (ReverbCompassRecipe.isLodestoneCompassInput(stack)) {
                compassCount += stack.getCount();
                if (compass.isEmpty()) compass = stack;
            }
            else if (stack.is(Items.ECHO_SHARD)) {
                shardCount += stack.getCount();
                if (shard.isEmpty()) shard = stack;
            }
        }

        if (compassCount <= 0 || shardCount <= 0) {
            this.inventory.setChanged();
            cir.setReturnValue(RecipeBookMenu.PostPlaceAction.PLACE_GHOST_RECIPE);
            return;
        }

        int amount = this.useMaxItems ? Math.min(Math.min(compassCount, shardCount), Math.min(compass.getMaxStackSize(), shard.getMaxStackSize())) : 1;

        this.reverbCompass$moveItems(this.inputGridSlots.get(0), amount, ReverbCompassRecipe::isLodestoneCompassInput);
        this.reverbCompass$moveItems(this.inputGridSlots.get(1), amount, stack -> stack.is(Items.ECHO_SHARD));
        this.inventory.setChanged();
        cir.setReturnValue(RecipeBookMenu.PostPlaceAction.NOTHING);
    }

    @Unique
    private void reverbCompass$moveItems(Slot targetSlot, int amount, Predicate<ItemStack> matcher) {
        int remaining = amount;

        while (remaining > 0) {
            remaining = this.reverbCompass$moveItemToGrid(targetSlot, matcher, remaining);
            if (remaining < 0) return;
        }
    }

    @Unique
    private int reverbCompass$moveItemToGrid(Slot targetSlot, Predicate<ItemStack> matcher, int count) {
        ItemStack targetStack = targetSlot.getItem();
        int inventorySlot = this.reverbCompass$findSlot(matcher, targetStack);
        if (inventorySlot == -1) return -1;

        ItemStack inventoryStack = this.inventory.getItem(inventorySlot);
        ItemStack takenStack = count < inventoryStack.getCount() ? this.inventory.removeItem(inventorySlot, count) : this.inventory.removeItemNoUpdate(inventorySlot);
        int takenCount = takenStack.getCount();

        if (targetStack.isEmpty()) targetSlot.set(takenStack);
        else targetStack.grow(takenCount);

        return count - takenCount;
    }

    @Unique
    private int reverbCompass$findSlot(Predicate<ItemStack> matcher, ItemStack existingItem) {
        List<ItemStack> items = this.inventory.getNonEquipmentItems();

        for (int slot = 0; slot < items.size(); slot++) {
            ItemStack stack = items.get(slot);
            if (!stack.isEmpty() && matcher.test(stack) && Inventory.isUsableForCrafting(stack) && (existingItem.isEmpty() || ItemStack.isSameItemSameComponents(existingItem, stack))) {
                return slot;
            }
        }
        return -1;
    }
}
