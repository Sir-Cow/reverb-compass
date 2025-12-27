package sircow.reverbcompass;

import net.fabricmc.api.ModInitializer;
import sircow.reverbcompass.components.FabricModComponents;
import sircow.reverbcompass.event.FabricModEvents;
import sircow.reverbcompass.recipe.FabricModRecipes;
import sircow.reverbcompass.sound.FabricModSounds;
import sircow.reverbcompass.trigger.FabricModTriggers;
import sircow.reverbcompass.update.FabricVersionChecker;

public class ReverbCompass implements ModInitializer {
    @Override
    public void onInitialize() {
        CommonClass.init();
        Constants.INSTANCE = new FabricVersionChecker();
        FabricModComponents.registerFabricModComponents();
        FabricModEvents.registerModEvents();
        FabricModRecipes.registerFabricModRecipes();
        FabricModSounds.registerFabricModSounds();
        FabricModTriggers.registerFabricModTriggers();
    }
}
