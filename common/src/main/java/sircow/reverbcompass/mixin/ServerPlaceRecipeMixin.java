package sircow.reverbcompass.mixin;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.protocol.game.ClientboundPlaceGhostRecipePacket;
import net.minecraft.recipebook.ServerPlaceRecipe;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sircow.reverbcompass.recipe.ReverbCompassRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Mixin(ServerPlaceRecipe.class)
public abstract class ServerPlaceRecipeMixin {
    @Shadow protected Inventory inventory;
    @Shadow protected RecipeBookMenu<?, ?> menu;

    @Shadow protected abstract void clearGrid();

    @Inject(method = "handleRecipeClicked(Lnet/minecraft/world/item/crafting/RecipeHolder;Z)V", at = @At("HEAD"), cancellable = true)
    private void reverbCompass$placeLodestoneCompassOnly(RecipeHolder<?> recipe, boolean notify, CallbackInfo ci) {
        if (!(recipe.value() instanceof ReverbCompassRecipe)) return;

        ci.cancel();

        List<Slot> gridSlots = new ArrayList<>();
        for (int i = 0; i < this.menu.getGridWidth() * this.menu.getGridHeight() + 1; i++) {
            if (i != this.menu.getResultSlotIndex()) {
                gridSlots.add(this.menu.getSlot(i));
            }
        }
        if (gridSlots.size() < 2) return;

        this.clearGrid();

        int compassCount = 0;
        int shardCount = 0;
        ItemStack compass = ItemStack.EMPTY;
        ItemStack shard = ItemStack.EMPTY;

        for (ItemStack stack : this.inventory.items) {
            if (stack.isEmpty() || stack.isDamaged() || stack.isEnchanted() || stack.has(DataComponents.CUSTOM_NAME)) continue;

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
            ServerPlayer player = (ServerPlayer) this.inventory.player;
            player.connection.send(new ClientboundPlaceGhostRecipePacket(player.containerMenu.containerId, recipe));
            return;
        }

        int amount = notify ? Math.min(Math.min(compassCount, shardCount), Math.min(compass.getMaxStackSize(), shard.getMaxStackSize())) : 1;

        this.reverbCompass$moveItems(gridSlots.get(0), amount, ReverbCompassRecipe::isLodestoneCompassInput);
        this.reverbCompass$moveItems(gridSlots.get(1), amount, stack -> stack.is(Items.ECHO_SHARD));
        this.inventory.setChanged();
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
        List<ItemStack> items = this.inventory.items;

        for (int slot = 0; slot < items.size(); slot++) {
            ItemStack stack = items.get(slot);
            if (!stack.isEmpty() && matcher.test(stack) && !stack.isDamaged() && !stack.isEnchanted() && !stack.has(DataComponents.CUSTOM_NAME) && (existingItem.isEmpty() || ItemStack.isSameItemSameComponents(existingItem, stack))) {
                return slot;
            }
        }
        return -1;
    }
}
