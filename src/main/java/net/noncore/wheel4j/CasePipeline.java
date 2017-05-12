package net.noncore.wheel4j;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class CasePipeline<T, R> implements Case<T> {
    private T value;
    private Object result;
    private boolean matched;
    private boolean finished;

    CasePipeline(T value) {
        this.value = value;
    }

    @Override
    public Case<T> whenNull() {
        if (!finished) {
            matched = value == null;
        }
        return this;
    }

    @Override
    public Case<T> when(T... targets) {
        if (!finished) {
            matched = Stream.of(targets)
                    .anyMatch(v -> Objects.equals(v, value));
        }
        return this;
    }

    @Override
    public Case<T> when(Predicate<T> predicate) {
        if (!finished) {
            matched = predicate.test(value);
        }
        return this;
    }

    @Override
    public <R> Case<T> then(R result) {
        if (!finished && matched) {
            this.result = result;
            finished = true;
        }
        return this;
    }

    @Override
    public <R> Case<T> then(Function<T, R> function) {
        if (!finished && matched) {
            this.result = function.apply(value);
            finished = true;
        }
        return this;
    }

    @Override
    public Case<T> call(Consumer<T> consumer) {
        if (!finished && matched) {
            consumer.accept(value);
            finished = true;
        }
        return this;
    }

    @Override
    public Case<T> other() {
        if (!finished) {
            matched = true;
        }
        return this;
    }

    @Override
    public <R> R end() {
        return (R) result;
    }
}
