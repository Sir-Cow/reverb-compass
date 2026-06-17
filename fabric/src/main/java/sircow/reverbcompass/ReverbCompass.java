package sircow.reverbcompass;

import net.fabricmc.api.ModInitializer;
import sircow.reverbcompass.components.FabricModComponents;
import sircow.reverbcompass.recipe.FabricModRecipes;
import sircow.reverbcompass.sound.FabricModSounds;
import sircow.reverbcompass.trigger.FabricModTriggers;

public class ReverbCompass implements ModInitializer {
    @Override
    public void onInitialize() {
        CommonClass.init();
        FabricModComponents.registerFabricModComponents();
        FabricModRecipes.registerFabricModRecipes();
        FabricModSounds.registerFabricModSounds();
        FabricModTriggers.registerFabricModTriggers();
    }
}
