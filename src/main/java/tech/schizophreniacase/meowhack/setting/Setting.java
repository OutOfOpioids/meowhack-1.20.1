package tech.schizophreniacase.meowhack.setting;

public class Setting<T> {
    protected String name;
    protected T value;
    protected Module module;

    public Setting(String name, T value, Module module) {
        this.name = name;
        this.value = value;
        this.module = module;
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
}
