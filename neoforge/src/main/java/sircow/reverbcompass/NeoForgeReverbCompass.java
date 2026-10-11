package sircow.reverbcompass;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import sircow.reverbcompass.client.NeoForgeReverbCompassClient;

@Mod(Constants.MOD_ID)
public class NeoForgeReverbCompass {
    public NeoForgeReverbCompass(IEventBus eventBus) {
        CommonClass.init();
        eventBus.addListener(this::clientSetup);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(NeoForgeReverbCompassClient::registerItemProperties);
    }
}
