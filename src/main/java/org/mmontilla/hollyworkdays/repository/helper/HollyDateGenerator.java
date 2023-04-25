package org.mmontilla.hollyworkdays.repository.helper;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class HollyDateGenerator extends AbstractDateGenerator<LocalDate> {
    public HollyDateGenerator() {
        super();
    }

    @Override
    public LocalDate getDate(int yearOf) {
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(
                        startDateOf(yearOf).toEpochDay(),
                        endDateOf(yearOf).toEpochDay()
                );

        return LocalDate.ofEpochDay(randomDay);
    }
}