package sircow.reverbcompass.compat.mixin;

import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sircow.reverbcompass.Constants;
import sircow.reverbcompass.component.ModComponents;

@Mixin(targets = "mezz.jei.library.plugins.vanilla.ingredients.ItemStackHelper")
public abstract class ItemStackHelperMixin {
    @Inject(method = "getDisplayModId(Lnet/minecraft/world/item/ItemStack;)Ljava/lang/String;", at = @At("HEAD"), cancellable = true)
    private void reverbCompass$overrideDisplayModId(ItemStack stack, CallbackInfoReturnable<String> cir) {
        if (stack.has(ModComponents.REVERB_COMPASS)) {
            cir.setReturnValue(Constants.MOD_ID);
        }
    }
}
