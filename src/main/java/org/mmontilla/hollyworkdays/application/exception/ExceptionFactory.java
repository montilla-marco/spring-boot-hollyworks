package org.mmontilla.hollyworkdays.application.exception;

import org.mmontilla.hollyworkdays.application.exception.ApplicationException;
import org.mmontilla.hollyworkdays.application.utils.ApplicationTierConfiguration.ErrorMessages;
public class ExceptionFactory {

    public enum Exceptions {
        INVALID_YEAR_RANGE(new ApplicationException(ErrorMessages.INVALID_YEAR.toString())),
        INVALID_HOLLY_DAYS_RANGE(new ApplicationException(ErrorMessages.INVALID_NUMBER_OF.toString()));

        public final ApplicationException exception;

        Exceptions(ApplicationException exception) {
            this.exception = exception;
        }
    }
}
