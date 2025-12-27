package sircow.reverbcompass.sound;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import sircow.reverbcompass.Constants;

public class FabricModSounds {
    private static void register(ModSounds.SoundEntry entry) {
        var registered = Registry.register(BuiltInRegistries.SOUND_EVENT, Constants.id(entry.id), entry.factory.get());
        entry.bind(() -> registered);
    }

    public static void registerFabricModSounds() {
        register(ModSounds.REVERB_COMPASS_USE);
        register(ModSounds.REVERB_COMPASS_USE1);
        register(ModSounds.REVERB_COMPASS_USE2);
        register(ModSounds.REVERB_COMPASS_USE3);
    }
}
