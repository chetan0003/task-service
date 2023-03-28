package com.task.service.impl;

import com.task.entities.HolidayDetail;
import com.task.entities.UserDetail;
import com.task.model.Holiday;
import com.task.model.Response;
import com.task.repository.HolidayRepository;
import com.task.service.HolidayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class HolidayServiceImpl implements HolidayService {

    private final HolidayRepository holidayRepository;
    @Override
    public Response createHoliday(Holiday holiday) {
        if(holiday.getDate() != null && holiday.getDate().contains("T")) {
            return Response.builder().statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value()).message("Please enter date..!").build();
        }
        UserDetail userDetail =UserDetail.builder().id(holiday.getUserId()).build();
        HolidayDetail holidayDetail = HolidayDetail.builder().name(holiday.getName()).date(holiday.getDate()).userDetail(userDetail).build();
        HolidayDetail holidayDetail1 = this.holidayRepository.save(holidayDetail);
        return Response.builder().statusCode(HttpStatus.CREATED.value()).data(holidayDetail1).build();
    }

    @Override
    public Response updateHoliday(Holiday holiday) {
        UserDetail userDetail = UserDetail.builder().id(holiday.getUserId()).build();
        HolidayDetail holidayDetail = HolidayDetail.builder().id(holiday.getId()).name(holiday.getName()).date(holiday.getDate()).userDetail(userDetail).build();
        HolidayDetail holidayDetail1 = this.holidayRepository.save(holidayDetail);
        return Response.builder().statusCode(HttpStatus.OK.value()).data(holidayDetail1).build();
    }

    @Override
    public Response getHoliday(Integer holidayId) {
        HolidayDetail holidayDetail1 = null;
       Optional<HolidayDetail> holidayDetail =  this.holidayRepository.findById(holidayId);
       if (holidayDetail.isPresent())
           holidayDetail1 = holidayDetail.get();
        return Response.builder().statusCode(HttpStatus.OK.value()).data(holidayDetail1).build();
    }

    @Override
    public Response getAllHolidays(Integer userId) {
        List<HolidayDetail> holidayDetailList = this.holidayRepository.findAllByUserId(userId);
        return Response.builder().statusCode(HttpStatus.OK.value()).data(holidayDetailList).build();
    }

    @Override
    public Response deleteHoliday(Integer holidayId) {
        HolidayDetail holidayDetail = HolidayDetail.builder().id(holidayId).build();
        this.holidayRepository.delete(holidayDetail);
        return Response.builder().statusCode(HttpStatus.OK.value()).build();
    }
}
