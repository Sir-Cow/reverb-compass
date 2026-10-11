package sircow.reverbcompass.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import mezz.jei.api.registration.IExtraIngredientRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import sircow.reverbcompass.Constants;
import sircow.reverbcompass.component.ModComponents;

import java.util.List;

@JeiPlugin
public final class ReverbCompassJeiPlugin implements IModPlugin {
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return Constants.id("jei_plugin");
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(Items.COMPASS, new ISubtypeInterpreter<>() {
            @Override
            public Object getSubtypeData(ItemStack stack, UidContext context) {
                if (stack.has(ModComponents.REVERB_COMPASS)) return "reverb";
                if (stack.has(DataComponents.LODESTONE_TRACKER)) return "lodestone";
                return "";
            }

            @Override
            public @NotNull String getLegacyStringSubtypeInfo(ItemStack stack, UidContext context) {
                return getSubtypeData(stack, context).toString();
            }
        });
    }

    @Override
    public void registerExtraIngredients(IExtraIngredientRegistration registration) {
        ItemStack reverbCompass = new ItemStack(Items.COMPASS);
        reverbCompass.set(ModComponents.REVERB_COMPASS, true);
        registration.addExtraItemStacks(List.of(reverbCompass));
    }
}
