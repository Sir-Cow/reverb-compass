package sircow.reverbcompass.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IExtraIngredientRegistration;
import mezz.jei.api.registration.ISlotDisplayInterpreterRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import org.jspecify.annotations.NonNull;
import sircow.reverbcompass.Constants;
import sircow.reverbcompass.component.ModComponents;

import java.util.List;

@JeiPlugin
public final class ReverbCompassJeiPlugin implements IModPlugin {
    @Override
    public @NonNull Identifier getPluginUid() {
        return Constants.id("jei_plugin");
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(Items.COMPASS, (stack, context) -> {
            if (stack.has(ModComponents.REVERB_COMPASS)) return "reverb";
            if (stack.has(DataComponents.LODESTONE_TRACKER)) return "lodestone";
            return "";
        });
    }

    @Override
    public void registerExtraIngredients(IExtraIngredientRegistration registration) {
        ItemStack reverbCompass = new ItemStack(Items.COMPASS);
        reverbCompass.set(ModComponents.REVERB_COMPASS, true);
        registration.addExtraItemStacks(List.of(reverbCompass));
    }

    @Override
    @SuppressWarnings("removal")
    public void registerSlotDisplayInterpreters(ISlotDisplayInterpreterRegistration registration) {
        registration.registerUniversal(SlotDisplay.ItemSlotDisplay.TYPE, (display, builder) -> {
            if (display.item().value() == Items.COMPASS) builder.setMatchesAllSubtypes(false);
        });
        registration.registerUniversal(SlotDisplay.TagSlotDisplay.TYPE, (display, builder) -> {
            boolean any = false;
            boolean allCompass = true;
            for (Holder<Item> holder : BuiltInRegistries.ITEM.getTagOrEmpty(display.tag())) {
                any = true;
                if (holder.value() != Items.COMPASS) {
                    allCompass = false;
                    break;
                }
            }
            if (any && allCompass) builder.setMatchesAllSubtypes(false);
        });
    }
}
