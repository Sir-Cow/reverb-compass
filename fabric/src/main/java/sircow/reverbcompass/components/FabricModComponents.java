package sircow.reverbcompass.components;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import sircow.reverbcompass.Constants;

public class FabricModComponents {
    private static <T> void register(ModComponents.ComponentEntry<T> entry) {
        var registered = Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Constants.id(entry.id), entry.factory.get());
        entry.bind(() -> registered);
    }

    public static void registerFabricModComponents() {
        register(ModComponents.REVERB_COMPASS);
    }
}
