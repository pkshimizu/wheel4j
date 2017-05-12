package net.noncore.wheel4j;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Case<T> {
    static <T> Case<T> of(T value) {
        return new CasePipeline<>(value);
    }
    Case<T> whenNull();
    Case<T> when(T... targets);
    Case<T> when(Predicate<T> predicate);
    <R> Case<T> then(R result);
    <R> Case<T> then(Function<T, R> function);
    Case<T> call(Consumer<T> consumer);
    Case<T> other();
    <R> R end();
}
