package tech.schizophreniacase.meowhack;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.schizophreniacase.meowhack.event.events.Render2DEvent;
import tech.schizophreniacase.meowhack.manager.managers.ModuleManager;

public class Meowhack implements ModInitializer {

	public static final Meowhack INSTANCE = new Meowhack();
    public static final Logger LOGGER = LoggerFactory.getLogger("meowhack");

	private ModuleManager moduleManager;

	@Override
	public void onInitialize() {
		moduleManager = new ModuleManager();
	}

	public ModuleManager getModuleManager() {
		return moduleManager;
	}
}