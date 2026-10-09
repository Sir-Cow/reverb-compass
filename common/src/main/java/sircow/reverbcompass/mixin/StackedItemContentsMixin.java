package sircow.reverbcompass.mixin;

import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sircow.reverbcompass.component.ModComponents;

@Mixin(StackedItemContents.class)
public class StackedItemContentsMixin {
    @Inject(method = "accountStack(Lnet/minecraft/world/item/ItemStack;I)V", at = @At("HEAD"), cancellable = true)
    private void reverbCompass$skipReverbCompass(ItemStack stack, int maxCount, CallbackInfo ci) {
        if (stack.has(ModComponents.REVERB_COMPASS)) {
            ci.cancel();
        }
    }
}
