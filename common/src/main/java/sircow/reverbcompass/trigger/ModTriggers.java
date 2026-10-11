package sircow.reverbcompass.trigger;

import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import sircow.reverbcompass.Constants;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModTriggers {
    private static final Map<ResourceLocation, CriterionTrigger<?>> TRIGGERS = new LinkedHashMap<>();

    public static final CustomTrigger USE_REVERB_COMPASS = register("use_reverb_compass", new CustomTrigger());

    private static <T extends CriterionTrigger<?>> T register(String name, T trigger) {
        TRIGGERS.put(Constants.id(name), trigger);
        return trigger;
    }

    public static Map<ResourceLocation, CriterionTrigger<?>> getTriggers() {
        return TRIGGERS;
    }
}
