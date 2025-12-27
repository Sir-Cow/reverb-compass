package sircow.reverbcompass.trigger;

import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import sircow.reverbcompass.Constants;

public class FabricModTriggers {
    public static void registerFabricModTriggers() {
        register(ModTriggers.USE_REVERB_COMPASS);
    }

    private static <T extends CriterionTrigger<?>> void register(ModTriggers.TriggerEntry<T> entry) {
        var registered = Registry.register(BuiltInRegistries.TRIGGER_TYPES, Constants.id(entry.id), entry.factory.get());
        entry.bind(() -> registered);
    }
}
