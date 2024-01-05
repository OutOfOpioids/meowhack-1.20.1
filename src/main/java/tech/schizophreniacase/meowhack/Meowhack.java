package tech.schizophreniacase.meowhack;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.schizophreniacase.meowhack.event.events.Render2DEvent;
import tech.schizophreniacase.meowhack.manager.managers.ModuleManager;

public class Meowhack implements ModInitializer {

	public static Meowhack INSTANCE;
    public static final Logger LOGGER = LoggerFactory.getLogger("meowhack");

	public Meowhack() {
		INSTANCE = this;
		moduleManager = new ModuleManager();
	}

	private ModuleManager moduleManager;

	@Override
	public void onInitialize() {
		LOGGER.info("Welcome to Meowhack!");
	}

	public ModuleManager getModuleManager() {
		return moduleManager;
	}
}