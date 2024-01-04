package tech.schizophreniacase.meowhack.setting.settigns;

import tech.schizophreniacase.meowhack.module.Module;
import tech.schizophreniacase.meowhack.setting.Setting;

public class IntegerSetting extends Setting<Integer> {
    private final int min;
    private final int max;
    public IntegerSetting(String name, Integer value, int min, int max) {
        super(name, value);
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
