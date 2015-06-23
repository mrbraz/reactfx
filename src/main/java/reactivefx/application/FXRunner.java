package reactivefx.application;

import javafx.application.Platform;

public class FXRunner {
  public static void run(Runnable runnable){
    if(Platform.isFxApplicationThread()){
      runnable.run();
      return;
    }
    
    Platform.runLater(runnable);
  }
}
