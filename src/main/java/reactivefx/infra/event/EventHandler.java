package reactivefx.infra.event;

public abstract class EventHandler<T> {

	private HandlerState state = HandlerState.ACTIVE;

	public final void fire(Event<T> evt) {
		this.state.run(this, evt);
	}

	public void canceled() {
		this.state = HandlerState.CANCELED;
	}

	public void activate() {
		this.state = HandlerState.ACTIVE;
	}

	public abstract void onEvent(Event<T> evt);

	enum HandlerState{
		ACTIVE{
			@Override
			<T> void run(EventHandler<T> handler, Event<T> evt) {
				handler.onEvent(evt);
			}
		}, CANCELED{
			@Override
			<T> void run(EventHandler<T> handler, Event<T> evt) {
				//do nothing
			}
		};
		
		abstract <T> void run(EventHandler<T> handler, Event<T> evt);
	}
}