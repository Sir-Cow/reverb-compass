package sircow.reverbcompass.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import sircow.reverbcompass.component.ModComponents;

@Mixin(Inventory.class)
public class InventoryMixin {
    @Redirect(method = "findSlotMatchingCraftingIngredient(Lnet/minecraft/core/Holder;Lnet/minecraft/world/item/ItemStack;)I", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/core/Holder;)Z"))
    private boolean reverbCompass$rejectReverbCompass(ItemStack stack, Holder<Item> item) {
        return !stack.has(ModComponents.REVERB_COMPASS) && stack.is(item);
    }
}
