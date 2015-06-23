package reactivefx.infra.ioc;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import reactivefx.infra.event.Channel;
import reactivefx.infra.event.EventBus;

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
	  
	   ConfigurationBuilder configuration = new ConfigurationBuilder().addScanners(new SubTypesScanner(), new TypeAnnotationsScanner(), 
	        new MethodAnnotationsScanner(), new MethodParameterScanner(), new FieldAnnotationsScanner()).setUrls(ClasspathHelper.forClassLoader());
	    
	    Reflections scanner = new Reflections(configuration);
	    EventBus eventBus = instanceOf(EventBus.class);
	    for(Class<? extends Channel> channelClass : scanner.getSubTypesOf(Channel.class)){
	      eventBus.register(instanceOf(channelClass));
	    }
	}

	public static <T> T instanceOf(Class<T> clazz) {
		return ApplicationContext.injector.getInstance(clazz);
	}

	public static void injectMembers(Object object) {
		ApplicationContext.injector.injectMembers(object);
	}
}