package br.com.mhbp.threads.stream;

import java.lang.reflect.Proxy;
import java.util.Comparator;
import java.util.function.*;
import java.util.stream.Stream;

public interface EnhancedStream<T> extends Stream<T> {

    EnhancedStream<T> distinct(ToIntFunction<T> hashcode, BiPredicate<T, T> equals, BinaryOperator<T> merger);
    <R> EnhancedStream<R> map(Function<? super T, ? extends R> mapper);
    <R> EnhancedStream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
    EnhancedStream<T> distinct();
    EnhancedStream<T> sorted();
    EnhancedStream<T> sorted(Comparator<? super T> comparator);
    EnhancedStream<T> peek(Consumer<? super T> action);
    EnhancedStream<T> limit(long maxSize);
    EnhancedStream<T> skip(long n);
    EnhancedStream<T> takeWhile(Predicate<? super T> predicate);
    EnhancedStream<T> dropWhile(Predicate<? super T> predicate);
    EnhancedStream<T> sequential();
    EnhancedStream<T> parallel();
    EnhancedStream<T> unordered();
    EnhancedStream<T> onClose(Runnable closeHandler);

    @SafeVarargs
    static <E> EnhancedStream<E> of (E... elements) {
        return from(Stream.of(elements));
    }

    static <E> EnhancedStream<E> from(Stream<E> elements) {

        return (EnhancedStream<E>) Proxy.newProxyInstance(
                    EnhancedStream.class.getClassLoader(),
                    new Class<?>[] {EnhancedStream.class},
                    new EnhancedStreamHandler<>(elements)
               );
    }

}
