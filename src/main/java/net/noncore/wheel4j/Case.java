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
        return caseValue(target, v -> result);
    }

    public <R> CasePipeline<T, R> when(Object target, Function<T, R> function) {
        return caseValue(target, function);
    }

    public <R> CasePipeline<T, R> caseValue(Object target, Function<T, R> function) {
        return new CasePipeline<T, R>(value, v -> Objects.equals(v, target), function);
    }

    public <R> CasePipeline<T, R> when(Predicate<T> predicate, R result) {
        if (predicate == null) {
            return caseValue(null, v -> result);
        }
        return new CasePipeline<T, R>(value, predicate, v -> result);
    }

    public <R> CasePipeline<T, R> when(Predicate<T> predicate, Function<T, R> function) {
        if (predicate == null) {
            return caseValue(null, function);
        }
        return new CasePipeline<T, R>(value, predicate, function);
    }

    public <R> CasePipeline<T, R> other(R result) {
        return new CasePipeline<T, R>(value, v -> true, v -> result);
    }
}
