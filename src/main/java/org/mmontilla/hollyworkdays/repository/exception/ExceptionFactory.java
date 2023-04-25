package org.mmontilla.hollyworkdays.repository.exception;

import org.mmontilla.hollyworkdays.repository.utils.RepositoryTierConfiguration.ErrorMessages;

public class ExceptionFactory {

    public enum Exceptions {
        INVALID_YEAR(new RepositoryException(ErrorMessages.INVALID_YEAR.message)),
        INVALID_HOLLY_DAYS_NUMBER(new RepositoryException(ErrorMessages.INVALID_HOLLY_DAYS_NUMBER.message));

        public final RepositoryException exception;

        Exceptions(RepositoryException exception) {
            this.exception = exception;
        }
    }
}
