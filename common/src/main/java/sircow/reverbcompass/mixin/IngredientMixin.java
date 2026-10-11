package sircow.reverbcompass.mixin;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sircow.reverbcompass.component.ModComponents;

@Mixin(Ingredient.class)
public abstract class IngredientMixin {
    @Shadow public abstract ItemStack[] getItems();

    @Inject(method = "test(Lnet/minecraft/world/item/ItemStack;)Z", at = @At("HEAD"), cancellable = true)
    private void reverbCompass$rejectReverbCompass(ItemStack input, CallbackInfoReturnable<Boolean> cir) {
        if (!input.has(ModComponents.REVERB_COMPASS)) return;

        for (ItemStack item : this.getItems()) {
            if (item.is(input.getItem())) {
                cir.setReturnValue(false);
                return;
            }
        }
    }
}
