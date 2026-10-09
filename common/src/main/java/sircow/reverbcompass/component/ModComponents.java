package sircow.reverbcompass.component;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import sircow.reverbcompass.Constants;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModComponents {
    private static final Map<Identifier, DataComponentType<?>> COMPONENTS = new LinkedHashMap<>();

    public static final DataComponentType<Boolean> REVERB_COMPASS = register("reverb_compass", DataComponentType.<Boolean>builder().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build());

    private static <T> DataComponentType<T> register(String name, DataComponentType<T> componentType) {
        COMPONENTS.put(Constants.id(name), componentType);
        return componentType;
    }

    public static Map<Identifier, DataComponentType<?>> getComponents() {
        return COMPONENTS;
    }
}
