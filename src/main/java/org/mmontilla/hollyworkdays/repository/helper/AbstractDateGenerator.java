package org.mmontilla.hollyworkdays.repository.helper;

import org.mmontilla.hollyworkdays.common.InputTester;
import org.mmontilla.hollyworkdays.repository.exception.ExceptionFactory.Exceptions;
import org.mmontilla.hollyworkdays.repository.utils.PredicateFactory;
import org.mmontilla.hollyworkdays.repository.utils.RepositoryTierConfiguration.Ranges;

import java.time.LocalDate;

public abstract class AbstractDateGenerator<T> implements DateGenerator<T> {
    public AbstractDateGenerator() {
    }

    protected LocalDate startDateOf(int yearOf) {
        validateYear(yearOf);
        return getDateOf(
                yearOf,
                Ranges.FIRST_MONTH_OF_RANGE.day,
                Ranges.FIRST_DAY_OF_RANGE.day
        );
    }

    protected LocalDate endDateOf(int yearOf) {
        validateYear(yearOf);
        return getDateOf(
                yearOf,
                Ranges.LAST_MONTH_OF_RANGE.day,
                Ranges.LAST_DAY_OF_RANGE.day
        );
    }

    private static LocalDate getDateOf(int yearOf, int month, int day) {
        return LocalDate.of(yearOf, month, day);
    }

    private boolean validateYear(int yearOf) {
        return InputTester.orThrow(Exceptions.INVALID_YEAR.exception)
                .isValid(yearOf, PredicateFactory.Integers.YEAR);
    }
}
