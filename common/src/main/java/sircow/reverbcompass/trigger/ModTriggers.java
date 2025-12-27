package sircow.reverbcompass.trigger;

import net.minecraft.advancements.CriterionTrigger;
import sircow.reverbcompass.trigger.custom.UseReverbCompassTrigger;

import java.util.function.Supplier;

public class ModTriggers {
    public static final TriggerEntry<UseReverbCompassTrigger> USE_REVERB_COMPASS = new TriggerEntry<>("use_reverb_compass", UseReverbCompassTrigger::new);

    public static class TriggerEntry<T extends CriterionTrigger<?>> {
        public final String id;
        public final Supplier<T> factory;
        private Supplier<T> trigger;

        public TriggerEntry(String id, Supplier<T> factory) {
            this.id = id;
            this.factory = factory;
        }

        public void bind(Supplier<T> supplier) {
            this.trigger = supplier;
        }

        public T get() {
            return this.trigger.get();
        }
    }
}

