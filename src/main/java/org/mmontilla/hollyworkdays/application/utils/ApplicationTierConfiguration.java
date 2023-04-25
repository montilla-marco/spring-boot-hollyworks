package org.mmontilla.hollyworkdays.application.utils;

import java.time.DayOfWeek;
import java.util.Set;

public class ApplicationTierConfiguration {

    public enum ErrorMessages {
        INVALID_YEAR("APPLICATION_TIER_ERROR: The year must be in a valid range from 2023 to 3000"),
        INVALID_NUMBER_OF("APPLICATION_TIER_ERROR: The number of hally days must be in a valid range from 1 to 18"),
        HOLLY_DAY_RESULT_MESSAGE(""),
        WORK_DAY_RESULT_MESSAGE("");
        public final String message;

        ErrorMessages(String message) {
            this.message = message;
        }
    }

    public enum InformationMessages {
        HOLLY_DAY_RESULT_MESSAGE("Feriados En Dia Semana = "),
        WORK_DAY_RESULT_MESSAGE("Dias Laborales = ");
        public final String message;

        InformationMessages(String message) {
            this.message = message;
        }
    }

    public static final Set<DayOfWeek> BusinessDays = Set.of(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY
    );
}
