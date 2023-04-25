package org.mmontilla.hollyworkdays.presentation;

import org.mmontilla.hollyworkdays.application.HollyWorksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hollyworks")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Controller {

    private final HollyWorksService service;

    public Controller(HollyWorksService service) {
        this.service = service;
    }

    //todo: make return
    @GetMapping
    ResponseEntity<List<String>> findAll(@RequestParam @Min(value = 2023, message = "INVALID_YEAR")
                                         @Max(value = 3000, message = "INVALID_YEAR") int yearOf,
                                         @RequestParam @Min(value = 1, message = "INVALID_NUMBER_OF")
                                         @Max(value = 18, message = "INVALID_NUMBER_OF") int numberOf) {
        return new ResponseEntity(service.getHollyWorks(yearOf, numberOf), HttpStatus.OK);
    }
}
