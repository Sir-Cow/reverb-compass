package sircow.reverbcompass.components;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import sircow.reverbcompass.Constants;

public class NeoforgeModComponents {
    public static final DeferredRegister<DataComponentType<?>> COMPONENTS = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, Constants.MOD_ID);

    private static <T> void register(ModComponents.ComponentEntry<T> entry) {
        entry.bind(COMPONENTS.register(entry.id, entry.factory));
    }

    public static void registerNeoForgeModComponents(IEventBus eventBus) {
        register(ModComponents.REVERB_COMPASS);
        COMPONENTS.register(eventBus);
    }
}
