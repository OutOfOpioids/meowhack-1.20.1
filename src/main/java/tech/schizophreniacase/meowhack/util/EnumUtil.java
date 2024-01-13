package tech.schizophreniacase.meowhack.util;

public class EnumUtil {
   public static Enum<?> next(Enum<?> e) {
      Enum<?>[] values = e.getDeclaringClass().getEnumConstants();
      return values.length - 1 == e.ordinal() ? values[0] : values[e.ordinal() + 1];
   }

    public static Enum<?> previous(Enum<?> e) {
        Enum<?>[] values = e.getDeclaringClass().getEnumConstants();
        return e.ordinal() - 1 < 0 ? values[values.length - 1] : values[e.ordinal() - 1];
    }

    public static Enum<?> fromString(Enum<?> initial, String name) {
       Enum<?> e = fromString(initial.getDeclaringClass(), name);
       if (e != null) {
            return e;
       }
       return initial;
    }

    public static <T extends Enum<?>> T fromString(Class<T> type, String name) {
       for(T constant : type.getEnumConstants()) {
          if(constant.name().equalsIgnoreCase(name)) {
             return constant;
          }
       }
       return null;
    }

    public static Enum<?> getEnumStartingWith(String prefix, Class<? extends Enum<?>> type) {

        if (prefix == null) {
            return null;
        }

        prefix = prefix.toLowerCase();
        Enum<?>[] constants = type.getEnumConstants();

        for(Enum<?> constant : constants) {
            if(constant.name().toLowerCase().startsWith(prefix)) {
                return constant;
            }
        }

        return null;
    }
}
