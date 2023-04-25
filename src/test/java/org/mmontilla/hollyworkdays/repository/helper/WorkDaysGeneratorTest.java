package org.mmontilla.hollyworkdays.repository.helper;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WorkDaysGeneratorTest {

    public static final int DEFAULT_YEAR = 2023;

    public static final int EXPECTED_WORKDAYS = 260;
    @Test
    void givenDefaultYear_whenGetDate_thenReturnWorkDaysList() {
        //arrange
        WorkDaysGenerator workDaysGenerator = new WorkDaysGenerator();
        //act
        List<LocalDate> all = workDaysGenerator.getDate(DEFAULT_YEAR);
        //assert
        assertThat(all).isNotNull();
        assertThat(all.get(0)).isNotNull();
        //act
        all = workDaysGenerator.getDate(DEFAULT_YEAR);
        //assert
        assertThat(all.size()).isEqualTo(EXPECTED_WORKDAYS);
    }
}