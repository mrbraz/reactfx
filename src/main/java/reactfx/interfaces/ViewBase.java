package reactfx.interfaces;

import javafx.scene.Parent;

/**
 * @author mrbraz
 * 
 * It is a Controller class and it is an important part of auto FXML load convention and it means that every controller must derive this class
 */
public abstract class ViewBase {
  private Parent root;

  Parent getRoot() {
    return root;
  }

  public void setRoot(Parent root) {
    this.root = root;
  }
  
  protected void attach(){}
  
  protected void detach(){}
}