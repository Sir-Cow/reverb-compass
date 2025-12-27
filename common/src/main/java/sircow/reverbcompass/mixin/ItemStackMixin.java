package sircow.reverbcompass.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.LodestoneTracker;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sircow.reverbcompass.components.ModComponents;
import sircow.reverbcompass.sound.ModSounds;
import sircow.reverbcompass.trigger.ModTriggers;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void reverbCompass$onUse(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack stack = (ItemStack) (Object) this;
        if (!(stack.getItem() instanceof CompassItem) || !stack.has(ModComponents.REVERB_COMPASS.get())) return;

        LodestoneTracker tracker = stack.get(DataComponents.LODESTONE_TRACKER);
        if (tracker == null || tracker.target().isEmpty()) return;

        GlobalPos globalPos = tracker.target().get();
        if (level.dimension() != globalPos.dimension()) return;

        if (!level.isClientSide()) {
            BlockPos pos = globalPos.pos().above();
            player.teleportTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.REVERB_COMPASS_USE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.REVERB_COMPASS_USE1.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.REVERB_COMPASS_USE2.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.REVERB_COMPASS_USE3.get(), SoundSource.PLAYERS, 1.0F, 1.0F);

            if (player instanceof ServerPlayer serverPlayer) {
                ModTriggers.USE_REVERB_COMPASS.get().trigger(serverPlayer);
            }

            ItemStack result = stack.copy();
            result.setCount(1);
            result.remove(ModComponents.REVERB_COMPASS.get());
            stack.shrink(1);

            if (stack.isEmpty()) {
                player.setItemInHand(hand, result);
            }
            else {
                player.getInventory().placeItemBackInInventory(result);
            }
        }

        cir.setReturnValue(InteractionResult.SUCCESS_SERVER);
    }
}
