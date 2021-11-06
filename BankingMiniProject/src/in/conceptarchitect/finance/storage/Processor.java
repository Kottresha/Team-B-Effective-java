package in.conceptarchitect.finance.storage;

@FunctionalInterface
public interface Processor<T> {
	
	void process(T object);
	
	default boolean initialize() {
		return true;
	}
	default void close() {
		
	}
}
