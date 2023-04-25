package org.mmontilla.hollyworkdays.repository.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmontilla.hollyworkdays.repository.exception.RepositoryException;
import org.mmontilla.hollyworkdays.repository.helper.HollyDateGenerator;
import org.mmontilla.hollyworkdays.repository.helper.WorkDaysGenerator;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DateRepositoryImplTest {

    public static final int DEFAULT_YEAR = 2023;

    public static final int DEFAULT_MONTH = 5;

    public static final int ZERO = 0;

    public static final int ONE_HOLLY_DAY = 1;

    public static final int MY_DAY = 17;

    public static final String ERROR = "REPOSITORY_ERROR";

    @InjectMocks
    private DateRepositoryImpl repository;

    @Mock
    private HollyDateGenerator hollyDateGenerator;

    @Mock
    private WorkDaysGenerator workDaysGenerator;

    @Test
    void givenInvalidYearOf_whenGetWorkDays_thenReturnListOfWorksDays() {
        //arrange
        when(workDaysGenerator.getDate(ZERO)).thenThrow(new RepositoryException(ERROR));
        //act & assert
        RepositoryException thrown = assertThrows(RepositoryException.class, () -> {
            repository.getWorkDays(ZERO);
        }, "RepositoryException was expected");

        assertThat(thrown.getMessage()).isEqualTo(ERROR);
    }

    @Test
    void givenYearOf_whenGetWorkDays_thenReturnListOfWorksDays() {
        //arrange
        LocalDate date = LocalDate.of(DEFAULT_YEAR, DEFAULT_MONTH, MY_DAY);
        List<LocalDate> worksDays = List.of(date);
        when(workDaysGenerator.getDate(DEFAULT_YEAR)).thenReturn(worksDays);
        //act
        List<LocalDate> workDays = repository.getWorkDays(DEFAULT_YEAR);
        //assert
        assertThat(workDays).isEqualTo(workDays);
        assertThat(workDays.get(0)).isEqualTo(date);
    }

    @Test
    void givenYearOfAndInvalidNumberOf_whenGetHollyDays_thenThrowsInvalidNumberOfException() {
        //act & assert
        RepositoryException thrown = assertThrows(RepositoryException.class, () -> {
            repository.getHollyDays(DEFAULT_YEAR, ZERO);
        }, "RepositoryException was expected");
    }

    @Test
    void givenValidYearOfAndInvalidNumberOf_whenGetHollyDays_thenReturnListOfWorksDays() {
        //act
        List<LocalDate> hollyDays = repository.getHollyDays(DEFAULT_YEAR, ONE_HOLLY_DAY);
        //assert
        assertThat(hollyDays).isInstanceOf(List.class);
    }

    @Test
    void givenAValidYearOfAndInvalidNumberOf_whenGetHollyDays_thenReturnNotEmptyListOfOneWorksDays() {
        //arrange
        LocalDate hollyDay = LocalDate.of(DEFAULT_YEAR, DEFAULT_MONTH, MY_DAY);
        Mockito.when(hollyDateGenerator.getDate(DEFAULT_YEAR)).thenReturn(hollyDay);
        //act
        List<LocalDate> hollyDays = repository.getHollyDays(DEFAULT_YEAR, ONE_HOLLY_DAY);
        //assert
        assertThat(hollyDays.size()).isEqualTo(ONE_HOLLY_DAY);
        assertThat(hollyDays.get(0)).isNotNull();
        assertThat(hollyDays.get(0)).isEqualTo(hollyDay);
    }

    @Test
    void givenAValidYearOfAndInvalidNumberOf_whenGetHollyDays_thenReturnListOfUniqueOneWorksDays() {
        //arrange
        LocalDate hollyDay = LocalDate.of(1977, 5, 18);
        LocalDate bornDay = LocalDate.of(2020, 2, 10);
        LocalDate oldDay = LocalDate.of(1943, 5, 21);
        Mockito.when(hollyDateGenerator.getDate(DEFAULT_YEAR))
                .thenReturn(hollyDay)
                .thenReturn(hollyDay)
                .thenReturn(hollyDay)
                .thenReturn(hollyDay)
                .thenReturn(bornDay)
                .thenReturn(oldDay);

        //act
        List<LocalDate> hollyDays = repository.getHollyDays(DEFAULT_YEAR, 3);
        //assert
        assertThat(hollyDays.size()).isEqualTo(3);
        assertThat(hollyDays.get(0)).isNotNull();
        assertThat(hollyDays.get(0)).isEqualTo(hollyDay);
        assertThat(hollyDays.get(1)).isNotNull();
        assertThat(hollyDays.get(1)).isEqualTo(bornDay);
        assertThat(hollyDays.get(2)).isNotNull();
        assertThat(hollyDays.get(2)).isEqualTo(oldDay);
    }
}