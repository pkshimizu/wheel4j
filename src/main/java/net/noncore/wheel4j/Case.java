package net.noncore.wheel4j;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class Case<T> {
    private T value;

    private Case(T value) {
        this.value = value;
    }

    public static <T> Case<T> of(T value) {
        return new Case<>(value);
    }

    public <R> CasePipeline<T, R> when(Object target, R result) {
        return whenValue(target, v -> result);
    }

    public <R> CasePipeline<T, R> when(Object target, Function<T, R> function) {
        return whenValue(target, function);
    }

    public <R> CasePipeline<T, R> whenValue(Object target, Function<T, R> function) {
        return new CasePipeline<T, R>(value, v -> Objects.equals(v, target), function);
    }

    public <R> CasePipeline<T, R> when(Predicate<T> predicate, R result) {
        if (predicate == null) {
            return whenValue(null, v -> result);
        }
        return new CasePipeline<T, R>(value, predicate, v -> result);
    }

    public <R> CasePipeline<T, R> when(Predicate<T> predicate, Function<T, R> function) {
        if (predicate == null) {
            return whenValue(null, function);
        }
        return new CasePipeline<T, R>(value, predicate, function);
    }

    public <R> CasePipeline<T, R> other(R result) {
        return new CasePipeline<T, R>(value, v -> true, v -> result);
    }
}
