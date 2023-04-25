package org.mmontilla.hollyworkdays.application.impl;

import org.mmontilla.hollyworkdays.application.HollyWorksService;
import org.mmontilla.hollyworkdays.application.exception.ExceptionFactory.Exceptions;
import org.mmontilla.hollyworkdays.application.utils.ApplicationTierConfiguration.InformationMessages;
import org.mmontilla.hollyworkdays.application.utils.PredicateFactory;
import org.mmontilla.hollyworkdays.common.InputTester;
import org.mmontilla.hollyworkdays.repository.DateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mmontilla.hollyworkdays.application.utils.ApplicationTierConfiguration.BusinessDays;

@Service
public class HollyWorksServiceImpl implements HollyWorksService {

    private final DateRepository dateRepository;

    public HollyWorksServiceImpl(DateRepository dateRepository) {
        this.dateRepository = dateRepository;
    }

    @Override
    public List<String> getHollyWorks(int yearOf, int numberOf) {
        validateYear(yearOf);
        validateNumberOf(numberOf);
        List<LocalDate> hollyDays = dateRepository.getHollyDays(yearOf, numberOf);
        int workDaysOnHollyDay = calculateWorkDaysOnHollyDay(hollyDays);
        int worDaysNumber = calculateWorkDays(workDaysOnHollyDay, dateRepository.getWorkDays(yearOf).size());
        return Arrays.asList(InformationMessages.HOLLY_DAY_RESULT_MESSAGE.message + workDaysOnHollyDay,
                InformationMessages.WORK_DAY_RESULT_MESSAGE.message + worDaysNumber);
    }

    private boolean validateYear(int yearOf) {
        return InputTester.orThrow(Exceptions.INVALID_YEAR_RANGE.exception)
                .isValid(yearOf, PredicateFactory.Integers.YEAR);
    }

    private boolean validateNumberOf(int numberOf) {
        return InputTester.orThrow(Exceptions.INVALID_HOLLY_DAYS_RANGE.exception)
                .isValid(numberOf, PredicateFactory.Integers.NUMBER_OF);
    }

    private int calculateWorkDaysOnHollyDay(List<LocalDate> hollyDays) {
        return hollyDays
                .stream()
                .filter(t -> BusinessDays.contains(t.getDayOfWeek()))
                .collect(Collectors.toList())
                .size();
    }

    private int calculateWorkDays(int workDaysOnHollyDay, int workDays) {
        return workDays - workDaysOnHollyDay;
    }

}
