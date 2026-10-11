package sircow.reverbcompass.mixin;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sircow.reverbcompass.component.ModComponents;

@Mixin(CompassItem.class)
public class CompassItemMixin {
    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void reverbCompass$preventReverbCompassLock(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack stack = context.getItemInHand();

        if (stack.has(ModComponents.REVERB_COMPASS)) {
            cir.setReturnValue(InteractionResult.PASS);
        }
    }

    @Inject(method = "getDescriptionId", at = @At("RETURN"), cancellable = true)
    private void reverbCompass$forceName(ItemStack stack, CallbackInfoReturnable<String> cir) {
        if (stack.has(ModComponents.REVERB_COMPASS)) {
            cir.setReturnValue("item.reverbcompass.reverb_compass");
        }
    }
}
