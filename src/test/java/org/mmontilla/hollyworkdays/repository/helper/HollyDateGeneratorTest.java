package org.mmontilla.hollyworkdays.repository.helper;

import org.junit.jupiter.api.Test;
import org.mmontilla.hollyworkdays.repository.utils.RepositoryTierConfiguration.Ranges;

import java.time.LocalDate;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HollyDateGeneratorTest {

    public static final int DEFAULT_YEAR = 2023;

    public static final int D = 1999;

    public static final int THIRTY_FIRST_CENTURY_MIN_YEAR = 3001;

    @Test
    void givenARange_whenGetDate_thenReturnDataInRange() {
        //arrange
        LocalDate start = LocalDate.of(DEFAULT_YEAR, Ranges.FIRST_MONTH_OF_RANGE.day, Ranges.FIRST_DAY_OF_RANGE.day);
        LocalDate end = LocalDate.of(DEFAULT_YEAR, Ranges.LAST_MONTH_OF_RANGE.day, Ranges.LAST_DAY_OF_RANGE.day);
        //act
        LocalDate random = new HollyDateGenerator().getDate(DEFAULT_YEAR);
        //assert
        assertThat(random).isAfterOrEqualTo(start).isBefore(end);
    }

    @Test
    void givenALoopOfDateRequest_whenGetDate_thenReturnAllDataInRange() {
        //arrange
        LocalDate start = LocalDate.of(DEFAULT_YEAR, Ranges.FIRST_MONTH_OF_RANGE.day, Ranges.FIRST_DAY_OF_RANGE.day);
        LocalDate end = LocalDate.of(DEFAULT_YEAR, Ranges.LAST_MONTH_OF_RANGE.day, Ranges.LAST_DAY_OF_RANGE.day);
        HollyDateGenerator hollyDateGenerator = new HollyDateGenerator();
        //act & assert
        IntStream.range(0, 200)
                .forEach(index -> {
                    LocalDate random = hollyDateGenerator.getDate(DEFAULT_YEAR);
                    assertThat(random).isAfterOrEqualTo(start).isBefore(end);
                });
    }

}