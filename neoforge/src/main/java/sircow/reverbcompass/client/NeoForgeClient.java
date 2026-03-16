package sircow.reverbcompass.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import sircow.reverbcompass.Constants;
import sircow.reverbcompass.components.ModComponents;

public class NeoForgeClient {
    public static void registerItemProperties() {
        ItemProperties.register(Items.COMPASS, Constants.id("reverb_compass"), (ItemStack stack, ClientLevel level, LivingEntity entity, int seed) -> stack.has(ModComponents.REVERB_COMPASS.get()) ? 1.0F : 0.0F);
    }
}
