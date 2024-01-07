package tech.schizophreniacase.meowhack.event.bus;

import tech.schizophreniacase.meowhack.Meowhack;
import tech.schizophreniacase.meowhack.event.Event;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.function.Consumer;

public class Subscriber {

    private final Consumer<Object> consumer;
    private final Class<? extends Event> eventType;
    private final Class<?> targetClass;

    public Subscriber(Object target, Method method, Class<? extends Event> eventType) {
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            CallSite callSite = LambdaMetafactory.metafactory(lookup,
                    "accept",
                    MethodType.methodType(Consumer.class, target.getClass()),
                    MethodType.methodType(void.class, Object.class),
                    lookup.unreflect(method),
                    MethodType.methodType(void.class, eventType));
            consumer = (Consumer<Object>) callSite.getTarget().invokeWithArguments(target);
            this.eventType = eventType;
            this.targetClass = target.getClass();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    public void invoke(Event event) {
        consumer.accept(event);
    }

    public Class<? extends Event> getEventType() {
        return eventType;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }


}
