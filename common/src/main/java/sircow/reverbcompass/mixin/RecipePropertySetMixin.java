package sircow.reverbcompass.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipePropertySet;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sircow.reverbcompass.component.ModComponents;

import java.util.Set;

@Mixin(RecipePropertySet.class)
public class RecipePropertySetMixin {
    @Shadow @Final private Set<Holder<Item>> items;

    @Inject(method = "test(Lnet/minecraft/world/item/ItemStack;)Z", at = @At("HEAD"), cancellable = true)
    private void reverbCompass$rejectReverbCompass(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
        if (itemStack.has(ModComponents.REVERB_COMPASS) && this.items.contains(itemStack.typeHolder())) cir.setReturnValue(false);
    }
}
