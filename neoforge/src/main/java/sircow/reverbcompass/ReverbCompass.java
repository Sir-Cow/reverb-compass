package sircow.reverbcompass;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import sircow.reverbcompass.client.NeoForgeClient;
import sircow.reverbcompass.components.NeoforgeModComponents;
import sircow.reverbcompass.recipe.NeoforgeModRecipes;
import sircow.reverbcompass.sound.NeoforgeModSounds;
import sircow.reverbcompass.trigger.NeoForgeModTriggers;

@Mod(Constants.MOD_ID)
public class ReverbCompass {
    public ReverbCompass(IEventBus eventBus) {
        CommonClass.init();
        eventBus.addListener(this::clientSetup);
        NeoforgeModComponents.registerNeoForgeModComponents(eventBus);
        NeoforgeModRecipes.registerNeoForgeModRecipes(eventBus);
        NeoforgeModSounds.registerNeoForgeModSounds(eventBus);
        NeoForgeModTriggers.registerNeoForgeModTriggers(eventBus);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(NeoForgeClient::registerItemProperties);
    }
}
