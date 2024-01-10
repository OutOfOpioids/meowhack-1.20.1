package tech.schizophreniacase.meowhack.manager.managers;

import tech.schizophreniacase.meowhack.module.Module;
import tech.schizophreniacase.meowhack.module.modules.client.CustomTitle;
import tech.schizophreniacase.meowhack.module.modules.client.Hud;
import tech.schizophreniacase.meowhack.module.modules.combat.AntiPoplag;

import java.util.Arrays;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules;
    public ModuleManager() {
        super();
        modules = Arrays.asList(
            new CustomTitle(),
            new Hud(),
            new AntiPoplag()
        );
    }

    public List<Module> getModules() {
        return modules;
    }

    public Module getModuleByName(String name) {
        for (Module module : modules) {
            if (module.getName().equalsIgnoreCase(name)) {
                return module;
            }
        }
        return null;
    }
}
