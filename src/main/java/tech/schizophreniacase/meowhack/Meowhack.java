package tech.schizophreniacase.meowhack;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.schizophreniacase.meowhack.event.bus.EventBus;
import tech.schizophreniacase.meowhack.event.bus.Subscribe;
import tech.schizophreniacase.meowhack.event.events.TestEvent;
import tech.schizophreniacase.meowhack.manager.managers.ModuleManager;
import tech.schizophreniacase.meowhack.manager.managers.TickManager;

public class Meowhack implements ModInitializer {

	public static Meowhack INSTANCE;
    public static final Logger LOGGER = LoggerFactory.getLogger("Meowhack");

	public EventBus EVENT_BUS = new EventBus();

	private ModuleManager moduleManager;
	private TickManager tickManager;

	public Meowhack() {
		INSTANCE = this;
		moduleManager = new ModuleManager();
		tickManager = new TickManager();
		EVENT_BUS.subscribe(this);
	}

	@Subscribe
	public void onTest(TestEvent event) {
		//LOGGER.info("Test event fired!");
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Welcome to Meowhack!");
	}

	public ModuleManager getModuleManager() {
		return moduleManager;
	}
	public TickManager getTickManager() {
		return tickManager;
	}
}