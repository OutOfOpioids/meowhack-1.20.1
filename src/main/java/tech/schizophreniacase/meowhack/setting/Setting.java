package tech.schizophreniacase.meowhack.setting;

import tech.schizophreniacase.meowhack.module.Module;

public class Setting<T> {
    protected String name;
    protected T value;
    protected Module module;

    public Setting(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public Module getModule() {
        return module;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
