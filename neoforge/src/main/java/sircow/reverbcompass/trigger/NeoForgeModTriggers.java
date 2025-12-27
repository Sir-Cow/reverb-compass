package sircow.reverbcompass.trigger;

import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import sircow.reverbcompass.Constants;

public class NeoForgeModTriggers {
    public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = DeferredRegister.create(Registries.TRIGGER_TYPE, Constants.MOD_ID);

    public static void registerNeoForgeModTriggers(IEventBus eventBus) {
        register(ModTriggers.USE_REVERB_COMPASS);
        TRIGGERS.register(eventBus);
    }

    private static <T extends CriterionTrigger<?>> void register(ModTriggers.TriggerEntry<T> entry) {
        entry.bind(TRIGGERS.register(entry.id, entry.factory));
    }
}
