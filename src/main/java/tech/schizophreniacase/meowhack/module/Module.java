package tech.schizophreniacase.meowhack.module;

import tech.schizophreniacase.meowhack.util.Bind;

public abstract class Module {
    protected boolean enabled;

    private String name;
    private final Category category;
    public Bind bind = new Bind(-1);

    public Module(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void toggle() {
        enabled = !enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }
}
