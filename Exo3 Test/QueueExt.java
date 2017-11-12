import java.util.*;
import java.util.function.*;
public interface QueueExt<E extends Object> extends Queue<E>{
    boolean add(E e);
    E element();
    boolean offer(E e);
    E peek();
    E poll();
    // E remove();
    // QueueExt<E> filtre(Predicate<E> cond);
    // <U> QueueExt<U> map(Function<E, U> f);
    // Optional<E> trouve(Predicate<E> cond);
    <U> U reduit(U z, BiFunction<U, E, U> f);
}
