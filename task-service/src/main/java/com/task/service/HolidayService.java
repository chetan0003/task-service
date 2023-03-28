package com.task.service;

import com.task.model.Holiday;
import com.task.model.Response;

public interface HolidayService {

    Response createHoliday(Holiday holiday);
    Response updateHoliday(Holiday holiday);
    Response getHoliday(Integer holidayId);
    Response getAllHolidays(Integer userId);
    Response deleteHoliday(Integer holidayId);
}
