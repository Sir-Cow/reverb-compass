package sircow.reverbcompass.sound;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import sircow.reverbcompass.Constants;

public class NeoforgeModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, Constants.MOD_ID);

    private static void register(ModSounds.SoundEntry entry) {
        entry.bind(SOUND_EVENTS.register(entry.id, entry.factory));
    }

    public static void registerNeoForgeModSounds(IEventBus eventBus) {
        register(ModSounds.REVERB_COMPASS_USE);
        register(ModSounds.REVERB_COMPASS_USE1);
        register(ModSounds.REVERB_COMPASS_USE2);
        register(ModSounds.REVERB_COMPASS_USE3);
        SOUND_EVENTS.register(eventBus);
    }
}
