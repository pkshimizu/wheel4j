package net.noncore.wheel4j;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class CasePipeline<T, R> {
    private T value;
    private Predicate<T> predicate;
    private Function<T, R> function;
    private CasePipeline<T, R> next;
    private CasePipeline<T, R> head;

    CasePipeline(T value, Predicate<T> predicate, Function<T, R> funcation) {
        this.value = value;
        this.predicate = predicate;
        this.function = funcation;
        head = this;
    }

    private CasePipeline(T value, Predicate<T> predicate, Function<T, R> funcation, CasePipeline<T, R> head) {
        this.value = value;
        this.predicate = predicate;
        this.function = funcation;
        this.head = head;
    }

    public CasePipeline<T, R> when(Object target, R result) {
        next = new CasePipeline<T, R>(value, v -> Objects.equals(v, target), v -> result, head);
        return next;
    }

    public CasePipeline<T, R> when(Object target, Function<T, R> function) {
        next = new CasePipeline<T, R>(value, v -> Objects.equals(v, target), function, head);
        return next;
    }

    public CasePipeline<T, R> when(Predicate<T> predicate, R result) {
        next = new CasePipeline<T, R>(value, predicate, v -> result, head);
        return next;
    }

    public CasePipeline<T, R> when(Predicate<T> predicate, Function<T, R> function) {
        next = new CasePipeline<T, R>(value, predicate, function, head);
        return next;
    }

    public CasePipeline<T, R> other(R result) {
        next = new CasePipeline<T, R>(value, v -> true, v -> result, head);
        return next;
    }

    public CasePipeline<T, R> other(Function<T, R> function) {
        next = new CasePipeline<T, R>(value, v -> true, function, head);
        return next;
    }

    public R end() {
        return head.evaluate();
    }

    private R evaluate() {
        if (predicate.test(value)) {
            return function.apply(value);
        } else if (next != null){
            return next.evaluate();
        }
        return null;
    }
}
