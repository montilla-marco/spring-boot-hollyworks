package org.mmontilla.hollyworkdays.application.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmontilla.hollyworkdays.application.exception.ApplicationException;
import org.mmontilla.hollyworkdays.application.utils.ApplicationTierConfiguration.ErrorMessages;
import org.mmontilla.hollyworkdays.repository.DateRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HollyWorksServiceImplTest {

    @InjectMocks
    private HollyWorksServiceImpl service;

    @Mock
    private DateRepository dateRepository;

    public static final int DEFAULT_YEAR = 2023;
    public static final int DEFAULT_HOLLY_DAYS_NUMBER = 3;
    public static final int INVALID_NUMBER = 0;
    public static final int RESPONSE_ELEMENTS_NUMBER = 2;

    @Test
    void givenInvalidYearRange_whenGetHollyWorks_thenThrowsApplicationException() {
        //arrange
        ApplicationException exception = new ApplicationException(ErrorMessages.INVALID_YEAR.toString());
        //assert & act
        ApplicationException thrown = assertThrows(ApplicationException.class, () -> {
            service.getHollyWorks(INVALID_NUMBER, INVALID_NUMBER);
        }, "ApplicationException was expected");
        //assert
        assertThat(thrown.getMessage()).isEqualTo(ErrorMessages.INVALID_YEAR.toString());
    }

    @Test
    void givenValidYearAndInvalidNumberOfRange_whenGetHollyWorks_thenThrowsApplicationException() {
        //arrange
        ApplicationException exception = new ApplicationException(ErrorMessages.INVALID_NUMBER_OF.toString());
        //assert & act
        ApplicationException thrown = assertThrows(ApplicationException.class, () -> {
            service.getHollyWorks(DEFAULT_YEAR, INVALID_NUMBER);
        }, "ApplicationException was expected");
        //assert
        assertThat(thrown.getMessage()).isEqualTo(ErrorMessages.INVALID_NUMBER_OF.toString());
    }

    @Test
    void givenValidParamsRange_whenGetHollyWorks_thenReturnResponseList() {
        //act
        List<String> hollyWorks = service.getHollyWorks(DEFAULT_YEAR, DEFAULT_HOLLY_DAYS_NUMBER);
        //assert
        assertThat(hollyWorks.size()).isEqualTo(RESPONSE_ELEMENTS_NUMBER);
    }

    @Test
    void givenTwoHollyDaysOnWorkDay_whenGetHollyWorks_thenCompareReturnResponseList() {
        //arrange
        int TEST_YEAR = 2023;
        LocalDate firstDay = LocalDate.of(TEST_YEAR, 5, 17);
        LocalDate secondDay = LocalDate.of(TEST_YEAR, 5, 18);
        LocalDate thirdDay = LocalDate.of(TEST_YEAR, 5, 19);
        List<LocalDate> hollyDays = new ArrayList<>();
        hollyDays.add(firstDay);
        hollyDays.add(secondDay);
        List<LocalDate> workDays = new ArrayList<>();
        workDays.add(firstDay);
        workDays.add(secondDay);
        workDays.add(thirdDay);
        when(dateRepository.getHollyDays(TEST_YEAR, 2)).thenReturn(hollyDays);
        when(dateRepository.getWorkDays(TEST_YEAR)).thenReturn(workDays);
        //act
        List<String> hollyWorks = service.getHollyWorks(TEST_YEAR, 2);
        //assert
        assertThat(hollyWorks.size()).isEqualTo(RESPONSE_ELEMENTS_NUMBER);
        assertThat(hollyWorks.get(0)).isEqualTo("Feriados En Dia Semana = 2");
        assertThat(hollyWorks.get(1)).isEqualTo("Dias Laborales = 1");
    }
}