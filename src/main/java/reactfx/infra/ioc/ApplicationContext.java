package reactfx.infra.ioc;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class ApplicationContext {
	private static Injector injector;
	
	private ApplicationContext() {
		super();
	}
	
	public static void init(Module... modules){
		ApplicationContext.injector = Guice.createInjector(modules);
	}

	public static <T> T instanceOf(Class<T> clazz) {
		return ApplicationContext.injector.getInstance(clazz);
	}

	public static void injectMembers(Object object) {
		ApplicationContext.injector.injectMembers(object);
	}
}