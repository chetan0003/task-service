package com.task.service.impl;

import com.task.entities.HolidayDetail;
import com.task.model.Response;
import com.task.model.Task;
import com.task.repository.HolidayRepository;
import com.task.service.TaskService;
import com.task.util.TaskUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final HolidayRepository holidayRepository;
    @Override
    public Response getCalculateEndDate(Task task) {
        List<HolidayDetail> holidayDetailList = holidayRepository.findAll();
        // Calculate the end date by adding the duration to the start date, skipping weekends and holidays
        Integer durationInDays = task.getDays();
        String [] date = task.getStartDate().split("-");
        LocalDate endDate = LocalDate.of(Integer.valueOf(date[0]), Integer.valueOf(date[1]), Integer.valueOf(date[2]));//task.getStartDate();
        for (int i = 0; i < durationInDays; i++) {
            endDate = endDate.plus(1, ChronoUnit.DAYS);
            if (endDate.getDayOfWeek() == DayOfWeek.SATURDAY || endDate.getDayOfWeek() == DayOfWeek.SUNDAY || checkHoliday(holidayDetailList ,endDate)) {
                durationInDays++;
            }
        }
        log.info(task.getStartDate() + " " + task.getDays());
        log.info("end Date " + endDate);
        return Response.builder().statusCode(200).data(TaskUtil.getStringDateFromLocalDate(endDate)).build();
    }

    Boolean checkHoliday(List<HolidayDetail> holidayDetailList, LocalDate endDate) {
        for(HolidayDetail holiday : holidayDetailList) {
            String [] date = holiday.getDate().split("-");
            LocalDate holidayDate = LocalDate.of(Integer.valueOf(date[0]), Integer.valueOf(date[1]), Integer.valueOf(date[2]));
            if(holidayDate.equals(endDate)) {
              return true;
            }
        }
        return false;
    }
}
