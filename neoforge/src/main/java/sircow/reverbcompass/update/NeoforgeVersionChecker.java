package sircow.reverbcompass.update;

import net.neoforged.fml.ModList;
import sircow.reverbcompass.Constants;

public final class NeoforgeVersionChecker implements VersionChecker {
    @Override
    public String getVersion() {
        return ModList.get()
                .getModContainerById(Constants.MOD_ID)
                .orElseThrow()
                .getModInfo()
                .getVersion()
                .toString();
    }
}
