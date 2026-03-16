package sircow.reverbcompass.mixin;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sircow.reverbcompass.components.ModComponents;

@Mixin(CompassItem.class)
public class CompassItemMixin {
    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void reverbCompass$preventReverbCompassLock(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack stack = context.getItemInHand();

        if (stack.has(ModComponents.REVERB_COMPASS.get()) && context.getLevel().getBlockState(context.getClickedPos()).is(Blocks.LODESTONE)) {
            cir.setReturnValue(InteractionResult.PASS);
        }
    }

    @Inject(method = "getDescriptionId", at = @At("RETURN"), cancellable = true)
    private void reverbCompass$forceName(ItemStack stack, CallbackInfoReturnable<String> cir) {
        if (stack.has(ModComponents.REVERB_COMPASS.get())) {
            cir.setReturnValue("item.reverbcompass.reverb_compass");
        }
    }
}
