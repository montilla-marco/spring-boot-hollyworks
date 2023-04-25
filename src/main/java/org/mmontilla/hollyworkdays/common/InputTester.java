package org.mmontilla.hollyworkdays.common;

import java.util.function.Predicate;

public interface InputTester<I, T extends RuntimeException> {

    boolean isValid(I input, Predicate condition);

    static <I, T extends RuntimeException> InputTester<I, T> orThrow(T exception) {
        return (input, condition) -> {
            if (condition.test(input)) {
                return true;
            }
            throw exception;
        };
    }
}
