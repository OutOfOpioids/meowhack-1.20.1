package tech.schizophreniacase.meowhack.manager.managers;

import tech.schizophreniacase.meowhack.module.Module;
import tech.schizophreniacase.meowhack.module.modules.client.CustomTitle;
import tech.schizophreniacase.meowhack.module.modules.client.Hud;

import java.util.Arrays;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules;
    public ModuleManager() {
        super();
        modules = Arrays.asList(
            new CustomTitle(),
            new Hud()
        );
    }

}
