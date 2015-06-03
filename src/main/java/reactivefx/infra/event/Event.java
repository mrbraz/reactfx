package reactivefx.infra.event;

import reactivefx.infra.event.EventBus.EventType;

public class Event<T> {

	private Object source;

	private T data;

	private EventType type;

	public Event(EventType type) {
		this(type, null, null);
	}

	public Event(EventType type, T data) {
		this(type, data, null);
	}

	public Event(EventType type, T data, Object source) {
		super();
		this.type = type;
		this.data = data;
		this.source = source;
	}

	public Object source() {
		return this.source;
	}

	public T data() {
		return this.data;
	}

	public EventType type() {
		return this.type;
	}
}