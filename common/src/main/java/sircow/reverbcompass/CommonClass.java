package sircow.reverbcompass;

import sircow.reverbcompass.platform.Services;

public class CommonClass {
    public static void init() {
        if (Services.PLATFORM.isModLoaded("reverbcompass")) {
            Constants.LOG.info("Initialising " + Constants.MOD_NAME);
        }
    }
}
