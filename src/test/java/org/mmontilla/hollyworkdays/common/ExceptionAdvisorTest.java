package org.mmontilla.hollyworkdays.common;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmontilla.hollyworkdays.application.exception.ApplicationException;
import org.mmontilla.hollyworkdays.common.ExceptionAdvisor.ResponseFields;
import org.mmontilla.hollyworkdays.repository.exception.RepositoryException;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class ExceptionAdvisorTest {

    public static final String ERROR_MESSAGE = "ERROR: ";
    @InjectMocks
    private ExceptionAdvisor advisor;

    @Test
    void givenApplicationException_whenSomeErrorRaisedInApplicationTier_thenReturnError() {
        //arrange
        LocalDate now = LocalDate.now();
        //act
        ResponseEntity<Map<String, Object>> mapResponseEntity =
                advisor.handleApplicationException(new ApplicationException(ERROR_MESSAGE));
        //assert
        assertThat(mapResponseEntity).isNotNull();
        Map<String, Object> entityBody = mapResponseEntity.getBody();
        assertThat(entityBody.get(ResponseFields.TIMESTAMP.field)).isEqualTo(now);
        assertThat(entityBody.get(ResponseFields.STATUS.field)).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(entityBody.get(ResponseFields.ERROR.field)).isEqualTo(HttpStatus.BAD_REQUEST.getReasonPhrase());
        assertThat(entityBody.get(ResponseFields.MESSAGE.field)).isEqualTo(ERROR_MESSAGE);
    }

    @Test
    void givenRepositoryException_whenSomeErrorRaisedInRepositoryTier_thenReturnError() {
        //arrange
        LocalDate now = LocalDate.now();
        //act
        ResponseEntity<Map<String, Object>> mapResponseEntity =
                advisor.handleRepositoryException(new RepositoryException(ERROR_MESSAGE));
        //assert
        assertThat(mapResponseEntity).isNotNull();
        Map<String, Object> entityBody = mapResponseEntity.getBody();
        assertThat(entityBody.get(ResponseFields.TIMESTAMP.field)).isEqualTo(now);
        assertThat(entityBody.get(ResponseFields.STATUS.field)).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(entityBody.get(ResponseFields.ERROR.field)).isEqualTo(HttpStatus.BAD_REQUEST.getReasonPhrase());
        assertThat(entityBody.get(ResponseFields.MESSAGE.field)).isEqualTo(ERROR_MESSAGE);
    }

    @Test
    void givenAnyException_whenNotControlledErrorRaised_thenReturnError() {
        //arrange
        LocalDate now = LocalDate.now();
        //act
        ResponseEntity<Map<String, Object>> mapResponseEntity =
                advisor.handleAllExceptions(new IllegalArgumentException(ERROR_MESSAGE));
        //assert
        assertThat(mapResponseEntity).isNotNull();
        Map<String, Object> entityBody = mapResponseEntity.getBody();
        assertThat(entityBody.get(ResponseFields.TIMESTAMP.field)).isEqualTo(now);
        assertThat(entityBody.get(ResponseFields.STATUS.field)).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(entityBody.get(ResponseFields.ERROR.field)).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        assertThat(entityBody.get(ResponseFields.MESSAGE.field)).isEqualTo(ERROR_MESSAGE);
    }
}