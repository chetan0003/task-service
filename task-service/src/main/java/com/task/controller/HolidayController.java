package com.task.controller;

import com.task.model.Holiday;
import com.task.model.Response;
import com.task.model.User;
import com.task.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/holiday")
@RequiredArgsConstructor
@CrossOrigin
public class HolidayController {

    private final HolidayService holidayService;

    @PostMapping("/create")
    public ResponseEntity<Response> postHoliday(@RequestBody Holiday holiday) {
        return new ResponseEntity<>(this.holidayService.createHoliday(holiday), HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Response> updateHoliday(@RequestBody Holiday holiday) {
        return new ResponseEntity<>(this.holidayService.updateHoliday(holiday), HttpStatus.OK);
    }

    @GetMapping("/{holidayId}")
    public ResponseEntity<Response> getHoliday(@PathVariable Integer holidayId) {
        return new ResponseEntity<>(this.holidayService.getHoliday( holidayId), HttpStatus.OK);
    }
    @GetMapping("/getAllHolidays/{userId}")
    public ResponseEntity<Response> getAllHolidays(@PathVariable Integer userId) {
        return new ResponseEntity<>(this.holidayService.getAllHolidays( userId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteHoliday(@PathVariable Integer id) {
        return new ResponseEntity<>(this.holidayService.deleteHoliday( id), HttpStatus.OK);
    }
}
