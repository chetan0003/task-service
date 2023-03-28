package com.task.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Slf4j
public class TaskUtil {

    public static String getStringDateFromLocalDate(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMMM dd");
        String formattedString = localDate.format(formatter);
        return formattedString;
    }
}
