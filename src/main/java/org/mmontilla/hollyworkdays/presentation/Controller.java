package org.mmontilla.hollyworkdays.presentation;

import org.mmontilla.hollyworkdays.application.HollyWorksService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hollyworks")
public class Controller {

    private final HollyWorksService service;

    public Controller(HollyWorksService service) {
        this.service = service;
    }

    //todo: make return ResponseEntity<List<String>>
    @GetMapping
    List<String> findAll(@RequestParam int yearOf, @RequestParam int numberOf) {
        return service.getHollyWorks(yearOf, numberOf);
    }
}
