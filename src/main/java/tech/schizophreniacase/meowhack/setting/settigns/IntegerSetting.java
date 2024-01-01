package tech.schizophreniacase.meowhack.setting.settigns;

import tech.schizophreniacase.meowhack.setting.Setting;

public class IntegerSetting extends Setting<Integer> {
    private final int min;
    private final int max;
    public IntegerSetting(String name, Integer value, Module module, int min, int max) {
        super(name, value, module);
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
