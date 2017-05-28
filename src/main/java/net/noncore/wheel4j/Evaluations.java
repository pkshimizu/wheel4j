package net.noncore.wheel4j;

import java.lang.reflect.Field;

public class Evaluations {

    static class EqualsVisitor implements TraceVisitor {
        private boolean result = false;

        @Override
        public boolean visit(Object left, Object right) {
            if (left == null && right == null) {
                result = true;
                return false;
            }
            if (left == null || right == null) {
                result = false;
                return false;
            }
            if (hasEqualsMethod(left.getClass())) {
                result = left.equals(right);
                if (!result) {
                    return false;
                }
            }
            return true;
        }

        boolean getResult() {
            return result;
        }
    }

    public static boolean equals(Object left, Object right) {
        EqualsVisitor visitor = new EqualsVisitor();
        equals(left, right, visitor);
        return visitor.getResult();
    }

    private static boolean equals(Object left, Object right, TraceVisitor visitor) {
        if (!visitor.visit(left, right)) {
            return false;
        }
        if (left == null || right == null) {
            return false;
        }
        Class leftClass = left.getClass();
        Class rightClass = right.getClass();
        if (leftClass != rightClass) {
            return false;
        }
        if (!hasEqualsMethod(leftClass)) {
            Field[] fields = leftClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    if (!equals(field.get(left), field.get(right), visitor)) {
                        return false;
                    }
                } catch (IllegalAccessException e) {
                }
            }
        }
        return true;

    }

    private static boolean hasEqualsMethod(Class<?> clazz) {
        try {
            clazz.getDeclaredMethod("equals", Object.class);
        } catch (NoSuchMethodException e) {
            return false;
        }
        return true;
    }
}
