package reactivefx.infra.ioc;

import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import reactivefx.infra.FXView;
import reactivefx.interfaces.View;

import com.google.inject.Inject;
import com.google.inject.ProvisionException;
import com.google.inject.TypeLiteral;

class ViewMatcher extends Listener<View> {

	public boolean matches(TypeLiteral<?> type) {
		return type.getRawType().isAnnotationPresent(FXView.class);
	}
	
	@Override
	protected Class<? extends PostInjection<View>> observer() {
		return ViewPostInjection.class;
	}
	
	static class ViewPostInjection implements PostInjection<View> {
		@Inject
		private FXMLLoader loader;
		
		public void visit(View view) {
			try {
			  Class<?> clazz = view.getClass();
			  String fxmlFilePath = "/" + this.toFileNotation(clazz.getName()) + ".fxml";
			  InputStream input = clazz.getResourceAsStream(fxmlFilePath);
			  
			  this.loader.setController(view);
        view.setRoot((Parent) this.loader.load(input));
			} catch (IOException e) {
        throw new ProvisionException("An internal error has been occurred.", e);
      }
		}
		
		private String toFileNotation(String javaNotation){
			return javaNotation.replace(".", "/");
		}
	}
}