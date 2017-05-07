package net.noncore.wheel4j;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CaseTest {

    @Test
    public void testWhenConditionValueReturnValue() {
        String actual = Case.of(1)
                .when(1, "one")
                .when(2, "two")
                .when(3, "three")
                .other("other")
                .end();
        assertThat(actual, is("one"));
    }

    @Test
    public void testOtherReturnValue() {
        String actual = Case.of(4)
                .when(1, "one")
                .when(2, "two")
                .when(3, "three")
                .other("other")
                .end();
        assertThat(actual, is("other"));
    }

    @Test
    public void textWhenConditionExprReturnValue() {
        String actual = Case.of(1)
                .when(v -> v % 2 == 0, "Even")
                .when(v -> v % 2 == 1, "Odd")
                .end();
        assertThat(actual, is("Odd"));
    }

    @Test
    public void textWhenConditionNullReturnValue() {
        Integer value = null;
        String actual = Case.of(value)
                .when(null, "Null")
                .when(v -> v % 2 == 1, "Odd")
                .when(v -> v % 2 == 0, "Even")
                .end();
        assertThat(actual, is("Null"));
    }

    @Test
    public void testWhenConditionValueReturnExpr() {
        int actual = Case.of(2)
                .when(1, v -> v + 10)
                .when(2, v -> v + 20)
                .when(3, v -> v + 30)
                .other(40)
                .end();
        assertThat(actual, is(22));
    }

    @Test
    public void testOtherReturnExpr() {
        int actual = Case.of(4)
                .when(1, v -> v + 10)
                .when(2, v -> v + 20)
                .when(3, v -> v + 30)
                .other(v -> v + 40)
                .end();
        assertThat(actual, is(44));
    }

    @Test
    public void textWhenConditionExprReturnExpr() {
        int actual = Case.of(3)
                .when(v -> v % 2 == 0, v -> v / 2)
                .when(v -> v % 2 == 1, v -> (v + 1) / 2)
                .end();
        assertThat(actual, is(2));
    }

    @Test
    public void textWhenConditionNullReturnExpr() {
        Integer value = null;
        int actual = Case.of(value)
                .when(null, v -> 10 * 5)
                .when(v -> v % 2 == 0, v -> v / 2)
                .when(v -> v % 2 == 1, v -> (v + 1) / 2)
                .end();
        assertThat(actual, is(50));
    }

    @Test
    public void testSample() {
        int actual = Case.of("hello world")
                .when(null, 0)
                .when(v -> v.isEmpty(), 0)
                .other(v -> v.length())
                .end();
        assertThat(actual, is(11));
    }
}