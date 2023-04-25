package org.mmontilla.hollyworkdays.repository.utils;

public class RepositoryTierConfiguration {

    public enum Ranges {
        FIRST_DAY_OF_RANGE(01),
        LAST_DAY_OF_RANGE(31),
        FIRST_MONTH_OF_RANGE(01),
        LAST_MONTH_OF_RANGE(12);
        public final int day;
        Ranges(int day) {
            this.day = day;
        }
    }
    public enum ErrorMessages {
        INVALID_YEAR("REPOSITORY_ERROR: The year must be in a valid range from 2000 to 3000"),
        INVALID_HOLLY_DAYS_NUMBER("REPOSITORY_ERROR: The year must be in a valid range from 2000 to 3000");;
        public final String message;
        ErrorMessages(String message) {
            this.message = message;
        }
    }
}
