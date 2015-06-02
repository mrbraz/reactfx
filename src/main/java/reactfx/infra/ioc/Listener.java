package reactfx.infra.ioc;

import com.google.inject.Binder;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.AbstractMatcher;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

abstract class Listener<O> extends AbstractMatcher<TypeLiteral<?>>{

	public void bindTo(Binder binder){
		binder.bindListener(this, this.listener());
	}
	
	private TypeListener listener(){
		return new TypeListener() {
			public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
				encounter.register(new InjectionListener<I>(){
					public void afterInjection(I instance) {
						PostInjection<O> observer = ApplicationContext.instanceOf(Listener.this.observer());
						observer.visit((O) instance);
					}
				});
			}
		};
	}
	
	protected abstract Class<? extends PostInjection<O>> observer();
}