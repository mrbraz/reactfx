package reactivefx.interfaces;

import javafx.scene.Parent;

public interface View {
  void setRoot(Parent root);
  
  Parent getRoot();
  
  void attach();
  
  void detach();
}