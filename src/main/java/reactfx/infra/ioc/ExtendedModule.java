package reactfx.infra.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public abstract class ExtendedModule extends AbstractModule {
	protected <T> void bindPostInjection(final Class<T> target, final Class<? extends PostInjection<T>> observer){
		Listener<T> listener = new Listener<T>() {
		  public boolean matches(TypeLiteral<?> t) {
				return target.isAssignableFrom(t.getRawType());
			}
			
			@Override
			protected Class<? extends PostInjection<T>> observer() {
				return observer;
			}
		};
		
		listener.bindTo(this.binder());
	}
}
