package com.task.service.impl;

import com.task.entities.HolidayDetail;
import com.task.entities.UserDetail;
import com.task.model.Holiday;
import com.task.model.Response;
import com.task.repository.HolidayRepository;
import com.task.service.HolidayService;
import com.task.util.TaskUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HolidayServiceImpl implements HolidayService {

    private final HolidayRepository holidayRepository;
    @Override
    public Response createHoliday(Holiday holiday) {
        UserDetail userDetail =UserDetail.builder().id(holiday.getUserId()).build();
        HolidayDetail holidayDetail = HolidayDetail.builder().name(holiday.getName()).date(holiday.getDate()).userDetail(userDetail).build();
        HolidayDetail holidayDetail1 = this.holidayRepository.save(holidayDetail);
        return Response.builder().statusCode(201).data(holidayDetail1).build();
    }

    @Override
    public Response updateHoliday(Holiday holiday) {
        UserDetail userDetail = UserDetail.builder().id(holiday.getUserId()).build();
        HolidayDetail holidayDetail = HolidayDetail.builder().id(holiday.getId()).name(holiday.getName()).date(holiday.getDate()).userDetail(userDetail).build();
        HolidayDetail holidayDetail1 = this.holidayRepository.save(holidayDetail);
        return Response.builder().statusCode(201).data(holidayDetail1).build();
    }

    @Override
    public Response getHoliday(Integer holidayId) {
        HolidayDetail holidayDetail1 = null;
       Optional<HolidayDetail> holidayDetail =  this.holidayRepository.findById(holidayId);
       if (holidayDetail.isPresent())
           holidayDetail1 = holidayDetail.get();
        return Response.builder().statusCode(200).data(holidayDetail1).build();
    }

    @Override
    public Response getAllHolidays(Integer userId) {
        List<HolidayDetail> holidayDetailList = this.holidayRepository.findAllByUserId(userId);
        return Response.builder().statusCode(200).data(holidayDetailList).build();
    }

    @Override
    public Response deleteHoliday(Integer holidayId) {
        HolidayDetail holidayDetail = HolidayDetail.builder().id(holidayId).build();
        this.holidayRepository.delete(holidayDetail);
        return Response.builder().statusCode(200).build();
    }
}
