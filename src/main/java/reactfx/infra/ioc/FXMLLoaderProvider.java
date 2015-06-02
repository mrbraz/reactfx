package reactfx.infra.ioc;

import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class FXMLLoaderProvider implements Provider<FXMLLoader> {
	@Inject
	private ResourceBundle dictionary;

	public FXMLLoader get() {
		FXMLLoader loader = new FXMLLoader();
		loader.setResources(this.dictionary);
		return loader;
	}
}