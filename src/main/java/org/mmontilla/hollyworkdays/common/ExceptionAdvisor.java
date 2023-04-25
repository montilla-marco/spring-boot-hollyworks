package org.mmontilla.hollyworkdays.common;

import org.mmontilla.hollyworkdays.application.exception.ApplicationException;
import org.mmontilla.hollyworkdays.repository.exception.RepositoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvisor extends ResponseEntityExceptionHandler {

    public enum ResponseFields {
        TIMESTAMP("timestamp"),
        STATUS("status"),
        MESSAGE("message"),
        ERROR("error");

        public final String field;

        ResponseFields(String field) {
            this.field = field;
        }
    }

    private Map<String, Object> getExceptionResponse(HttpStatus status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(ResponseFields.TIMESTAMP.field, LocalDate.now());
        body.put(ResponseFields.STATUS.field, status.value());
        body.put(ResponseFields.ERROR.field, status.getReasonPhrase());
        return body;
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Map<String, Object>> handleApplicationException(ApplicationException ex) {
        Map<String, Object> error = getExceptionResponse(HttpStatus.BAD_REQUEST);
        System.out.println("error = " + ex.getMessage());
        error.put(ResponseFields.MESSAGE.field, ex.getMessage());
        return new ResponseEntity<Map<String, Object>>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RepositoryException.class)
    public ResponseEntity<Map<String, Object>> handleRepositoryException(RepositoryException ex) {
        Map<String, Object> error = getExceptionResponse(HttpStatus.BAD_REQUEST);
        error.put(ResponseFields.MESSAGE.field, ex.getMessage());
        return new ResponseEntity<Map<String, Object>>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex) {
        Map<String, Object> error = getExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR);
        error.put(ResponseFields.MESSAGE.field, ex.getMessage());
        return new ResponseEntity<Map<String, Object>>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        Map<String, Object> error = getExceptionResponse(HttpStatus.BAD_REQUEST);
        error.put(ResponseFields.MESSAGE.field, ex.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
