package sircow.reverbcompass.sound;

import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import sircow.reverbcompass.Constants;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModSounds {
    private static final Map<Identifier, SoundEvent> SOUNDS = new LinkedHashMap<>();

    public static final SoundEvent REVERB_COMPASS_USE = register("reverb_compass_use");
    public static final SoundEvent REVERB_COMPASS_USE1 = register("enderpearl_land_silent");
    public static final SoundEvent REVERB_COMPASS_USE2 = register("sculk_catalyst_break_silent");
    public static final SoundEvent REVERB_COMPASS_USE3 = register("ender_eye_dead_silent");

    private static SoundEvent register(String name) {
        return register(Constants.id(name));
    }

    private static SoundEvent register(Identifier name) {
        return register(name, name);
    }

    private static SoundEvent register(Identifier name, Identifier location) {
        SoundEvent soundEvent = SoundEvent.createVariableRangeEvent(location);
        SOUNDS.put(name, soundEvent);
        return soundEvent;
    }

    public static Map<Identifier, SoundEvent> getSounds() {
        return SOUNDS;
    }
}
