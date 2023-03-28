package com.task.controller;

import com.task.model.Response;
import com.task.model.Task;
import com.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@CrossOrigin(value = "*")
public class TaskController {

    private final TaskService taskService;
    @PostMapping()
    public ResponseEntity<Response> getCalculateEndDate(@RequestBody Task task) {
        return new ResponseEntity<>(this.taskService.getCalculateEndDate(task), HttpStatus.OK);
    }

}
