package tech.schizophreniacase.meowhack.module.modules.client;

import tech.schizophreniacase.meowhack.module.Category;
import tech.schizophreniacase.meowhack.module.Module;

public class CustomTitle extends Module {
    public static CustomTitle INSTANCE;
    public CustomTitle() {
        super("CustomTitle", Category.CLIENT);
        INSTANCE = this;
        INSTANCE.enabled = true;
    }
}
