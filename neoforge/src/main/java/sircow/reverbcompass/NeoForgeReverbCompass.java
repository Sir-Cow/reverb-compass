package sircow.reverbcompass;

import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import sircow.reverbcompass.platform.Services;

@Mod(Constants.MOD_ID)
public class NeoForgeReverbCompass {
    public NeoForgeReverbCompass(IEventBus eventBus) {
        if (Services.PLATFORM.isModLoaded("pinferno")) {
            throw new IllegalStateException(String.valueOf(Component.translatable("info.reverbcompass.conflict", Constants.MOD_ID, "Preserved: Inferno")));
        }

        CommonClass.init();
    }
}
