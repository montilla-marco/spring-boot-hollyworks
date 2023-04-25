package org.mmontilla.hollyworkdays.repository.helper;

import org.junit.jupiter.api.Test;
import org.mmontilla.hollyworkdays.repository.exception.RepositoryException;
import org.mmontilla.hollyworkdays.repository.utils.RepositoryTierConfiguration.ErrorMessages;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AbstractDateGeneratorTest {

    public static final int NINETEENTH_CENTURY_MAX_YEAR = 1999;

    public static final int THIRTY_FIRST_CENTURY_MIN_YEAR = 3001;

    @Test
    void givenInvalidYearRange_whenStartDateOf_thenThrowsInvalidYearException() {
        //act
        RepositoryException thrown = assertThrows(RepositoryException.class, () -> {
            new Sut().startDateOf(NINETEENTH_CENTURY_MAX_YEAR);
        }, "InvalidYearException was expected");
        assertThrows(RepositoryException.class, () -> {
            new Sut().startDateOf(THIRTY_FIRST_CENTURY_MIN_YEAR);
        }, "RepositoryException was expected");
        // assert
        assertThat(thrown.getMessage()).isEqualTo(ErrorMessages.INVALID_YEAR.message);
    }

    @Test
    void givenInvalidYearRange_whenEndDateOf_thenThrowsInvalidYearException() {
        //act
        RepositoryException thrown = assertThrows(RepositoryException.class, () -> {
            new Sut().endDateOf(NINETEENTH_CENTURY_MAX_YEAR);
        }, "RepositoryException was expected");
        assertThrows(RepositoryException.class, () -> {
            new Sut().endDateOf(THIRTY_FIRST_CENTURY_MIN_YEAR);
        }, "RepositoryException was expected");
        // assert
        assertThat(thrown.getMessage()).isEqualTo(ErrorMessages.INVALID_YEAR.message);
    }

    class Sut<T> extends AbstractDateGenerator<T> {
        public Sut() {
            super();
        }
        @Override
        public T getDate(int year) {
            return null;
        }
    }
}