package org.mmontilla.hollyworkdays.repository.helper;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class WorkDaysGenerator extends AbstractDateGenerator<List<LocalDate>> {
    final Set<DayOfWeek> businessDays = Set.of(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY
    );

    public WorkDaysGenerator() {
        super();
    }

    @Override
    public List<LocalDate> getDate(int yearOf) {
        return startDateOf(yearOf)
                .datesUntil(endDateOf(yearOf))
                .filter(t -> businessDays.contains(t.getDayOfWeek()))
                .collect(Collectors.toList());
    }

}
