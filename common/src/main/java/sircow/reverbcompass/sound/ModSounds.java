package sircow.reverbcompass.sound;

import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import sircow.reverbcompass.Constants;

import java.util.function.Supplier;

public class ModSounds {
    public static final SoundEntry REVERB_COMPASS_USE = new SoundEntry("reverb_compass_use", 16.0F);
    public static final SoundEntry REVERB_COMPASS_USE1 = new SoundEntry("enderpearl_land_silent", 16.0F);
    public static final SoundEntry REVERB_COMPASS_USE2 = new SoundEntry("sculk_catalyst_break_silent", 16.0F);
    public static final SoundEntry REVERB_COMPASS_USE3 = new SoundEntry("ender_eye_dead_silent", 16.0F);

    public static class SoundEntry {
        public final String id;
        public final Supplier<SoundEvent> factory;
        private Supplier<SoundEvent> event;

        public SoundEntry(String name, float range) {
            this.id = name;
            this.factory = () -> SoundEvent.createFixedRangeEvent(Constants.id(name), range);
        }

        public void bind(Supplier<SoundEvent> supplier) {
            this.event = supplier;
        }

        public SoundEvent get() {
            return this.event.get();
        }
    }
}
