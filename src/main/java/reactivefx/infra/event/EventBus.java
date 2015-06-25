package reactivefx.infra.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import javafx.application.Platform;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class EventBus {
  private Map<Class<? extends Event>, List<Channel<? extends Event>>> handlers = new HashMap<>();
  
  private @Inject ExecutorService executor;
  
  public <E extends Event> void asyncDispatch(E event){
    Runnable command = new Runnable() {
      @Override
      public void run() {
        EventBus.this.dispatch(event);
      }
    };
    this.executor.execute(command);
  }
  
  public <E extends Event> void fxDispatch(E event){
    Runnable command = new Runnable() {
      @Override
      public void run() {
        EventBus.this.dispatch(event);
      }
    };
    Platform.runLater(command);
  }
  
  public void register(Channel<? extends Event> channel) {
    if (!this.handlers.containsKey(channel.type())) {
      this.handlers.put(channel.type(), new ArrayList<Channel<? extends Event>>());
    }

    this.handlers.get(channel.type()).add(channel);
  }

  @SuppressWarnings("unchecked")
  public <E extends Event> void dispatch(E event) {
    if (!this.handlers.containsKey(event.getClass())) {
      return;
    }

    List<Channel<? extends Event>> channels = this.handlers.get(event.getClass());
    for (Channel<? extends Event> channelAt : channels) {
      Channel<E> channel = (Channel<E>) channelAt;
      channel.dispatch(event);
    }
  }
}