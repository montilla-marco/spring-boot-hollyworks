package org.mmontilla.hollyworkdays.presentation;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mmontilla.hollyworkdays.application.HollyWorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Controller.class)
public class ControllerApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HollyWorksService service;

    public static final String HOLLY_DAYS_WEEK_NUMBER_MESSAGE = "Feriados En Dia Semana = 4";
    public static final String WORKDAYS_MESSAGE = "Dias Laborales = 256";

    public static final String CONTEXT_PATH = "/api/v1/hollyworks";
    @Test
    void givenNoQueryParams_whenFindAll_thenReturnBadRequest() throws Exception {
        // act and assert
        this.mockMvc.perform(get(CONTEXT_PATH))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.emptyString()));
    }

    @Test
    void givenStringValuedQueryParams_whenFindAll_thenReturnBadRequest() throws Exception {
        // act and assert
        this.mockMvc.perform(get(CONTEXT_PATH)
                        .param("yearOf", "ban")
                        .param("numberOf", "ban")
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.emptyString()));
    }

    @Test
    void givenNumberQueryParams_whenFindAll_thenReturnOk() throws Exception {
        //arrange
        List<String> mockList = Arrays.asList(HOLLY_DAYS_WEEK_NUMBER_MESSAGE, WORKDAYS_MESSAGE);
        when(service.getHollyWorks(2023, 5)).thenReturn(mockList);
        // act and assert
        this.mockMvc.perform(get(CONTEXT_PATH)
                        .param("yearOf", "2023")
                        .param("numberOf", "5")
                )
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(HOLLY_DAYS_WEEK_NUMBER_MESSAGE)))
                .andExpect(content().string(Matchers.containsString(WORKDAYS_MESSAGE)));
    }
}

