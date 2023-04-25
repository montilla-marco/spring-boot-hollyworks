package org.mmontilla.hollyworkdays.repository.impl;

import org.mmontilla.hollyworkdays.common.InputTester;
import org.mmontilla.hollyworkdays.repository.DateRepository;
import org.mmontilla.hollyworkdays.repository.exception.ExceptionFactory;
import org.mmontilla.hollyworkdays.repository.helper.HollyDateGenerator;
import org.mmontilla.hollyworkdays.repository.helper.WorkDaysGenerator;
import org.mmontilla.hollyworkdays.repository.utils.PredicateFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DateRepositoryImpl implements DateRepository {
    private final HollyDateGenerator hollyDateGenerator;

    private final WorkDaysGenerator workDaysGenerator;

    public DateRepositoryImpl(HollyDateGenerator hollyDateGenerator, WorkDaysGenerator workDaysGenerator) {
        this.hollyDateGenerator = hollyDateGenerator;
        this.workDaysGenerator = workDaysGenerator;
    }

    @Override
    public List<LocalDate> getWorkDays(int year) {
        return workDaysGenerator.getDate(year);
    }

    @Override
    public List<LocalDate> getHollyDays(int year, int numberOf) {
        validateNumber(numberOf);
        return getUniqueHollyDays(year, numberOf);
    }

    private void validateNumber(int number) {
        InputTester.orThrow(ExceptionFactory.Exceptions.INVALID_HOLLY_DAYS_NUMBER.exception)
                .isValid(number, PredicateFactory.Integers.HOLLY_DAYS_NUMBER);
    }

    private List<LocalDate> getUniqueHollyDays(int yearOf, int numberOf) {
        List<LocalDate> hollyDates = Arrays.stream(new LocalDate[numberOf])
                .map(index -> hollyDateGenerator.getDate(yearOf))
                .collect(Collectors.toList());
        List<LocalDate> uniqueHollyDays = removeDuplicates(hollyDates);
        if (uniqueHollyDays.size() == numberOf) {
            return uniqueHollyDays;
        }
        return getUniqueHollyDays(yearOf, numberOf);
    }

    private List<LocalDate> removeDuplicates(List<LocalDate> hollyDates) {
        LinkedHashSet<LocalDate> hashSet = new LinkedHashSet<>(hollyDates);
        return new ArrayList<>(hashSet);
    }
}
