package org.mmontilla.hollyworkdays.repository;

import java.time.LocalDate;
import java.util.List;

public interface DateRepository {

    List<LocalDate> getWorkDays(int year);

    List<LocalDate> getHollyDays(int year, int numberOf);
}
