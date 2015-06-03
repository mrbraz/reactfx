package reactivefx.infra.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class EventBus {
	private final Map<EventType, List<EventHandler<?>>> events;

	public EventBus() {
		this.events = new HashMap<EventType, List<EventHandler<?>>>();
	}

	public <T> EventBus register(EventType type, EventHandler<T> handler) {
		if (!this.events.containsKey(type)) {
			this.events.put(type, new ArrayList<EventHandler<?>>());
		}

		this.events.get(type).add(handler);

		return this;
	}
	
	public <T> EventBus fire(EventType type){
		return this.fire(new Event<T>(type));
	}

	public <T> EventBus fire(Event<T> e) {
		if (!this.events.containsKey(e.type())) {
			return this;
		}

		List<EventHandler<?>> handlers = this.events.get(e.type());

		for (EventHandler<?> handler : handlers) {
			((EventHandler<T>) handler).fire(e);
		}

		return this;
	}

	public <T> EventBus unregister(EventHandler<T> handler) {
		handler.canceled();
		return this;
	}

	public EventBus remove(EventType type) {

		List<EventHandler<?>> handlers = this.events.get(type);

		for (EventHandler<?> handler : handlers) {
			handler.canceled();
		}

		return this;
	}

	public Iterable<EventType> types() {
		return this.events.keySet();
	}

	public static interface EventType {
		String name();
	}
}