package sircow.reverbcompass.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.Items;
import sircow.reverbcompass.Constants;
import sircow.reverbcompass.components.ModComponents;

public class ReverbCompassClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ItemProperties.register(Items.COMPASS, Constants.id("reverb_compass"), (stack, level, entity, seed) -> stack.has(ModComponents.REVERB_COMPASS.get()) ? 1.0F : 0.0F);
    }
}
