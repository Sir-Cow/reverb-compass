package sircow.reverbcompass;

import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sircow.reverbcompass.update.VersionChecker;

public class Constants {
	public static final String MOD_ID = "reverbcompass";
	public static final String MOD_NAME = "Reverb Compass";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
	public static VersionChecker INSTANCE;

	public static Identifier id(String name) {
		return Identifier.fromNamespaceAndPath(MOD_ID, name);
	}
}
