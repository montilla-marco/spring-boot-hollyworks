package org.mmontilla.hollyworkdays.presentation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmontilla.hollyworkdays.application.HollyWorksService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class ControllerTest {

    public static final int DEFAULT_YEAR = 2023;

    public static final int DEFAULT_HOLLY_DAYS_NUMBER = 6;

    public static final int TEST_YEAR = 2024;

    public static final int TEST_HOLLY_DAYS_NUMBER = 10;

    public static final String HOLLY_DAYS_WEEK_NUMBER_MESSAGE = "Feriados En Dia Semana = 4";

    public static final String WORKDAYS_MESSAGE = "Dias Laborales = 256";

    public static final String TEST_HOLLY_DAYS_WEEK_NUMBER_MESSAGE = "Feriados En Dia Semana = 8";

    public static final String TEST_WORKDAYS_MESSAGE = "Dias Laborales = 254";

    @InjectMocks
    private Controller controller;

    @Mock
    private HollyWorksService service;

    @Test
    void give_YearOfAndNumberOf_whenFindAll_thenReturnHollyWorksDaysList() {
        //act
        List<String> result = controller.findAll(DEFAULT_YEAR, DEFAULT_HOLLY_DAYS_NUMBER);
        //assert
        assertThat(result).isNotNull();
    }

    @Test
    void give_YearOfAndNumberOf_whenFindAll_thenReturnHollyWorksDaysNotEmptyList() {
        //arrange
        List<String> mockList = List.of("");
        Mockito.when(service.getHollyWorks(DEFAULT_YEAR, DEFAULT_HOLLY_DAYS_NUMBER)).thenReturn(mockList);
        //act
        List<String> result = controller.findAll(DEFAULT_YEAR, DEFAULT_HOLLY_DAYS_NUMBER);
        //assert
        assertThat(result).isNotNull();
        assertThat(result.get(0)).isNotNull();
    }

    @Test
    void give_YearOfAndNumberOf_whenFindAll_thenReturnHollyWorksDaysListWithExpectedResults() {
        //arrange
        List<String> mockList = Arrays.asList(HOLLY_DAYS_WEEK_NUMBER_MESSAGE, WORKDAYS_MESSAGE);
        Mockito.when(service.getHollyWorks(DEFAULT_YEAR, DEFAULT_HOLLY_DAYS_NUMBER)).thenReturn(mockList);
        //act
        List<String> result = controller.findAll(DEFAULT_YEAR, DEFAULT_HOLLY_DAYS_NUMBER);
        //assert
        assertThat(result).isNotNull();
        assertThat(result.get(0)).isEqualTo(HOLLY_DAYS_WEEK_NUMBER_MESSAGE);
        assertThat(result.get(1)).isEqualTo(WORKDAYS_MESSAGE);
    }

    @Test
    void give_ChangeYearOfAndNumberOf_whenFindAll_thenReturnHollyWorksDaysListWithExpectedResults() {
        //arrange
        List<String> mockList = Arrays.asList(TEST_HOLLY_DAYS_WEEK_NUMBER_MESSAGE, TEST_WORKDAYS_MESSAGE);
        Mockito.when(service.getHollyWorks(TEST_YEAR, TEST_HOLLY_DAYS_NUMBER)).thenReturn(mockList);
        //act
        List<String> result = controller.findAll(TEST_YEAR, TEST_HOLLY_DAYS_NUMBER);
        //assert
        assertThat(result).isNotNull();
        assertThat(result.get(0)).isEqualTo(TEST_HOLLY_DAYS_WEEK_NUMBER_MESSAGE);
        assertThat(result.get(1)).isEqualTo(TEST_WORKDAYS_MESSAGE);
    }

}