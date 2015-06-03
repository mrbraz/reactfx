package reactivefx.interfaces;

import javafx.scene.Parent;

/**
 * @author mrbraz
 * 
 * It is a Controller class and it is an important part of auto FXML load convention and it means that every controller must derive this class
 */
public abstract class ViewBase {
  private Parent root;

  public Parent getRoot() {
    return root;
  }

  public void setRoot(Parent root) {
    this.root = root;
  }
  
  public void attach(){}
  
  public void detach(){}
}