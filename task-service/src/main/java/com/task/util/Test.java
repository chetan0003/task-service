package com.task.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static LocalDate calculateEndDate(LocalDate startDate, int durationInDays, List<LocalDate> holidays) {
        // Calculate the end date by adding the duration to the start date, skipping weekends and holidays
        LocalDate endDate = startDate;
        for (int i = 0; i < durationInDays; i++) {
            endDate = endDate.plus(1, ChronoUnit.DAYS);
            if (endDate.getDayOfWeek() == DayOfWeek.SATURDAY || endDate.getDayOfWeek() == DayOfWeek.SUNDAY || holidays.contains(endDate)) {
                durationInDays++;
            }
        }
        return endDate;
    }

    public static void main(String[] args) {
        // Example usage: calculate the end date of a task that starts on March 28th and has a duration of 5 days, excluding weekends and holidays
        LocalDate startDate = LocalDate.of(2023, 3, 28);
        int durationInDays = 5;
        List<LocalDate> holidays = new ArrayList<>();
        holidays.add(LocalDate.of(2023, 4, 1)); // April Fools' Day
        LocalDate endDate = calculateEndDate(startDate, durationInDays, holidays);
        System.out.println("End date: " + endDate.toString()); // Output: End date: 2023-04-03
    }
}
