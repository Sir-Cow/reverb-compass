package sircow.reverbcompass;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class ReverbCompass {
    public ReverbCompass(IEventBus eventBus) {
        CommonClass.init();
    }
}
