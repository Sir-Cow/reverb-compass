package sircow.reverbcompass.event;

import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;
import sircow.reverbcompass.Constants;
import sircow.reverbcompass.component.ModComponents;
import sircow.reverbcompass.recipe.ModRecipes;
import sircow.reverbcompass.sound.ModSounds;
import sircow.reverbcompass.trigger.ModTriggers;

@EventBusSubscriber(modid = Constants.MOD_ID)
public class NeoForgeRegisterEventHandler {
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
