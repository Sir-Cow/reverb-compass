package sircow.reverbcompass.event;

import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;
import sircow.reverbcompass.Constants;
import sircow.reverbcompass.component.ModComponents;
import sircow.reverbcompass.recipe.ModRecipes;
import sircow.reverbcompass.sound.ModSounds;
import sircow.reverbcompass.trigger.ModTriggers;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForgeRegisterEventHandler {
    @SubscribeEvent
    public static void register(RegisterEvent event) {
        event.register(Registries.DATA_COMPONENT_TYPE, helper ->
                ModComponents.getComponents().forEach(helper::register)
        );
        event.register(Registries.RECIPE_SERIALIZER, helper ->
                ModRecipes.getRecipes().forEach(helper::register)
        );
        event.register(Registries.SOUND_EVENT, helper ->
                ModSounds.getSounds().forEach(helper::register)
        );
        event.register(Registries.TRIGGER_TYPE, helper ->
                ModTriggers.getTriggers().forEach(helper::register)
        );
    }
}
