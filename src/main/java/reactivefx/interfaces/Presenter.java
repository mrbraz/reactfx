package reactivefx.interfaces;

public abstract class Presenter<V extends View> {

    private V view;
    
    protected void set(V view){
      this.view = view;
    }
    
    public V view(){
      return this.view;
    }

    public final void go(ViewLoader loader){
      this.bind();
      loader.load(this.view);
    }
    
    final void detach(){
      this.view.detach();
      this.unbind();
    } 
    
    protected void bind(){}
    
    protected void unbind(){}
    
  }