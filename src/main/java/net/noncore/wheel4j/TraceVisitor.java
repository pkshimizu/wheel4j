package net.noncore.wheel4j;

@FunctionalInterface
public interface TraceVisitor {
    boolean visit(Object left, Object right);
}
