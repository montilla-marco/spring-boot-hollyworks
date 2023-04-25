package org.mmontilla.hollyworkdays.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputTesterTest {

    @Test
    void givenFalsePredicate_whenTest_thenThrowsRuntimeException() {
        //assert & act
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            InputTester
                    .orThrow(new RuntimeException("Exception throws on false predicate"))
                    .isValid(10, in -> false);
        }, "RuntimeException was expected");
        //assert
        assertThat(thrown.getMessage()).isEqualTo("Exception throws on false predicate");
    }

    @Test
    void givenTruePredicate_whenTest_thenReturnTrue() {
        //act
        boolean result = InputTester
                .orThrow(new RuntimeException("Exception throws on false predicate"))
                .isValid(10, in -> true);
        //assert
        assertThat(result).isTrue();
    }
}