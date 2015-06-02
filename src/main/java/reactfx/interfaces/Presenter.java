package reactfx.interfaces;

public abstract class Presenter<V extends ViewBase> {

    private V view;
    
    protected Presenter(V view) {
      this.view = view;
    }

    public V view(){
      return this.view;
    }

    public final void go(ViewPane pane){
      this.bind();
      pane.load(this.view);
    }
    
    final void detach(){
      this.view.detach();
      this.unbind();
    } 
    
    protected void bind(){}
    
    protected void unbind(){}
    
  }