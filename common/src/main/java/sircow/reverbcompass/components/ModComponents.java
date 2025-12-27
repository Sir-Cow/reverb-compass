package sircow.reverbcompass.components;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;

import java.util.function.Supplier;

public class ModComponents {
    public static final ComponentEntry<Boolean> REVERB_COMPASS = new ComponentEntry<>("reverb_compass", () -> DataComponentType.<Boolean>builder().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build());

    public static class ComponentEntry<T> {
        public final String id;
        public final Supplier<DataComponentType<T>> factory;
        private Supplier<DataComponentType<T>> type;

        public ComponentEntry(String id, Supplier<DataComponentType<T>> factory) {
            this.id = id;
            this.factory = factory;
        }

        public void bind(Supplier<DataComponentType<T>> supplier) {
            this.type = supplier;
        }

        public DataComponentType<T> get() {
            return this.type.get();
        }
    }
}
