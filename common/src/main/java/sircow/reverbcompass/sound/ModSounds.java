package sircow.reverbcompass.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import sircow.reverbcompass.Constants;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModSounds {
    private static final Map<ResourceLocation, SoundEvent> SOUNDS = new LinkedHashMap<>();

    public static final SoundEvent REVERB_COMPASS_USE = register("reverb_compass_use", 16.0F);
    public static final SoundEvent REVERB_COMPASS_USE1 = register("enderpearl_land_silent", 16.0F);
    public static final SoundEvent REVERB_COMPASS_USE2 = register("sculk_catalyst_break_silent", 16.0F);
    public static final SoundEvent REVERB_COMPASS_USE3 = register("ender_eye_dead_silent", 16.0F);

    private static SoundEvent register(String name, float range) {
        SoundEvent soundEvent = SoundEvent.createFixedRangeEvent(Constants.id(name), range);
        SOUNDS.put(Constants.id(name), soundEvent);
        return soundEvent;
    }

    public static Map<ResourceLocation, SoundEvent> getSounds() {
        return SOUNDS;
    }
}
