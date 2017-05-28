package net.noncore.wheel4j;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EvaluationsTest {
    @Test
    public void testEqualsNull() {
        assertThat(Evaluations.equals(null, null), is(true));
        assertThat(Evaluations.equals(1, null), is(false));
        assertThat(Evaluations.equals(null, 1), is(false));
    }
    @Test
    public void testEqualsPrimitive() {
        assertThat(Evaluations.equals(1, 1), is(true));
        assertThat(Evaluations.equals(1, 2), is(false));
        assertThat(Evaluations.equals(1L, 1L), is(true));
        assertThat(Evaluations.equals(1L, 2L), is(false));
        assertThat(Evaluations.equals(1.1, 1.1), is(true));
        assertThat(Evaluations.equals(1.1, 2.1), is(false));
        assertThat(Evaluations.equals('a', 'a'), is(true));
        assertThat(Evaluations.equals('a', 'b'), is(false));
    }

    static class SuperClass {
        private final int value1;
        private final String value2;

        SuperClass(int value1, String value2) {
            this.value1 = value1;
            this.value2 = value2;
        }
    }

    static class SubClass extends SuperClass {
        private final Long value3;

        SubClass(int value1, String value2, Long value3) {
            super(value1, value2);
            this.value3 = value3;
        }
    }

    @Test
    public void testEqualsObject() {
        assertThat(Evaluations.equals("Sample", "Sample"), is(true));
        assertThat(Evaluations.equals("Sample", "Example"), is(false));
        assertThat(Evaluations.equals(new SuperClass(1, "Sample"), new SuperClass(1, "Sample")), is(true));
        assertThat(Evaluations.equals(new SuperClass(1, "Sample"), new SuperClass(2, "Sample")), is(false));
        assertThat(Evaluations.equals(new SuperClass(1, "Sample"), new SuperClass(1, "Example")), is(false));
        assertThat(Evaluations.equals(new SubClass(1, "Sample", 2L), new SubClass(1, "Sample", 2L)), is(true));
        assertThat(Evaluations.equals(new SubClass(1, "Sample", 2L), new SubClass(1, "Sample", 3L)), is(false));
    }
}
