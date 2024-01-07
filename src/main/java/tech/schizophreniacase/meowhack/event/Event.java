package tech.schizophreniacase.meowhack.event;

public class Event {
    private boolean cancelled;

    public void cancel(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
