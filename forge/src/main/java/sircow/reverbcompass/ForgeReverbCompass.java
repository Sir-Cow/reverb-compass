package sircow.reverbcompass;

import net.minecraft.network.chat.Component;
import net.minecraftforge.fml.common.Mod;
import sircow.reverbcompass.platform.Services;

@Mod(Constants.MOD_ID)
public class ForgeReverbCompass {
    public ForgeReverbCompass() {
        if (Services.PLATFORM.isModLoaded("pinferno")) {
            throw new IllegalStateException(String.valueOf(Component.translatable("info.reverbcompass.conflict", Constants.MOD_ID, "Preserved: Inferno")));
        }

        CommonClass.init();
    }
}
