package tech.schizophreniacase.meowhack.event.bus;

import tech.schizophreniacase.meowhack.Meowhack;
import tech.schizophreniacase.meowhack.event.Event;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;

public class EventBus {
    private final Map<Class<?>, List<Subscriber>> subscribers = new ConcurrentHashMap<>();

    public void post(Event event) {
        List<Subscriber> eventSubscribers = subscribers.get(event.getClass());
        if(eventSubscribers == null) return;
        for(Subscriber s : eventSubscribers) {
            try {
                s.invoke(event);
            } catch (Throwable throwable) {
                Meowhack.LOGGER.error(throwable.getMessage());
            }
        }
    }

    public void subscribe(Object object) {
        for(Method method : object.getClass().getDeclaredMethods()) {
            if(!method.isAnnotationPresent(Subscribe.class) || method.getParameterCount() == 0) continue;
            subscribers.computeIfAbsent(method.getParameters()[0].getType(), k -> new CopyOnWriteArrayList<>()).add(new Subscriber(object, method, getEvent(method)));
        }
    }

    private static Class<? extends Event> getEvent(Method method) {
        Parameter[] parameters = method.getParameters();
        if(parameters.length == 0 || !Event.class.isAssignableFrom(parameters[0].getType())) {
            throw new RuntimeException("Invalid subscriber params");
        }
        return (Class<? extends Event>) parameters[0].getType();
    }
}
