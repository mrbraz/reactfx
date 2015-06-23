package reactivefx.infra.event;

public interface Channel<E extends Event> {
  void dispatch(E event);
  
  Class<? extends E> type();
}
