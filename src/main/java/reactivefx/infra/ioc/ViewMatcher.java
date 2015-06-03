package reactivefx.infra.ioc;

import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import reactivefx.infra.FXView;
import reactivefx.interfaces.View;
import reactivefx.interfaces.ViewBase;

import com.google.inject.Inject;
import com.google.inject.ProvisionException;
import com.google.inject.TypeLiteral;

class ViewMatcher extends Listener<Object> {

	public boolean matches(TypeLiteral<?> type) {
		return type.getRawType().isAnnotationPresent(FXView.class);
	}
	
	@Override
	protected Class<? extends PostInjection<Object>> observer() {
		return ViewPostInjection.class;
	}
	
	static class ViewPostInjection implements PostInjection<Object> {
		@Inject
		private FXMLLoader loader;
		
		public void visit(Object instance) {
			Class<?> clazz = instance.getClass();
			String fxmlFilePath = "/" + this.toFileNotation(clazz.getName()) + ".fxml";
			InputStream input = clazz.getResourceAsStream(fxmlFilePath);
			
			if(!(instance instanceof ViewBase)){
			  throw new ProvisionException("Your View must derive " + ViewBase.class.toString());
			}
			
			this.loader.setController(instance);
			
			try {
        ((View)instance).setRoot((Parent) this.loader.load(input));
      } catch (IOException e) {
        throw new ProvisionException("An internal error has been occurred.", e);
      }
		}
		
		private String toFileNotation(String javaNotation){
			return javaNotation.replace(".", "/");
		}
	}
}