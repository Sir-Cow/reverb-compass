package sircow.reverbcompass;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sircow.reverbcompass.update.VersionChecker;

public class Constants {
	public static final String MOD_ID = "reverbcompass";
	public static final String MOD_NAME = "Reverb Compass";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
	public static VersionChecker INSTANCE;

	public static ResourceLocation id(String name) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
	}
}
