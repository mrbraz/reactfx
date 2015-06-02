package reactfx.infra.ioc;

public interface PostInjection<I> {
	void visit(I instance);
}