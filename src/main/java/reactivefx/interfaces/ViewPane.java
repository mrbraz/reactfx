package reactivefx.interfaces;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ViewPane extends StackPane implements ViewLoader {
  private static final ViewPane instance = new ViewPane();

  private ViewPane() {}
  
  public static ViewPane create(Stage stage){
    Scene scene = new Scene(instance);
    stage.setScene(scene);
    return ViewPane.instance;
  }
  
  public <V extends View> void go(Presenter<V> presenter){
    presenter.go(this);
  }
  
  public void load(View view){
    final DoubleProperty opacity = this.opacityProperty();

    if (this.getChildren().isEmpty()) {
      setOpacity(0.0);
      getChildren().add(view.getRoot());
      Timeline fadeIn = new Timeline(
          new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
          new KeyFrame(new Duration(1000), new KeyValue(opacity, 1.0)));
      fadeIn.play();
      view.attach();
      return;
    }
    
    Timeline fade = new Timeline(
        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
        new KeyFrame(new Duration(2000), new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent t) {
            getChildren().clear();
            getChildren().add(view.getRoot());
            Timeline fadeIn = new Timeline(
              new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
              new KeyFrame(new Duration(2000), new KeyValue(opacity, 1.0)));
            fadeIn.play();
            view.attach();
          }
        }, new KeyValue(opacity, 0.0)));
    fade.play();
  }
}