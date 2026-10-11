package sircow.reverbcompass.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import sircow.reverbcompass.Constants;
import sircow.reverbcompass.component.ModComponents;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ForgeReverbCompassClient {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(ForgeReverbCompassClient::registerItemProperties);
    }

    private static void registerItemProperties() {
        ItemProperties.register(Items.COMPASS, Constants.id("reverb_compass"), (ItemStack stack, ClientLevel level, LivingEntity entity, int seed) -> stack.has(ModComponents.REVERB_COMPASS) ? 1.0F : 0.0F);
    }
}
