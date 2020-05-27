package br.com.mhbp.threads.stream;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnhancedStreamHandler<T> implements InvocationHandler {

    private Stream<T> delegate;
    private static final Method enhancedDistinct;

    static {
        try {
            enhancedDistinct = EnhancedStream.class.getMethod("distinct", ToIntFunction.class, BiPredicate.class, BinaryOperator.class);
        } catch (NoSuchMethodException e) {
            throw new Error(e);
        }
    }

    private static final Map<Method, Method> methodMap = Stream.of(EnhancedStream.class.getMethods())
                                                         .filter(m -> !m.equals(enhancedDistinct) || !Modifier.isStatic(m.getModifiers()))
                                                         .collect(Collectors.toMap(
                                                                 Function.identity(),
                                                                 m -> {

                                                                     try {
                                                                         return Stream.class.getMethod(m.getName(), m.getParameterTypes());
                                                                     }catch (NoSuchMethodException e) {
                                                                         throw new Error(e);
                                                                     }
                                                            }));

    private static final class Key<E> {
        private final E e;
        private final ToIntFunction<E> hashCode;
        private final BiPredicate<E, E> equals;

        public Key(E e, ToIntFunction<E> hashCode,
                   BiPredicate<E, E> equals) {
            this.e = e;
            this.hashCode = hashCode;
            this.equals = equals;
        }

        @Override
        public int hashCode() {
            return hashCode.applyAsInt(e);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Key)) return false;
            @SuppressWarnings("unchecked")
            Key<E> that = (Key<E>) obj;
            return equals.test(this.e, that.e);
        }
    }

    public EnhancedStreamHandler(Stream<T> delegate) {
        this.delegate = delegate;
    }



    //TODO
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        return null;
    }

    //TODO javaspecialist
    private EnhancedStream<T> distinct(EnhancedStream<T> proxy,
                                       ToIntFunction<T> hashCode,
                                       BiPredicate<T, T> equals,
                                       BinaryOperator<T> merger) {
        return null;
    }
}
